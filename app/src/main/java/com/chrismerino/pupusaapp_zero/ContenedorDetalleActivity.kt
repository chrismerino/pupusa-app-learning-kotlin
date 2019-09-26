package com.chrismerino.pupusaapp_zero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import sv.edu.bitlab.pupusap.Models.Orden

class ContenedorDetalleActivity : AppCompatActivity() {

    var orden = Orden()

    var maiz = arrayListOf<Int>()
    var arroz = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contenedor_detalle)

        val params = this.intent.extras

        arroz = params!!.getIntegerArrayList(CONTADOR_ARROZ)!!
        maiz = params!!.getIntegerArrayList(CONTADOR_ARROZ)!!

        Log.d("ACTIVITY", "onCreate()")
        addFragment()
    }


    fun addFragment(){
        val fragment = DetalleOrdenFragment.newInstance(arroz, maiz)
        val builder = supportFragmentManager
            .beginTransaction()
            .add(R.id.contenedor_de_fragment_orden, fragment, FRAGMENT_TAG)
        builder.commit()
    }




    companion object{
        const val FRAGMENT_TAG = "FRAGMENT_TAG"
        const val ORDEN = "ORDEN"
        const val CONTADOR_ARROZ = "ARROZ"
        const val CONTADOR_MAIZ = "MAIZ"
    }


}
