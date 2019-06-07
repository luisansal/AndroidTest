package com.luisansal.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.luisansal.jetpack.model.domain.Login
import com.luisansal.jetpack.ui.mvp.login.LoginActivityMVP
import com.luisansal.jetpack.ui.mvp.login.LoginActivityPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginActivityMVP.View {

    @Inject
    lateinit var mPresenter: LoginActivityPresenter

    override fun entrarPantallaPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onClickBtnLogin() {
        btnLogin.setOnClickListener {
            login = Login(etUsr.text.toString(), etPwd.text.toString())
            mPresenter.validarLogin()
        }
    }

    override var login: Login? = null

    override fun mensajeLoginCorrecto() {
        Toast.makeText(this, "Logueo Correcto", Toast.LENGTH_LONG).show()
    }

    override fun mensajeLoginIncorrecto() {
        Toast.makeText(this, "Logueo Incorrecto", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Iniciando inyeccion de dependencias
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_login)

        mPresenter.setView(this)
        mPresenter.init()
    }
}