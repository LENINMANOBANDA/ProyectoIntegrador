/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import modelo.Votante;
import java.util.HashMap;

public class RegistroVotantes {

    private HashMap<String, Votante> votantes = new HashMap<>();

    public void registrar(String cedula, String clave) {
        votantes.putIfAbsent(cedula, new Votante(cedula, clave));
    }

    public Votante login(String cedula, String clave) {

        Votante v = votantes.get(cedula);

        if (v != null && v.login(cedula, clave)) {
            return v;
        }

        return null;
    }
}
