package com.bytesw.prueba.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bytesw.prueba.R
import com.bytesw.prueba.utils.UserPreferences
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userPreferences = UserPreferences(this)
        etUsuario  = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)

        findViewById<MaterialButton>(R.id.btnIngresar).setOnClickListener { validarLogin() }
        findViewById<MaterialButton>(R.id.btnIrRegistro).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validarLogin() {
        val usuario  = etUsuario.text.toString().trim()
        val password = etPassword.text.toString().trim()

        when {
            usuario.length < 8 ->
                Toast.makeText(this, getString(R.string.error_usuario_corto), Toast.LENGTH_SHORT).show()
            password.length < 6 ->
                Toast.makeText(this, getString(R.string.error_password_corta), Toast.LENGTH_SHORT).show()
            !password.any { it.isUpperCase() } ->
                Toast.makeText(this, getString(R.string.error_password_mayuscula), Toast.LENGTH_SHORT).show()
            !userPreferences.login(usuario, password) ->
                Toast.makeText(this, getString(R.string.error_credenciales), Toast.LENGTH_SHORT).show()
            else -> {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("USUARIO", usuario)
                startActivity(intent)
                finish()
            }
        }
    }
}
