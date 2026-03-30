package com.bytesw.prueba.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("bytesw_users", Context.MODE_PRIVATE)
    private val gson = Gson()

    private fun getUsers(): MutableMap<String, String> {
        val json = prefs.getString("users_map", null) ?: return mutableMapOf()
        val type = object : TypeToken<MutableMap<String, String>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveUsers(users: Map<String, String>) {
        prefs.edit().putString("users_map", gson.toJson(users)).apply()
    }

    // Retorna false si el usuario ya existe
    fun registrar(usuario: String, password: String): Boolean {
        val users = getUsers()
        if (users.containsKey(usuario)) return false
        users[usuario] = password
        saveUsers(users)
        return true
    }

    // Retorna true si las credenciales son correctas
    fun login(usuario: String, password: String): Boolean {
        val users = getUsers()
        return users[usuario] == password
    }
}
