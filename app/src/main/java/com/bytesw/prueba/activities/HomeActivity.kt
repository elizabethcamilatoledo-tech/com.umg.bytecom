package com.bytesw.prueba.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bytesw.prueba.R
import com.bytesw.prueba.fragments.DetailFragment
import com.bytesw.prueba.fragments.MasterFragment
import com.bytesw.prueba.models.Donut
import com.google.android.material.appbar.MaterialToolbar

class HomeActivity : AppCompatActivity(), MasterFragment.OnDonutSelectedListener {

    private lateinit var usuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        usuario = intent.getStringExtra("USUARIO") ?: ""

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.home_title)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentMaster, MasterFragment())
                .replace(R.id.fragmentDetail, DetailFragment())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.home_title))
            .setMessage("¡Bienvenido/a, $usuario!")
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            AlertDialog.Builder(this)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Estás seguro que querés cerrar sesión?")
                .setPositiveButton("Sí") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("No", null)
                .show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDonutSelected(donut: Donut) {
        val detailFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentDetail) as? DetailFragment
        detailFragment?.mostrarDetalle(donut)
    }
}
