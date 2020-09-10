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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

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
}