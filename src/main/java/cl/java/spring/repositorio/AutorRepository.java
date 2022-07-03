/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.java.spring.repositorio;

import cl.java.spring.modelo.Autor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AutorRepository implements InterfaceRepositoryAutor {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private Autor makeObject(ResultSet rs, int fileNum)throws SQLException{
        long id =rs.getLong("id");
        String rut =rs.getString("rut");
        String nombres = rs.getString("nombres");
        String apellidoPaterno = rs.getString("apellidoPaterno");
        String apellidoMaterno = rs.getString("apellidoMaterno");
        String nacionalidad = rs.getString("nacionalidad");
        LocalDate fechaNacimiento = rs.getObject("fechaNacimiento", LocalDate.class);
        LocalDate fechaDefuncion = rs.getObject("fechaDefuncion", LocalDate.class); 
        
        return new Autor(id, rut, nombres, apellidoPaterno, apellidoMaterno, nacionalidad, fechaNacimiento, fechaDefuncion);
    }
    
    @Override
    public List<Autor> list() {
            String sql ="select * from Autores";
            return jdbcTemplate.query(sql, this::makeObject);
        
    }

    @Override
    public Autor search(long id) {
        
        String sql ="Select * from Autores where id=?";
        return jdbcTemplate.queryForObject(sql, this::makeObject, id);
    }

    @Override
    public void create(Autor autor) {
        String sql="Insert into Autores(rut, nombres, apellidoPaterno, apellidoMaterno, nacionalidad, fechaNacimiento, fechaDefuncion values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, autor.getRut(), autor.getNombre(), autor.getApellidoP(), autor.getApellidoM(), autor.getNacionalidad(), autor.getFechaNacimiento(), autor.getFechaDefuncion());
    }

    @Override
    public void update(Autor autor) {
        String sql = "Update Autores set rut = ?, nombres = ?, "
                + "apellidoPaterno =?, apellidoMaterno =?"
                + "nacionalidad =?, fechaNacimiento =?"
                + "fechaDefuncion =? where id = ?";
        jdbcTemplate.update(sql, autor.getRut(), autor.getNombre(), autor.getApellidoP(), autor.getApellidoM(), autor.getNacionalidad(), autor.getFechaNacimiento(), autor.getFechaDefuncion());
    }

    @Override
    public void delete(long id) {
        
        String sql = "Delete from Autores where id =?";
        jdbcTemplate.update(sql, id);
    }
    
}
