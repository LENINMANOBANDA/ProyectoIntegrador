/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import modelo.Votante;
import java.util.HashMap;

public class RegistroVotantes {

    private HashMap<String, Votante> votantes = new HashMap<>();

    public boolean registrar(String cedula) {

        if (!votantes.containsKey(cedula)) {
            votantes.put(cedula, new Votante(cedula));
        }

        return true;
    }

    public boolean puedeVotar(String cedula) {

        Votante v = votantes.get(cedula);

        if (v == null) return false;

        return !v.yaVoto();
    }

    public void marcarVoto(String cedula) {

        Votante v = votantes.get(cedula);

        if (v != null) {
            v.marcarVoto();
        }
    }
}
