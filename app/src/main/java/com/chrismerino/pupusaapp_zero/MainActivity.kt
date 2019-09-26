package com.chrismerino.pupusaapp_zero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import com.chrismerino.pupusaapp_zero.DetalleOrdenFragment.Companion.CONTADOR_ARROZ
import com.chrismerino.pupusaapp_zero.DetalleOrdenFragment.Companion.CONTADOR_MAIZ

class MainActivity : AppCompatActivity() {


    // DECLARANDO CONTADORES

    var contadoresMaiz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )

    var contadoresArroz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )

    val pupusaStringResources = hashMapOf(
        QUESO to R.string.pupusa_queso,
        FRIJOLES to R.string.frijol_con_queso,
        REVUELTAS to R.string.revueltas
    )




    // BOTON ENVIAR
    var botonEnviar: Button? = null





    // BOTONES MAIZ - ARROZ
    var botonesMaiz = hashMapOf<String, Button>()
    var botonesArroz = hashMapOf<String, Button>()

    var quesoIzquierda: Button? = null
    var frijolIzquierda: Button? = null
    var revueltaIzquierda: Button? = null
    var quesoDerecha: Button? = null
    var frijolDerecha: Button? = null
    var revueltasDerecha: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SET CONTENT VIEW PARA MAIN ACTIVITY
        setContentView(R.layout.activity_main)



        botonEnviar = findViewById(R.id.botonEnviar)
        botonEnviar!!.setOnClickListener {

            // MANDAR A LLAMAR AL FRAGMENT
            confirmarOrden()
        }


        // INICIALIZANDO COMPONENTES DE UI

        quesoIzquierda = findViewById(R.id.quesoIzquierda)
        frijolIzquierda = findViewById(R.id.frijolIzquierdaMaiz)
        revueltaIzquierda = findViewById(R.id.revueltasIzquierda)

        botonesMaiz = hashMapOf(
            QUESO to quesoIzquierda!!,
            FRIJOLES to frijolIzquierda!!,
            REVUELTAS to revueltaIzquierda!!
        )

        quesoIzquierda!!.setOnClickListener { addMaiz(QUESO) }
        frijolIzquierda!!.setOnClickListener { addMaiz(FRIJOLES) }
        revueltaIzquierda!!.setOnClickListener { addMaiz(REVUELTAS) }


        quesoDerecha = findViewById(R.id.quesoDerecha)
        frijolDerecha = findViewById(R.id.frijolIDerechaArroz)
        revueltasDerecha = findViewById(R.id.revueltasDerecha)

        botonesArroz = hashMapOf(
            QUESO to quesoDerecha!!,
            FRIJOLES to frijolDerecha!!,
            REVUELTAS to revueltasDerecha!!
        )

        quesoDerecha!!.setOnClickListener { addArroz(QUESO) }
        frijolDerecha!!.setOnClickListener { addArroz(FRIJOLES) }
        revueltasDerecha!!.setOnClickListener { addArroz(REVUELTAS) }


        mostrarContadores()

    }

    fun mostrarContadores(){
        for ((key,value) in contadoresMaiz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesMaiz[key]!!.text = text
        }


        for ((key,value) in contadoresArroz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesArroz[key]!!.text = text
        }
    }


    fun addMaiz(relleno: String) {
        contadoresMaiz[relleno] = contadoresMaiz[relleno]!! + 1
        val contador = contadoresMaiz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesMaiz[relleno]!!.text = text
    }
    fun addArroz(relleno: String) {
        contadoresArroz[relleno] = contadoresArroz[relleno]!! + 1
        val contador = contadoresArroz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesArroz[relleno]!!.text = text
    }


    private fun confirmarOrden() {
        val intent = Intent(this, ContenedorDetalleActivity::class.java)
        val arroz = arrayListOf<Int>(
            contadoresArroz[QUESO]!!,
            contadoresArroz[FRIJOLES]!!,
            contadoresArroz[REVUELTAS]!!)
        val maiz = arrayListOf<Int>(
            contadoresMaiz[QUESO]!!,
            contadoresMaiz[FRIJOLES]!!,
            contadoresMaiz[REVUELTAS]!!)


        intent.putExtra(CONTADOR_ARROZ, arroz)
        intent.putExtra(CONTADOR_MAIZ, maiz)

        this.startActivity(intent)

    }




    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

    companion object{
        const val ORDEN = "ORDEN"
        const val QUESO = "QUESO"
        const val FRIJOLES = "FRIJOLES"
        const val REVUELTAS = "REVUELTAS"
        const val MAIZ = "MAIZ"
        const val ARROZ = "ARROZ"
    }

}
