package com.bangkit.skincareku.networking.data

import android.content.Context

class DataManager(context: Context) {
    private val preferences = context.getSharedPreferences("skincareku", Context.MODE_PRIVATE)

    fun saveEmail(email: String?) {
        preferences.edit().putString("email", email).apply()
    }
    fun saveBirthdate(birthdate: String?) {
        preferences.edit().putString("birthdate", birthdate).apply()
    }

    fun saveGender(gender: String?) {
        preferences.edit().putString("gender", gender).apply()
    }

    fun saveSkinProblem(skinProblem: String?) {
        preferences.edit().putString("skinProblem", skinProblem).apply()
    }

    fun saveAllergy(allergy: String?) {
        preferences.edit().putString("allergy", allergy).apply()
    }

    fun getEmail(): String? {
        return preferences.getString("email", null)
    }

    fun getBirthdate(): String? {
        return preferences.getString("birthdate", null)
    }

    fun getGender(): String? {
        return preferences.getString("gender", null)
    }

    fun getSkinProblem(): String? {
        return preferences.getString("skinProblem", null)
    }

    fun getAllergy(): String? {
        return preferences.getString("allergy", null)
    }
}