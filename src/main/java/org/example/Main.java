package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================================================");
        System.out.println("   SISTEMA RECURSIVO Y GRAFOS DE MONITOREO HÍDRICO - VEREDAS DEL RÍO");
        System.out.println("=====================================================================\n");
        GrafoRedRiego redHidrica = new GrafoRedRiego();
        System.out.println("--- 1. CONFIGURACIÓN DE LA RED HÍDRICA  ---");
        System.out.print("¿Cuántas conexiones hídricas desea registrar ?: ");
        int numConexiones = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numConexiones; i++) {
            System.out.println("\nConexión #" + (i + 1) + ":");
            System.out.print("  Ingrese Punto Origen : ");
            String origen = scanner.nextLine();
            System.out.print("  Ingrese Punto Destino : ");
            String destino = scanner.nextLine();
            redHidrica.conectarPuntos(origen, destino);
        }
        redHidrica.mostrarRed();

        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("--- 2. EVALUACIÓN RECURSIVA DE PÉRDIDAS DE AGUA ---");
        System.out.print("Ingrese la cantidad de tramos del canal a evaluar: ");
        int cantTramos = scanner.nextInt();
        scanner.nextLine();

        TramoCanal[] tramos = new TramoCanal[cantTramos];

        for (int i = 0; i < cantTramos; i++) {
            System.out.println("\nTramo #" + (i + 1) + ":");
            System.out.print("  Nombre del tramo/sector: ");
            String nombre = scanner.nextLine();
            System.out.print("  Caudal de entrada (L/s): ");
            double cEntrada = scanner.nextDouble();
            System.out.print("  Caudal de salida (L/s): ");
            double cSalida = scanner.nextDouble();
            scanner.nextLine();

            tramos[i] = new TramoCanal(nombre, cEntrada, cSalida);
        }

        double perdidaTotal = calcularPerdidaRecursiva(tramos, 0);

        System.out.println("\n=====================================================================");
        System.out.println("Pérdida total acumulada de agua en los canales: " + perdidaTotal + " L/s");

        System.out.print("Ingrese el umbral máximo de pérdida permitido (L/s): ");
        double umbral = scanner.nextDouble();

        if (perdidaTotal > umbral) {
            System.out.println(" [ALERTA HÍDRICA CRÍTICA]: La pérdida de " + perdidaTotal
                    + " L/s supera el umbral (" + umbral + " L/s). Requiere inspección.");
        } else {
            System.out.println(" [ESTADO OPTIMO]: Las filtraciones están dentro del rango tolerable.");
        }

        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("--- 3. EVALUACIÓN RECURSIVA DE RIESGO DE PLAGAS Y ESTRÉS HÍDRICO ---");
        System.out.print("Ingrese la cantidad de días de lectura a evaluar: ");
        int dias = scanner.nextInt();

        double[] humedadSuelo = new double[dias];
        double[] evapotranspiracion = new double[dias];

        for (int i = 0; i < dias; i++) {
            System.out.println("\nLectura Día #" + (i + 1) + ":");
            System.out.print("  Humedad del suelo (%): ");
            humedadSuelo[i] = scanner.nextDouble();
            System.out.print("  Evapotranspiración (mm/día): ");
            evapotranspiracion[i] = scanner.nextDouble();
        }

        double indiceRiesgo = evaluarRiesgoAgricolaRecursivo(humedadSuelo, evapotranspiracion, 0);

        System.out.println("\n=====================================================================");
        System.out.println("Índice de riesgo agrícola acumulado: " + String.format("%.2f", indiceRiesgo) + " pts");

        if (indiceRiesgo >= 10.0) {
            System.out.println(" [ALERTA AGRÍCOLA CRÍTICA]: Alto riesgo de plagas o estrés hídrico severo.");
        } else {
            System.out.println(" [ESTADO NORMAL]: Condiciones agrícolas estables.");
        }
        System.out.println("=====================================================================");
        scanner.close();
    }

    public static double calcularPerdidaRecursiva(TramoCanal[] tramos, int indice) {
        if (indice >= tramos.length) {
            return 0.0;
        }
        return tramos[indice].calcularPerdida() + calcularPerdidaRecursiva(tramos, indice + 1);
    }
    public static double evaluarRiesgoAgricolaRecursivo(double[] humedad, double[] et0, int dia) {
        if (dia >= humedad.length) {
            return 0.0;
        }

        double factorDia = 0.0;
        if (humedad[dia] < 25.0) factorDia += 2.5;
        if (et0[dia] > 5.0)     factorDia += 1.5;

        return factorDia + evaluarRiesgoAgricolaRecursivo(humedad, et0, dia + 1);
    }
}