/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Votante {
   private String cedula;
    private boolean yaVoto;

    public Votante(String cedula) {
        this.cedula = cedula;
        this.yaVoto = false;
    }

    public String getCedula() {
        return cedula;
    }

    public boolean yaVoto() {
        return yaVoto;
    }

    public void marcarVoto() {
        yaVoto = true;
    }
}