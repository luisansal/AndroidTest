package com.luisansal.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Iniciando inyeccion de dependencias
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_login)
    }
}