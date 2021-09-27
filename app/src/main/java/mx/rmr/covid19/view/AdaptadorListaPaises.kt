package mx.rmr.covid19.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.rmr.covid19.R
import mx.rmr.covid19.model.Pais

// Proporciona datos para el RecyclerView
class AdaptadorListaPaises (var arrPaises: ArrayList<Pais>) :
    RecyclerView.Adapter<AdaptadorListaPaises.PaisViewHolder>()
{
    //Fragmento
    var listener: RenglonListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        // Cada renglón se crea aquí
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_pais, parent, false)
        return PaisViewHolder(vista)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        holder.set(arrPaises[position])
        //Listener
        val vista = holder.itemView     //renglon_pais.xml
        val layoutRenglon = vista.findViewById<LinearLayout>(R.id.layoutRenglon)
        layoutRenglon.setOnClickListener{
            println("Click sobre ${arrPaises[position]}")
            //avisar al fragmento que hay un click
            listener?.clickEnRenglon(position)
        }
    }

    // Cuántos renglones tiene el RecyclerView???
    override fun getItemCount(): Int {
        return arrPaises.size
    }

    fun actualizar(lista: List<Pais>?) {
        arrPaises.clear()
        if (lista!=null) {
            arrPaises.addAll(lista)
        }
        notifyDataSetChanged()      // Recargar la información
    }

    // vista es nuestro renglón
    class PaisViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        private val tvPais = vista.findViewById<TextView>(R.id.tvPais)
        private val tvCasos = vista.findViewById<TextView>(R.id.tvCasos)
        private val ivBandera = vista.findViewById<ImageView>(R.id.ivBandera)
        // Agregar un ImageView para la bandera (Glide)

        fun set(pais: Pais) {
            tvPais.text = pais.nombre
            tvCasos.text = pais.casos.toString()
            Glide.with(this.itemView).load(pais.bandera["flag"].toString()).into(ivBandera)
        }
    }

}