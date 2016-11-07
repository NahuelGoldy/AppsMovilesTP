package com.example.nahuelsg.appsmovilestp;


import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity
        extends FragmentActivity
        implements OnMapReadyCallback, View.OnClickListener, View.OnLongClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private TextView tvCords;
    private ImageButton imgBoton;
    public LatLng posActual = new LatLng(1,1);
    private GoogleApiClient googleApiClient;
    private LatLng terminalGalvez = new LatLng(-32.030610, -61.223729);
    private LatLng terminalStf = new LatLng(-31.613249, -60.700407);
    private LatLng muniGalvez = new LatLng(-32.029369, -61.224513);
    private LatLng bancoNacionGalvez = new LatLng(-32.030296, -61.222229);
    private LatLng utn = new LatLng(-31.616946, -60.67308);
    private Marker  posActualMarker;
    FloatingActionButton fab;
    private double[] latLngAux = {0.0, 0.0};
    private boolean flagMoverCorrentPosition = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addComponentes();
        addGoogleApi();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void addLiseners() {
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);

    }

    /**
     * Para agregar los botones o toda esa fruta... En proceso
     */
    private void addComponentes() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    /**
     * Agrega la Api de Google
     */
    private void addGoogleApi() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addLiseners();
        addLugaresInteres();
        //Si el valor no es nulo, entonces el intent viene de "Ver en Mapa" de Listar_Lugares
        //El valor que trae el intent es un double[2], en orden Latitude-Longitude
        if (((getIntent()).getDoubleArrayExtra("latLng") != null)) {

            if (!(((getIntent()).getDoubleArrayExtra("latLng")).equals(latLngAux))) {
                double[] vacio = {0.0, 0.0};
                latLngAux = (getIntent()).getDoubleArrayExtra("latLng");
                LatLng latLngToMove = new LatLng(latLngAux[0], latLngAux[1]);

                //Limpio el map del intent y el latLngAux- asi solo tiene valor cuando viene desde Listar_Lugares
                latLngAux = vacio;
                getIntent().putExtra("latLng", vacio);
                moveMap(latLngToMove);
            } else {
                flagMoverCorrentPosition = true;
                /**Habilita a que se mueva a la posicion actual del usuario
                 * dado que si muevo el getCorrentPosition() a otro lugar que no sea en el metodo
                 * onConnect, no funciona
                 */
            }
        } else {
            flagMoverCorrentPosition = true;
        }
    }

    /**
     * Precargo lugares de interes
     */
    private void addLugaresInteres() {
        //Acomodar para que no serpita, solo para test...
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(terminalGalvez) //Pongo el lugar
                .title("Terminal de la Ciudad de Galvez")) //Le meto titulo
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mMap.addMarker(new MarkerOptions()
                .position(terminalStf) //Pongo el lugar
                .title("Terminal de la Ciudad de Santa Fe")) //Le meto titulo
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        mMap.addMarker(new MarkerOptions()
                .position(muniGalvez) //Pongo el lugar
                .title("Municipalidad de la Ciudad de Galvez")) //Le meto titulo
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mMap.addMarker(new MarkerOptions()
                .position(bancoNacionGalvez) //Pongo el lugar
                .title("Banco Nacion")) //Le meto titulo
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMap.addMarker(new MarkerOptions()
                .position(utn) //Pongo el lugar
                .title("Tecnologica")) //Le meto titulo
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

    }

    //Agarrar donde esta
    private void getCurrentLocation() {
/**
 * Esta fruta la tiro aumatica android para los permisos de Location
 * ------------------------------------------------------------------------------------------------
 */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
/**
 *-----------------------------------------------------------------------------------------------
 */
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            posActual = new LatLng(location.getLatitude(),location.getLongitude());  //Guardo la pos en la global

                //if == null, entonces nunca se creo. Else, existe, solo se debe actualizar las cord
            if(posActualMarker == null) {
                posActualMarker = mMap.addMarker(new MarkerOptions()
                        .position(posActual) //Pongo el lugar
                        .title("Su posicion actual"));//Le meto titulo
                posActualMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_posactual));
            }else {
                posActualMarker.setPosition(posActual);
            }

                //Si el flag es false, la activity fue llamada desde la lista de estacionamientos => no debe moverse la camara
            if (flagMoverCorrentPosition) {
                moveMap(posActual);
            }
        }
    }

    /**
     * Aunque ya existe el addMarker, esto me parece lo hace mas rapido mas legible
     *
     * @param latLng Posicion
     * @param title  Titulo
     * @param idIcon id del icono que va a tener
     */
    private void addMarker(LatLng latLng, String title, int idIcon) {
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //Pongo el lugar
                .title(title))//Le meto titulo
                .setIcon(BitmapDescriptorFactory.fromResource(idIcon));
    }

    //Funcion mover el mapa, resive LatLng (var de android que tiene latitud y longitud)
    private void moveMap(LatLng latLng) {
        String msg = latLng.latitude + ", " + latLng.longitude;

        //Muevo la camara
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Le doy zoom
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17f));

        //Faltaria mostrar el nombre del Marker- Cuando Martin me diga como usar la extencion de la API

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == fab.getId()) {
            Intent intent = new Intent(this, Listar_Lugares.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onLongClick(View v) {

        return false;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        addMarker(latLng, "Agregado por ClickLargo", R.drawable.marker_estacionamiento);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getPosition().equals(utn)) {
            Toast.makeText(this, "Funciona MarkerClick", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    /**
     * Para Arrancar y parar la API
     * -------------------------------------------------------------------------------------------------
     */
    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
/**
 * -------------------------------------------------------------------------------------------------
 */
}
