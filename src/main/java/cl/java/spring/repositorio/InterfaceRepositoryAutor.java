/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cl.java.spring.repositorio;

import cl.java.spring.modelo.Autor;
import java.util.List;

public interface InterfaceRepositoryAutor {
    
    public List<Autor> list();
    public Autor search(long id);
    public void create(Autor autor);
    public void update(Autor autor);
    public void delete(long id);
    
}
