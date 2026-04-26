/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control1;

import servicio.SistemaVotacion;
import servicio.RegistroVotantes;
import servicio.ArchivoVotos;
import modelo.Administrador;
import modelo.Votante;

import java.util.Scanner;

public class ControlSistema {

    private SistemaVotacion sistema;
    private RegistroVotantes registro;
    private Scanner sc;

    private Administrador admin = new Administrador("2200451306", "2004");

    public ControlSistema() {
        sistema = new SistemaVotacion();
        registro = new RegistroVotantes();
        sc = new Scanner(System.in);

        cargarCandidatosBase();
    }

    private void cargarCandidatosBase() {
        sistema.agregarCandidato("Ana");
        sistema.agregarCandidato("Luis");
        sistema.agregarCandidato("Carlos");
    }

    public void iniciar() {

        while (true) {

            System.out.println("==============================");
            System.out.println("SISTEMA DE VOTACION");
            System.out.println("==============================");
            System.out.println("1. Votar");
            System.out.println("2. Administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione: ");

            int opcion;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Ingrese un número válido");
                continue;
            }

            switch (opcion) {

                case 1:
                    loginVotante();
                    break;

                case 2:
                    loginAdmin();
                    break;

                case 3:
                    System.out.println(" Saliendo del Sistema");
                    return;

                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }

    private void loginVotante() {

        System.out.println("--- LOGIN VOTANTE ---");

        System.out.print("Cédula: ");
        String cedula = sc.nextLine();

        if (!validarCedula(cedula)) {
            System.out.println(" Cédula inválida (debe tener 10 dígitos)");
            return;
        }

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        registro.registrar(cedula, clave);

        Votante v = registro.login(cedula, clave);

        if (v == null) {
            System.out.println("❌ Credenciales incorrectas");
            return;
        }

        if (v.yaVoto()) {
            System.out.println("❌ Ya votaste");
            return;
        }

        flujoVotacion(v);
    }


    private void flujoVotacion(Votante v) {

        System.out.println("\n--- VOTACIÓN ---");

        sistema.mostrarCandidatos();

        int voto;

        try {
            System.out.print("Seleccione candidato: ");
            voto = Integer.parseInt(sc.nextLine()) - 1;
        } catch (Exception e) {
            System.out.println("❌ Número inválido");
            return;
        }

        System.out.print("Confirmar voto (S/N): ");
        String conf = sc.nextLine();

        if (!conf.equalsIgnoreCase("S")) {
            System.out.println("❌ Voto cancelado");
            return;
        }

        if (sistema.votar(voto)) {

            v.marcarVoto();

            ArchivoVotos.guardarVoto(
                    v.getCedula(),
                    sistema.getNombreCandidato(voto)
            );

            System.out.println("✔ Voto registrado");
        } else {
            System.out.println("❌ Candidato inválido");
        }
    }


    private void loginAdmin() {

        System.out.println("\n--- LOGIN ADMIN ---");

        System.out.print("Cédula: ");
        String cedula = sc.nextLine();

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        if (!admin.login(cedula, clave)) {
            System.out.println("❌ Acceso denegado");
            return;
        }

        panelAdmin();
    }

    
    private void panelAdmin() {

        while (true) {

            System.out.println("\n===== ADMIN =====");
            System.out.println("1. Ver resultados");
            System.out.println("2. Agregar candidato");
            System.out.println("3. Reiniciar sistema");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int op;

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Opción inválida");
                continue;
            }

            switch (op) {

                case 1:
                    sistema.mostrarCandidatos();
                    sistema.mostrarGanador();
                    sistema.mostrarRankingConBarras();
                    ArchivoVotos.leerVotos();
                    break;

                case 2:
                    System.out.print("Nombre candidato: ");
                    String nombre = sc.nextLine();
                    sistema.agregarCandidato(nombre);
                    System.out.println("Agregado");
                    break;

                case 3:
                    System.out.print("¿Seguro que quieres borrar los votos? (S/N): ");
                    String conf = sc.nextLine();

                    if (conf.equalsIgnoreCase("S")) {

                        ArchivoVotos.reiniciarArchivo();

                        sistema = new SistemaVotacion();
                        registro = new RegistroVotantes();
                        cargarCandidatosBase();

                        System.out.println("✔ Sistema reiniciado completamente");

                    } else {
                        System.out.println("❌ Cancelado");
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }

    
    private boolean validarCedula(String cedula) {
        return cedula.matches("\\d{10}");
    }
}
