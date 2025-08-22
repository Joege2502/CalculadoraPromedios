package com.example.calculadorapromedios
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import kotlin.getValue
import com.example.calculadorapromedios.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // No está en la práctica, pero es útil si lo tienes
        setContentView(R.layout.activity_main)

        // Asignar los componentes del layout a variables en el código
        val num1EditText = findViewById<EditText>(R.id.etCalificacion1)
        val num2EditText = findViewById<EditText>(R.id.etCalificacion2)
        val num3EditText = findViewById<EditText>(R.id.etCalificacion3)
        val calculateButton = findViewById<Button>(R.id.btnCalcular)
        val resultTextView = findViewById<TextView>(R.id.tvResultado)

        // Observar el promedio calculado para actualizar el TextView
        viewModel.promedio.observe(this, Observer { promedio ->
            resultTextView.text = "El promedio es: ${String.format("%.2f", promedio)}"
        })

        // Observar el mensaje de "guardado" para mostrar un Toast
        viewModel.guardarMensaje.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        // Configurar el "click listener" del botón
        calculateButton.setOnClickListener {
            try {
                // Obtener valores y convertirlos a Double
                val n1 = num1EditText.text.toString().toDouble()
                val n2 = num2EditText.text.toString().toDouble()
                val n3 = num3EditText.text.toString().toDouble()

                // Llamar a la función del ViewModel para calcular el promedio
                viewModel.calcularPromedio(n1, n2, n3)
            } catch (e: Exception) {
                // Manejar la excepción si los valores no son válidos
                Toast.makeText(this, "Ingrese valores válidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
