/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import modelo.Candidato;
import java.util.ArrayList;

public class SistemaVotacion {

    private ArrayList<Candidato> candidatos = new ArrayList<>();

    public void agregarCandidato(String nombre) {
        candidatos.add(new Candidato(nombre));
    }

    public boolean votar(int index) {

        if (index >= 0 && index < candidatos.size()) {
            candidatos.get(index).votar();
            return true;
        }

        return false;
    }

    public String getNombreCandidato(int index) {

        if (index >= 0 && index < candidatos.size()) {
            return candidatos.get(index).getNombre();
        }

        return "Desconocido";
    }

    public void mostrarCandidatos() {

        if (candidatos.isEmpty()) {
            System.out.println("❌ No hay candidatos registrados");
            return;
        }

        System.out.println("\n📋 CANDIDATOS:");

        int total = getTotalVotos();

        for (int i = 0; i < candidatos.size(); i++) {

            Candidato c = candidatos.get(i);

            double porcentaje = (total == 0)
                    ? 0
                    : (c.getVotos() * 100.0 / total);

            System.out.println((i + 1) + ". " + c.getNombre()
                    + " | Votos: " + c.getVotos()
                    + " | " + String.format("%.2f", porcentaje) + "%");
        }

        System.out.println("Total votos: " + total);
    }

    public int getTotalVotos() {

        int total = 0;

        for (Candidato c : candidatos) {
            total += c.getVotos();
        }

        return total;
    }

    public void mostrarGanador() {

        if (candidatos.isEmpty()) {
            System.out.println("❌ No hay datos");
            return;
        }

        Candidato ganador = candidatos.get(0);

        for (Candidato c : candidatos) {
            if (c.getVotos() > ganador.getVotos()) {
                ganador = c;
            }
        }

        System.out.println("\n🏆 GANADOR: " + ganador.getNombre()
                + " con " + ganador.getVotos() + " votos");
    }

    public void mostrarRankingConBarras() {

        if (candidatos.isEmpty()) {
            System.out.println("❌ No hay datos");
            return;
        }

        ArrayList<Candidato> ordenados = new ArrayList<>(candidatos);

        ordenados.sort((a, b) -> b.getVotos() - a.getVotos());

        int total = getTotalVotos();

        System.out.println("\n📊 RANKING");

        int pos = 1;

        for (Candidato c : ordenados) {

            double porcentaje = (total == 0)
                    ? 0
                    : (c.getVotos() * 100.0 / total);

            int barras = (int) (porcentaje / 5);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < barras; i++) {
                sb.append("█");
            }

            System.out.println(pos + ". " + c.getNombre()
                    + " | " + c.getVotos()
                    + " votos | " + String.format("%.2f", porcentaje) + "%"
                    + " | " + sb);

            pos++;
        }
    }
}
