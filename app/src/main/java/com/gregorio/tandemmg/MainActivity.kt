package com.gregorio.tandemmg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var tvBienvenida: TextView
    private lateinit var btnLogout: Button
    private lateinit var btnWa1: Button
    private lateinit var btnWa2: Button
    private lateinit var btnWa3: Button
    private lateinit var btnWa4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        tvBienvenida = findViewById(R.id.tvBienvenida)
        btnLogout = findViewById(R.id.btnLogout)
        btnWa1 = findViewById(R.id.btnWa1)
        btnWa2 = findViewById(R.id.btnWa2)
        btnWa3 = findViewById(R.id.btnWa3)
        btnWa4 = findViewById(R.id.btnWa4)

        // Mostrar nombre del usuario
        val usuario = auth.currentUser
        if (usuario != null) {
            val nombre = usuario.displayName ?: usuario.email ?: "cliente"
            tvBienvenida.text = "Hola, $nombre 👋"
        }

        // Botones WhatsApp
        btnWa1.setOnClickListener { abrirWhatsApp("Hola! Me interesa la Taza personalizada 🫖") }
        btnWa2.setOnClickListener { abrirWhatsApp("Hola! Me interesa el Cuadro con foto 🖼️") }
        btnWa3.setOnClickListener { abrirWhatsApp("Hola! Me interesa el Llavero grabado 🔑") }
        btnWa4.setOnClickListener { abrirWhatsApp("Hola! Me interesa la Caja de regalo 🎁") }

        // Cerrar sesión
        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun abrirWhatsApp(mensaje: String) {
        val numero = "529615603262"
        val url = "https://wa.me/$numero?text=${Uri.encode(mensaje)}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}