/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ArchivoVotos {

    public static void guardarVoto(String cedula, String candidato) {
        try (FileWriter fw = new FileWriter("votos.txt", true); PrintWriter pw = new PrintWriter(fw)) {

            pw.println("Cedula: " + cedula + " | Voto: " + candidato);

        } catch (Exception e) {
            System.out.println("Error al guardar voto");
        }
    }

    public static void leerVotos() {
        try {
            java.util.Scanner sc = new java.util.Scanner(new java.io.File("votos.txt"));

            System.out.println("\n📄 VOTOS REGISTRADOS:");
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo");
        }
    }


    public static void reiniciarArchivo() {
        try (PrintWriter pw = new PrintWriter("votos.txt")) {
            pw.print(""); 
            System.out.println(" Archivo de votos reiniciado");
        } catch (Exception e) {
            System.out.println(" Error al reiniciar archivo");
        }
    }
}
