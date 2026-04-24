/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Candidato {

     private String nombre;
    private int votos;

    public Candidato(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
    }

    public void votar() {
        votos++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVotos() {
        return votos;
    }

    public double getPorcentaje(int totalVotos) {
        if (totalVotos == 0) return 0;
        return (votos * 100.0) / totalVotos;
    }

    public String mostrar(int totalVotos) {
        return nombre +
                " | Votos: " + votos +
                " | " + String.format("%.2f", getPorcentaje(totalVotos)) + "%";
    }
}
