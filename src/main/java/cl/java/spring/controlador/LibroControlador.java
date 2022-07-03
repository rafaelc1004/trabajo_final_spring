
package cl.java.spring.controlador;

import cl.java.spring.modelo.Autor;
import cl.java.spring.modelo.Libro;
import cl.java.spring.repositorio.InterfaceRepositoryAutor;
import cl.java.spring.repositorio.InterfaceRepositoryLibro;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private InterfaceRepositoryLibro interfaceRepositoryLibro;
    
    @Autowired
    private InterfaceRepositoryAutor interfaceRepositoryAutor;
    
    @GetMapping("/nuevo")
    public String libroForm(Libro libro, Model modelo){
        List<Autor> listaAutor = interfaceRepositoryAutor.list();
        modelo.addAttribute("autores", listaAutor);
        return "libro/form";
    }
    
    @GetMapping("/edita/{id}")
    public String editarLibro(@PathVariable long id, Model modelo){
        Libro libro = interfaceRepositoryLibro.search(id);
        modelo.addAttribute("libro", libro);
        List<Autor> listaAutor = interfaceRepositoryAutor.list();
        modelo.addAttribute("autores", listaAutor);
        return "libro/form";
    }
    
    @GetMapping("/eliminar/{id}")
    private String eliminar(@PathVariable long id){
        interfaceRepositoryLibro.delete(id);
        return "redirect:/libro/listado";
    }
    
    @PostMapping("/procesar")
    public String procesarForm(@Valid Libro libro, BindingResult informeValidacion){
        if(informeValidacion.hasErrors()){
            return "libro/form";
        }
        if(libro.getId()==0){
            interfaceRepositoryLibro.create(libro);
        }else{
            interfaceRepositoryLibro.update(libro);
        }
        return "libro/listado";
    }
    
    @GetMapping("/listado")
    public String listado(Model modelo){
        List<Libro>lista = interfaceRepositoryLibro.list();
        modelo.addAttribute("libros", lista);
        return "libro/listado";
    }
            
    
}
