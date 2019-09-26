package com.chrismerino.pupusaapp_zero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import sv.edu.bitlab.pupusap.Models.Orden

class ContenedorDetalleActivity : AppCompatActivity() {

    var orden = Orden()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contenedor_detalle)
        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show()
        val params = this.intent.extras
        orden = params!!.getParcelable<Orden>(ORDEN)!!
        Log.d("ACTIVITY", "onCreate()")
        addFragment()
    }


    fun addFragment(){
        val fragment = DetalleOrdenFragment.newInstance(this.orden)
        val builder = supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_detalle_ordenxml, fragment, FRAGMENT_TAG)
        builder.commit()
    }




    companion object{
        const val FRAGMENT_TAG = "FRAGMENT_TAG"
    }


}
