/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cl.java.spring.repositorio;

import cl.java.spring.modelo.Libro;
import java.util.List;

/**
 *
 * @author Rafaelito
 */
public interface InterfaceRepositoryLibro {
    
    public List<Libro> list();
    public Libro search(long id);
    public void create(Libro libro);
    public void update(Libro libro);
    public void delete(long id);
    
}
