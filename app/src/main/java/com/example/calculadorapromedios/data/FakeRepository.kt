package com.example.calculadorapromedios.data

class FakeRepository {
    fun guardarPromedio(promedio: Double): String {
        // Simula guardar en base de datos
        return "Promedio ${String.format("%.2f", promedio)} guardado exitosamente."
    }
}