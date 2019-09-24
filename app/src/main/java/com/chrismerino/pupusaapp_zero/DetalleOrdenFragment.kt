package com.chrismerino.pupusaapp_zero

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.*
import java.lang.StringBuilder
import java.text.DecimalFormat


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class DetalleOrdenFragment : Fragment() {


    var param1 : String? = null
    var param2 : String? = null
    var arroz = arrayListOf<Int>()
    var maiz = arrayListOf<Int>()


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
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        // Inflate the layout for this fragment
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        val params = activity!!.intent.extras
//        arroz = params!!.getIntegerArrayList(CONTADOR_ARROZ)!!
//        maiz = params!!.getIntegerArrayList(CONTADOR_MAIZ)!!
//        displayDetalle()


        return inflater.inflate(R.layout.fragment_detalle_orden, container, false)
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



    fun displayDetalle() {
        val arr = arroz + maiz
        var total = 0.0f
        for((index, contador) in arr.withIndex()){
            val ids = lineItemsIDs[index]
            val detailTexview = activity!!.findViewById<TextView>(ids[0])
            val priceTextView= activity!!.findViewById<TextView>(ids[1])
            if(contador > 0){
                val totalUnidad = contador * DetalleOrdenFragment.VALOR_PUPUSA
                val descripcion = getDescripcion(index)
                detailTexview.text = getString(R.string.pupusa_line_item_description,
                    contador, descripcion)
                total += totalUnidad
                val precio = DecimalFormat("$#0.00").format(totalUnidad)
                priceTextView.text = precio
            } else{
                detailTexview.visibility = View.GONE
                priceTextView.visibility = View.GONE
            }
        }
        val totalPrecio = activity!!.findViewById<TextView>(R.id.lineItemPriceTotal)
        val precio = DecimalFormat("$#0.00").format(total)
        totalPrecio.text = precio
    }


    fun getDescripcion(index: Int): String {
        return when(index){
            DetalleOrdenFragment.QUESO -> "Queso de arroz"
            DetalleOrdenFragment.FRIJOLES -> "Frijol con queso de arroz"
            DetalleOrdenFragment.REVUELTAS -> "Revueltas de arroz"
            DetalleOrdenFragment.QUESO_MAIZ -> "Queso de maiz"
            DetalleOrdenFragment.REVUELTAS_MAIZ -> "Revueltas de maiz"
            else -> throw RuntimeException("Pupusa no soportada")
        }
    }






    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetalleOrdenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


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
