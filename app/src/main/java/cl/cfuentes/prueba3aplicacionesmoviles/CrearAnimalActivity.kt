package cl.cfuentes.prueba3aplicacionesmoviles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.cfuentes.prueba3aplicacionesmoviles.databinding.ActivityCrearAnimalBinding
import cl.cfuentes.prueba3aplicacionesmoviles.dataclasses.Animal
import cl.cfuentes.prueba3aplicacionesmoviles.helpers.VeterinariaDatabaseHelper

/**
 * Clase CrearPacienteActivity
 * Desarrollado por: Christian Fuentes Unda
 * Fecha: 15/05/2024 10:07
 *
 * Ultima modificacion por:
 * Fecha:
 */
class CrearAnimalActivity : AppCompatActivity() {
    lateinit var binding:ActivityCrearAnimalBinding
    lateinit var db : VeterinariaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = VeterinariaDatabaseHelper(this)

        binding.btnCreaAnimal.setOnClickListener {
            val chip = binding.chipAnimal.text.toString().toInt()
            val nombre_animal=binding.nombreAnimal.text.toString()
            val nombre_dueno = binding.nombreDuenoAnimal.text.toString()
            val raza_animal = binding.razaAnimal.text.toString()
            val animal = Animal(chip, nombre_animal, nombre_dueno,raza_animal)

            val estado = db.insertAnimal(animal)
            if (estado > -1)
                setResult(99)
            else
                setResult(estado)
            finish()
        }
    }
}