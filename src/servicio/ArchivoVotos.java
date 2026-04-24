/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ArchivoVotos {

    private static final String ARCHIVO = "votos.txt";

    public static void guardarVoto(String cedula, String candidato) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {

            pw.println("Cédula: " + cedula + " | Candidato: " + candidato);

        } catch (IOException e) {
            System.out.println(" Error al guardar voto: " + e.getMessage());
        }
    }
}