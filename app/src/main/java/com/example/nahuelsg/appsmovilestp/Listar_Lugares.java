package com.example.nahuelsg.appsmovilestp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;

public class Listar_Lugares extends AppCompatActivity {

    private EstacionamientoAdapter adapterEst;
    private ListView listaEst;
    private Estacionamiento[] Estacionamientos;
    private Button verMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__lugares);

        llenarEstacionamientos();
        listaEst = (ListView) findViewById(R.id.listLugares);
        adapterEst = new EstacionamientoAdapter(this, Arrays.asList(Estacionamientos));
        listaEst.setAdapter(adapterEst);
        setTitle("Listados de Estacionamientos");
    }




    public void verMapa(int position){

        // TODO: Completar accion ver Mapa
    }

    public void reservarEst(int position){


        // TODO: Completar accion reservar
    }

    private void llenarEstacionamientos(){
        Estacionamientos = new Estacionamiento[3];
        Estacionamientos[0] = new Estacionamiento();
        Estacionamientos[0].setDireccionEstacionamiento("DIRECCIÓN: En Algun Lugar");
        Estacionamientos[0].setEsPorDia(true);
        Estacionamientos[0].setEsPorHora(false);
        Estacionamientos[0].setNombreEstacionamiento("NOMBRE: El Estacionamiento 0");
        Estacionamientos[0].setTarifaEstacionamiento("TARIFA: Te cobramos dos huevos");
        Estacionamientos[0].setPosicionEstacionamiento(new LatLng(-32.029369,-61.224513));

        Estacionamientos[1] = new Estacionamiento();
        Estacionamientos[1].setDireccionEstacionamiento("DIRECCIÓN: En Algun Lugar");
        Estacionamientos[1].setEsPorDia(true);
        Estacionamientos[1].setEsPorHora(false);
        Estacionamientos[1].setNombreEstacionamiento("NOMBRE: El Estacionamiento 1");
        Estacionamientos[1].setTarifaEstacionamiento("TARIFA: Te cobramos dos huevos");
        Estacionamientos[1].setPosicionEstacionamiento(new LatLng(-32.029379,-61.224516));


        Estacionamientos[2] = new Estacionamiento();
        Estacionamientos[2].setDireccionEstacionamiento("DIRECCIÓN: En Algun Lugar");
        Estacionamientos[2].setEsPorDia(true);
        Estacionamientos[2].setEsPorHora(false);
        Estacionamientos[2].setNombreEstacionamiento("NOMBRE: El Estacionamiento 2");
        Estacionamientos[2].setTarifaEstacionamiento("TARIFA: Te cobramos dos huevos");
        Estacionamientos[2].setPosicionEstacionamiento(new LatLng(-32.029469,-61.224613));


    }
}

