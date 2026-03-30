package com.bytesw.prueba.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bytesw.prueba.R
import com.bytesw.prueba.utils.UserPreferences
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUsuario: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userPreferences     = UserPreferences(this)
        etUsuario           = findViewById(R.id.etUsuario)
        etPassword          = findViewById(R.id.etPassword)
        etConfirmPassword   = findViewById(R.id.etConfirmPassword)

        findViewById<MaterialButton>(R.id.btnRegistrar).setOnClickListener { validarRegistro() }
        findViewById<MaterialButton>(R.id.btnIrLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validarRegistro() {
        val usuario  = etUsuario.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirm  = etConfirmPassword.text.toString().trim()

        when {
            usuario.length < 8 ->
                Toast.makeText(this, getString(R.string.error_usuario_corto), Toast.LENGTH_SHORT).show()
            password.length < 6 ->
                Toast.makeText(this, getString(R.string.error_password_corta), Toast.LENGTH_SHORT).show()
            !password.any { it.isUpperCase() } ->
                Toast.makeText(this, getString(R.string.error_password_mayuscula), Toast.LENGTH_SHORT).show()
            password != confirm ->
                Toast.makeText(this, getString(R.string.error_password_no_coincide), Toast.LENGTH_SHORT).show()
            !userPreferences.registrar(usuario, password) ->
                Toast.makeText(this, getString(R.string.error_usuario_existe), Toast.LENGTH_SHORT).show()
            else -> {
                Toast.makeText(this, getString(R.string.registro_exitoso), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}
