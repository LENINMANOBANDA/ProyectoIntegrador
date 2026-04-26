/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Administrador extends Persona {

    private String clave;

    public Administrador(String cedula, String clave) {
        super(cedula);
        this.clave = clave;
    }

    public boolean login(String cedula, String clave) {
        return this.cedula.equals(cedula) && this.clave.equals(clave);
    }
}
