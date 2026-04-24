/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import servicio.SistemaVotacion;
import servicio.RegistroVotantes;
import servicio.ArchivoVotos;

import java.util.Scanner;

public class Main {

    // ATRIBUTOS
    private static int intentosFallidos = 0;
    private static final int MAX_INTENTOS = 3;

    //  BLOQUEO TEMPORAL
    private static long tiempoBloqueo = 0;
    private static final int TIEMPO_BLOQUEO = 30000;

    public static boolean validarCedula(String cedula) {
        return cedula.matches("\\d{10}");
    }

    public static boolean estaBloqueado() {

        if (tiempoBloqueo == 0) return false;

        long ahora = System.currentTimeMillis();

        if (ahora - tiempoBloqueo >= TIEMPO_BLOQUEO) {
            tiempoBloqueo = 0;
            intentosFallidos = 0;
            System.out.println("Sistema desbloqueado");
            return false;
        }

        long restante = (TIEMPO_BLOQUEO - (ahora - tiempoBloqueo)) / 1000;
        System.out.println(" Bloqueado. Espere " + restante + " segundos");
        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SistemaVotacion sistema = new SistemaVotacion();
        RegistroVotantes registro = new RegistroVotantes();

        
        sistema.agregarCandidato("Ana");
        sistema.agregarCandidato("Luis");
        sistema.agregarCandidato("Carlos");

        int opcion = 0;

        do {
            System.out.println("\n==============================");
            System.out.println("🗳️ SISTEMA DE VOTACIÓN");
            System.out.println("==============================");
            System.out.println("1. Votar");
            System.out.println("2. Ver resultados");
            System.out.println("3. Salir");

            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Ingrese un número válido");
                continue;
            }

            switch (opcion) {

                case 1:

                    if (estaBloqueado()) break;

                    String cedula = null;

                    while (true) {

                        System.out.print("Ingrese cédula (10 dígitos): ");
                        cedula = sc.nextLine();

                        if (validarCedula(cedula)) {
                            intentosFallidos = 0;
                            break;
                        }

                        intentosFallidos++;
                        System.out.println(" Cédula inválida. Intentos: "
                                + intentosFallidos + "/" + MAX_INTENTOS);

                        if (intentosFallidos >= MAX_INTENTOS) {
                            tiempoBloqueo = System.currentTimeMillis();
                            System.out.println(" Bloqueado por 30 segundos");
                            cedula = null;
                            break;
                        }
                    }

                    if (cedula == null) break;

                    registro.registrar(cedula);

                    if (!registro.puedeVotar(cedula)) {
                        System.out.println(" Ya votó ");
                        break;
                    }

                    sistema.mostrarCandidatos();

                    int voto;

                    while (true) {
                        System.out.print("Seleccione candidato: ");
                        try {
                            voto = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println(" Ingrese número válido");
                        }
                    }

                    if (sistema.votar(voto)) {

                        registro.marcarVoto(cedula);

                        // 🧾 GUARDAR EN TXT
                        String candidato = sistema.getNombreCandidato(voto);
                        ArchivoVotos.guardarVoto(cedula, candidato);

                        System.out.println(" Voto registrado y guardado");
                    } else {
                        System.out.println(" Opción inválida");
                    }

                    break;

                case 2:

                    sistema.mostrarCandidatos();
                    sistema.mostrarGanador();
                    sistema.mostrarRankingConBarras();

                    break;

                case 3:

                    System.out.println(" Saliendo del sistema");
                    break;

                default:

                    System.out.println(" Opción inválida");
            }

        } while (opcion != 3);

        sc.close();
    }
}