package com.example.nahuelsg.appsmovilestp;

/**
 * Created by Puchoo on 21/10/2016.
 */

import com.google.android.gms.maps.model.LatLng;

public class Estacionamiento {
    private String nombreEstacionamiento;
    private LatLng posicionEstacionamiento;
    private String direccionEstacionamiento;
    private Boolean esPorDia;
    private Boolean esPorHora;
    private String tarifaEstacionamiento;


    public void Estacionamiento(){


    }

    /**
     * Getter y Setters
     */
    public String getNombreEstacionamiento() {
        return nombreEstacionamiento;
    }

    public void setNombreEstacionamiento(String nombreEstacionamiento) {
        this.nombreEstacionamiento = nombreEstacionamiento;
    }

    public LatLng getPosicionEstacionamiento() {  return posicionEstacionamiento;    }

    public void setPosicionEstacionamiento(LatLng posicionEstacionamiento) {
        this.posicionEstacionamiento = posicionEstacionamiento;
    }

    public String getDireccionEstacionamiento() {  return direccionEstacionamiento;    }

    public void setDireccionEstacionamiento(String direccionEstacionamiento) {
        this.direccionEstacionamiento = direccionEstacionamiento;
    }

    public Boolean getEsPorDia() { return esPorDia;    }

    public void setEsPorDia(Boolean esPorDia) {    this.esPorDia = esPorDia;    }

    public Boolean getEsPorHora() {        return esPorHora;    }

    public void setEsPorHora(Boolean esPorHora) { this.esPorHora = esPorHora;    }

    public String getTarifaEstacionamiento() {   return tarifaEstacionamiento;    }

    public void setTarifaEstacionamiento(String tarifaEstacionamiento) {
        this.tarifaEstacionamiento = tarifaEstacionamiento;
    }
}

