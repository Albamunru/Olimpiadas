package com.example.tarea_unidad3;

import android.widget.ImageView;

public class Pais {

    private String nombrePais;
    private int fotoPais;
    private int numeroAlumnos;
    private String informaciones;
    private String idioma;
    public Pais(String nombrePais, int fotoPais, int numeroAlumnos, String informaciones, String idioma) {
        this.nombrePais = nombrePais;
        this.fotoPais = fotoPais;
        this.numeroAlumnos = numeroAlumnos;
        this.informaciones = informaciones;
        this.idioma = idioma;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public int getFotoPais() {
        return fotoPais;
    }

    public void setFotoPais(int fotoPais) {
        this.fotoPais = fotoPais;
    }

    public int getNumeroAlumnos() {
        return numeroAlumnos;
    }

    public void setNumeroAlumnos(int numeroAlumnos) {
        this.numeroAlumnos = numeroAlumnos;
    }

    public String getInformaciones() {
        return informaciones;
    }

    public void setInformaciones(String informaciones) {
        this.informaciones = informaciones;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "nombrePais='" + nombrePais + '\'' +
                ", fotoPais=" + fotoPais +
                ", numeroAlumnos=" + numeroAlumnos +
                '}';
    }
}
