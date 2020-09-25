package com.example.moviles

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(),
        OnMapReadyCallback,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraIdleListener,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    private lateinit var mMap: GoogleMap
    var hayPermisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermisos()


        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        establecerConfiguracionMap(mMap)
        val cci = LatLng(-0.17867, -78.485594)
        val zoom = 17f
    val poliLineaUno = googleMap.addPolyline(
            PolylineOptions()
                    .clickable(true)
                    .add(
                    LatLng(-0.178866, -78.484525),
                    LatLng(-0.178614, -78.485920),
                    LatLng(-0.176179, -78.485373),
                    LatLng(-0.176404, -78.483522)
            )
    )

        val poligonoUno = googleMap.addPolygon(
                PolygonOptions()
                        .clickable(true)
                        .add(
                        LatLng(-0.179513, -78.485113),
                        LatLng(-0.179642, -78.484775),
                        LatLng(-0.179092, -78.484566),
                        LatLng(-0.178953, -78.484931)
                )
        )
        poligonoUno.fillColor = -0xc771c4

        establecerListeners(mMap)
        agregarMarcador(cci, "CCI")
        moverCamaraConZoom(cci, zoom)
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun moverCamaraConZoom(larLng: LatLng, zoom: Float){
        mMap.moveCamera(
                CameraUpdateFactory
                        .newLatLngZoom(larLng, zoom)
        )
    }

    fun agregarMarcador(larLng: LatLng, title: String){
        mMap.addMarker(
            MarkerOptions()
                    .position(larLng)
                    .title(title)

        )
    }


    fun establecerConfiguracionMap(mapa: GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                    .checkSelfPermission(
                            contexto,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

            if (tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                        this.applicationContext,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos){
            Log.i("Permisos", "Hay permisos")
            hayPermisos = true
        }else{
            Log.i("Permisos", "No Hay permisos")
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    1
            )

        }
    }

    fun establecerListeners(mapa: GoogleMap){
        with(mapa){
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("Mapa", "onCameraMoveStarted: ${p0.toString()}")
    }

    override fun onCameraMove() {
        Log.i("Mapa", "onCameraMove")
    }

    override fun onCameraIdle() {
        Log.i("Mapa", "onCameraIdle")
    }

    override fun onPolylineClick(p0: Polyline?) {
        Log.i("Mapa", "onPolylineClick ${p0.toString()}")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("Mapa", "onPolygonClick: ${p0.toString()}")
    }
}