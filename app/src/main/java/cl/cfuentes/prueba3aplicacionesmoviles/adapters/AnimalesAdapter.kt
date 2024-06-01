package cl.cfuentes.prueba3aplicacionesmoviles.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.cfuentes.prueba3aplicacionesmoviles.databinding.AnimalesItemBinding
import cl.cfuentes.prueba3aplicacionesmoviles.dataclasses.Animal
import cl.cfuentes.prueba3aplicacionesmoviles.helpers.VeterinariaDatabaseHelper
import cl.cfuentes.prueba3aplicacionesmoviles.viewholders.AnimalesViewHolder
import com.google.android.material.snackbar.Snackbar

/**
 * Clase AnimalesAdapter
 * Desarrollado por: Christian Fuentes Unda
 * Fecha: 15/05/2024 10:22
 *
 * Ultima modificacion por:
 * Fecha:
 */
class AnimalesAdapter(private var animales:List<Animal>,context: Context) :RecyclerView.Adapter<AnimalesViewHolder>() {
    private val db:VeterinariaDatabaseHelper = VeterinariaDatabaseHelper(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalesViewHolder {
        return AnimalesViewHolder(
            AnimalesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return animales.size
    }

    override fun onBindViewHolder(holder: AnimalesViewHolder, position: Int) {
        val animal = animales[position]
        holder.binding.tvChipAnimal.text = animal.chip.toString()
        holder.binding.tvnombreAnimal.text = animal.nombre_animal.toString()
        holder.binding.btDelete.setOnClickListener {
            db.deleteAnimal(animal.chip)
            actualizarDatos(db.getAllAnimales())
            Snackbar.make(holder.itemView,"El animal ${animal.nombre_animal} con serie ${animal.chip} fue eliminado",Snackbar.LENGTH_LONG).show()
        }

        holder.binding.btUpdate.setOnClickListener {
            val animalActual = animales[position]
            


        }
    }

    fun actualizarDatos(newAnimales: List<Animal>){
        animales=newAnimales
        notifyDataSetChanged()
    }


}