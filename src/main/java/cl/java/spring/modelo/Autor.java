/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.java.spring.modelo;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {
    
    
    private Long id;
    @Size(min=9, max=10)
    @NotNull
    private String rut;
    @Size(min =3, max = 50)
    @NotNull
    private String nombre;
    @Size(min =3, max = 30)
    @NotNull
    private String apellidoP;
    @Size(min =3, max = 30)
    private String apellidoM;
    @Size(min =3, max = 30)
    @NotNull
    private String nacionalidad;
    @Past
    private LocalDate fechaNacimiento;
    
    private LocalDate fechaDefuncion;
    
}
