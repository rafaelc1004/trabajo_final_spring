
package cl.java.spring.repositorio;


import cl.java.spring.modelo.Autor;
import cl.java.spring.modelo.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LibroRepository implements InterfaceRepositoryLibro {
    

    
    @Autowired
    private InterfaceRepositoryAutor interfaceRepositoryAutor;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private Libro makeObject(ResultSet rs, int fuleNum)throws SQLException{
        long id = rs.getLong("id");
        String isbn = rs.getString("isbn");
        String nombre = rs.getString("nombre");
        LocalDate anioPublicacion = rs.getObject("anioPublicaion", LocalDate.class);
        long idAutor = rs.getLong("idAutor");
        Autor autor = interfaceRepositoryAutor.search(idAutor);
        
        return new Libro(id, isbn, nombre, anioPublicacion, autor);
    }
    
    
    @Override
    public List<Libro> list() {
        String sql = "Select * from Libros";
        return jdbcTemplate.query(sql, this::makeObject);
  
    }

    @Override
    public Libro search(long id) {
        String sql = "Select * from libros where id=?";
        return jdbcTemplate.queryForObject(sql, this::makeObject, id);
    }

    @Override
    public void create(Libro libro) {
        String sql = "Insert into Libros(isbn, nombre, anioPublicacion, "
                + "idAutor Values(?,?,?,?)";
        jdbcTemplate.update(sql, libro.getIsbn(), libro.getNombre(), libro.getAnioPublicacion(), libro.getAutor().getId());
                
    }

    @Override
    public void update(Libro libro) {
        String sql = "update Libros set isbn = ?, nombre = ?"
                + "anioPublicacion = ?, idAutor = ? where id = ?";
        jdbcTemplate.update(sql, libro.getIsbn(), libro.getNombre(), libro.getAnioPublicacion(), libro.getAutor().getId(), libro.getId());
    }

    public void delete(long id) {
        String sql = "Delete from Libros Where id = ?";
        jdbcTemplate.update(sql, id);
    }

    
}
