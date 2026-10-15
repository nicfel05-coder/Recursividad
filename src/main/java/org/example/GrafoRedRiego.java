package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoRedRiego {
    private Map<String, List<String>> adyacencia = new HashMap<>();

    public void conectarPuntos(String origen, String destino) {
        adyacencia.putIfAbsent(origen, new ArrayList<>());
        adyacencia.putIfAbsent(destino, new ArrayList<>());
        adyacencia.get(origen).add(destino);
    }

    public void mostrarRed() {
        System.out.println("\n--- MAPA DE LA RED HÍDRICA (ESTRUCTURA DE GRAFO) ---");
        for (String punto : adyacencia.keySet()) {
            System.out.println("Punto [" + punto + "] distribuye agua hacia -> " + adyacencia.get(punto));
        }
    }
}