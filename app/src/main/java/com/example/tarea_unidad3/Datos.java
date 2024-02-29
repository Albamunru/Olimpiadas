package com.example.tarea_unidad3;

public class Datos {

   private String temperatura;
    private String humedad;
    private String  calidadaire;
    private String gasespeligrosos;
    private String fecha;
    private String hora;


    public Datos(String temperatura, String humedad, String calidadaire, String gasespeligrosos, String fecha, String hora) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.calidadaire = calidadaire;
        this.gasespeligrosos = gasespeligrosos;
        this.fecha = fecha;
        this.hora = hora;
    }


    public String getTemperatura() {
        return temperatura;
    }

    public String getHumedad() {
        return humedad;
    }

    public String getCalidadaire() {
        return calidadaire;
    }

    public String getGasespeligrosos() {
        return gasespeligrosos;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "Datos{" +
                "temperatura='" + temperatura + '\'' +
                ", humedad='" + humedad + '\'' +
                ", calidadaire='" + calidadaire + '\'' +
                ", gasespeligrosos='" + gasespeligrosos + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
