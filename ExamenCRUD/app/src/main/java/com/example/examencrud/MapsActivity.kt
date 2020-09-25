package com.example.examencrud

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.examencrud.httphandler.CancionHandler
import com.example.examencrud.htttpmodels.ArtistaHTTP
import com.example.examencrud.htttpmodels.CancionHTTP
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.map_maker.view.*

class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener,
    GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private var artistaID: Int = 0
    var hayPermisos = false
    var handler: CancionHandler = CancionHandler()
    private lateinit var canciones: ArrayList<CancionHTTP>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermisos()
        artistaID = intent.getIntExtra("id", 0)

        Log.i("Mapa ID", "$artistaID")

        if (artistaID == 0){
            finish()
        }
        canciones = handler.getAllByArtist(artistaID)



        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        establecerConfiguracionMap(mMap)

        val cci = LatLng(-0.17867, -78.485594)
        val zoom = 17f

        establecerListeners(mMap)

        canciones.forEach {cancion ->

            getMarkerIcon(this, cancion) {
                val posicion = LatLng(cancion.latitud.toDouble(), cancion.longitud.toDouble())
                val options = MarkerOptions()
                    .position(posicion)
                    .icon(it)
                    .title(cancion.titulo)
                    .snippet(cancion.website)
                googleMap.addMarker(options)
            }

        }

        agregarMarcador(cci, "CCI")
        moverCamaraConZoom(cci, zoom)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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

    fun establecerListeners(mapa: GoogleMap){
        with(mapa){
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
            setOnMarkerClickListener(this@MapsActivity)

        }
    }

    private fun getMarkerIcon(context: Context, cancion: CancionHTTP, listener: (BitmapDescriptor) -> Unit) {
        val markerView = View.inflate(context, R.layout.map_maker, null)
        Glide.with(context)
            .asBitmap()
            .load(cancion.imagePath)
            .into(object : SimpleTarget<Bitmap>() {


                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    markerView.map_container.setImageBitmap(resource)
                    listener.invoke(BitmapDescriptorFactory.fromBitmap(getBitmapFromView(markerView)))
                }
            })
    }

    private fun getBitmapFromView(view: View): Bitmap {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.draw(canvas)
        return bitmap
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

    override fun onMarkerClick(p0: Marker?): Boolean {

        val uriUrl: Uri = Uri.parse(p0?.snippet)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)


//        val artista: ArtistaHTTP = canciones[0].artista as ArtistaHTTP
//        var intent: Intent = Intent(
//            Intent.ACTION_SEARCH
//        )
//        intent.setPackage("com.google.android.youtube")
//        intent.putExtra(
//            "query",
//            "${p0?.title} - ${artista.nombre}"
//        )
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        startActivity(intent)

        return false
    }
}