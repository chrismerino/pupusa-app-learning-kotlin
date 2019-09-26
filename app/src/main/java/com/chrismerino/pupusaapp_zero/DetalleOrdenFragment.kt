package com.chrismerino.pupusaapp_zero

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.DecimalFormat


class DetalleOrdenFragment : Fragment() {


    var arroz = arrayListOf<Int>()
    var maiz = arrayListOf<Int>()
    var inflater: LayoutInflater? = null


    val lineItemsIDs = arrayOf(
        arrayOf(R.id.lineItemDetail1, R.id.lineItemPrice1),
        arrayOf(R.id.lineItemDetail2, R.id.lineItemPrice2),
        arrayOf(R.id.lineItemDetail3, R.id.lineItemPrice3),
        arrayOf(R.id.lineItemDetail4, R.id.lineItemPrice4),
        arrayOf(R.id.lineItemDetail5, R.id.lineItemPrice5),
        arrayOf(R.id.lineItemDetail6, R.id.lineItemPrice6)
    )


    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            arroz = it.getIntegerArrayList(CONTADOR_ARROZ)!!
            maiz = it.getIntegerArrayList(CONTADOR_MAIZ)!!
        }

    }


    override fun onCreateView(
        // Inflate the layout for this fragment
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.inflater = inflater

        return inflater.inflate(R.layout.fragment_detalle_orden, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arr = arroz + maiz
        var total = 0.0f
        for ((index, contador) in arr.withIndex()) {
            val ids = lineItemsIDs[index]
            val detailTexview = activity!!.findViewById<TextView>(ids[0])
            val priceTextView = activity!!.findViewById<TextView>(ids[1])
            if (contador > 0) {
                val totalUnidad = contador * VALOR_PUPUSA
                val descripcion = getDescripcion(index)
                detailTexview.text = getString(
                    R.string.pupusa_line_item_description,
                    contador, descripcion
                )
                total += totalUnidad
                val precio = DecimalFormat("$#0.00").format(totalUnidad)
                priceTextView.text = precio
            } else {
                detailTexview.visibility = View.GONE
                priceTextView.visibility = View.GONE
            }
        }
        val totalPrecio = activity!!.findViewById<TextView>(R.id.lineItemPriceTotal)
        val precio = DecimalFormat("$#0.00").format(total)
        totalPrecio.text = precio


    }




    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


        // ESTA VACIO


    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    fun getDescripcion(index: Int): String {
        return when(index){
            QUESO -> "Queso de arroz"
            FRIJOLES -> "Frijol con queso de arroz"
            REVUELTAS -> "Revueltas de arroz"
            QUESO_MAIZ -> "Queso de maiz"
            FRIJOLES_MAIZ -> "Frijol con queso de maiz"
            REVUELTAS_MAIZ -> "Revueltas de maiz"
            else -> throw RuntimeException("Pupusa no soportada")
        }
    }






    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {


        @JvmStatic
        fun newInstance(arroz: ArrayList<Int>, maiz: ArrayList<Int>) =
            DetalleOrdenFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(CONTADOR_ARROZ, arroz)
                    putIntegerArrayList(CONTADOR_MAIZ, maiz)
                }
            }

        const val ORDEN = "ORDEN"
        const val QUESO = 0//3
        const val FRIJOLES = 1//4
        const val REVUELTAS = 2//5
        const val QUESO_MAIZ = 3//3
        const val FRIJOLES_MAIZ = 4//4
        const val REVUELTAS_MAIZ = 5//5
        const val CONTADOR_ARROZ = "ARROZ"
        const val CONTADOR_MAIZ = "MAIZ"
        const val VALOR_PUPUSA = 0.5F
        const val FRAGMENT_TAG = "FRAGMENT_TAG"



    }
}
