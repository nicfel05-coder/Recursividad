package org.example;

public class TramoCanal {
    private String nombreTramo;
    private double caudalEntrada;
    private double caudalSalida;

    public TramoCanal(String nombreTramo, double caudalEntrada, double caudalSalida) {
        this.nombreTramo = nombreTramo;
        this.caudalEntrada = caudalEntrada;
        this.caudalSalida = caudalSalida;
    }

    public String getNombreTramo() {
        return nombreTramo;
    }

    public double getCaudalEntrada() {
        return caudalEntrada;
    }

    public double getCaudalSalida() {
        return caudalSalida;
    }

    public double calcularPerdida() {
        return caudalEntrada - caudalSalida;
    }
}