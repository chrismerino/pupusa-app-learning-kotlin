package sv.edu.bitlab.pupusap.Models

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Orden() : Parcelable {
    var precioUnidad = 0.5f
    var textInput = ""
    var maiz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )
    var arroz = hashMapOf(
        QUESO to 0,
        FRIJOLES to 0,
        REVUELTAS to 0
    )
    private var fecha:Calendar = Calendar.getInstance()

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeFloat(this.precioUnidad)
        dest.writeInt(maiz[QUESO]!!)
        dest.writeInt(maiz[FRIJOLES]!!)
        dest.writeInt(maiz[REVUELTAS]!!)
        dest.writeInt(arroz[QUESO]!!)
        dest.writeInt(arroz[FRIJOLES]!!)
        dest.writeInt(arroz[REVUELTAS]!!)
//        dest.writeString(this.getFecha())
    }

    constructor(parcel: Parcel) : this() {
        precioUnidad = parcel.readFloat()
        maiz[QUESO] = parcel.readInt()
        maiz[FRIJOLES] = parcel.readInt()
        maiz[REVUELTAS] = parcel.readInt()
        arroz[QUESO] = parcel.readInt()
        arroz[FRIJOLES] = parcel.readInt()
        arroz[REVUELTAS] = parcel.readInt()
//        setFecha(parcel.readString()!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getTotal(): Float {
        val totalMaiz = maiz.map { entry ->
            entry.value
        }.reduce { total, counter -> total+counter }

        val totalArroz = arroz.map { entry ->
            entry.value
        }.reduce { total, counter -> total+counter }

        return (totalArroz * precioUnidad) + (totalMaiz * precioUnidad)
    }

//    fun getFecha(): String {
//        val formatter = SimpleDateFormat(FORMATO_FECHA)
//        return formatter.format(fecha.time)
//    }
//
//    fun setFecha(fecha: String) {
//        val formatter = SimpleDateFormat(FORMATO_FECHA)
//        this.fecha = Calendar.getInstance()
//        this.fecha.time = formatter.parse(fecha)
//    }


    companion object CREATOR : Parcelable.Creator<Orden> {

        const val QUESO = "QUESO"
        const val FRIJOLES = "FRIJOLES"
        const val REVUELTAS = "REVUELTAS"
        const val MAIZ = "MAIZ"
        const val ARROZ = "ARROZ"
        const val FORMATO_FECHA = "dd-MM-yyyy" //09-09-2019

        override fun createFromParcel(parcel: Parcel): Orden {
            return Orden(parcel)
        }

        override fun newArray(size: Int): Array<Orden?> {
            return arrayOfNulls(size)
        }


        fun randomOrders() :ArrayList<Orden>{
            var lista = arrayListOf<Orden>()
            for(index in 0..10){
                lista.add(Orden())
            }
            return lista
        }
    }

}