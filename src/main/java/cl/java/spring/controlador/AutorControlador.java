
package cl.java.spring.controlador;

import cl.java.spring.modelo.Autor;
import cl.java.spring.repositorio.InterfaceRepositoryAutor;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private InterfaceRepositoryAutor interfaceRepositoryAutor;
    
    @GetMapping("/nuevo")
	public String autorNuevo(Autor autor) {
		return "autor/form";
	}
	
	@GetMapping("/editar/{id}")
	public String autorEditar(@PathVariable long id, Model modelo) {
		Autor autor = interfaceRepositoryAutor.search(id);
		modelo.addAttribute("autor", autor);
		return "autor/form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@RequestParam long id) {
		interfaceRepositoryAutor.delete(id);
		return "redirect:/autor/listado";
	}

	@PostMapping("/procesar")
	public String procesarForm(@Valid Autor autor, BindingResult informeValidacion) {
		if(informeValidacion.hasErrors()) {
			return "autor/form";
		}
		if(autor.getId()==0) {
			interfaceRepositoryAutor.create(autor);
		}else {
			interfaceRepositoryAutor.update(autor);
		}
		return "redirect:/autor/listado";
	}
	
	@GetMapping("/listado")
	public String editar(Model modelo) {
		List<Autor> lista = interfaceRepositoryAutor.list();
		modelo.addAttribute("autores", lista);
		return "autor/listado";
	}
	
}
