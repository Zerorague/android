package cl.cfuentes.prueba3aplicacionesmoviles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import cl.cfuentes.prueba3aplicacionesmoviles.adapters.AnimalesAdapter
import cl.cfuentes.prueba3aplicacionesmoviles.databinding.ActivityMainBinding
import cl.cfuentes.prueba3aplicacionesmoviles.helpers.VeterinariaDatabaseHelper
import com.google.android.material.snackbar.Snackbar

/**
 * Clase MainActivity
 * Desarrollado por: Christian Fuentes Unda
 * Fecha: 15/05/2024 09:45
 *
 * Ultima modificacion por:
 * Fecha:
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: VeterinariaDatabaseHelper
    lateinit var animalesAdapter : AnimalesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = VeterinariaDatabaseHelper(this)
        animalesAdapter = AnimalesAdapter(db.getAllAnimales(),this)
        binding.rvAnimales.layoutManager = LinearLayoutManager(this)
        binding.rvAnimales.adapter=animalesAdapter

        binding.fabCrear.setOnClickListener {
            val intent = Intent(this, CrearAnimalActivity::class.java)
            getResultadoCrearPaciente.launch(intent)
        }



    }


    override fun onResume() {
        super.onResume()

        animalesAdapter.actualizarDatos(db.getAllAnimales())
    }

    private val getResultadoCrearPaciente =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == 99) {
                Snackbar.make(binding.root,"Paciente creado correctamente.", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(binding.root,"Error al insertar paciente.", Snackbar.LENGTH_LONG).show()
            }
        }

}