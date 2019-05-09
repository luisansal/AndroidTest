package com.luisansal.jetpack.ui.fragments.mvp

import android.text.Editable
import android.text.TextWatcher
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.common.observer.BaseCompletableObserver
import com.luisansal.jetpack.common.observer.BaseSingleObserver
import com.luisansal.jetpack.model.usecase.interfaces.UserUseCase
import com.luisansal.jetpack.ui.fragments.NewUserFragment
import javax.inject.Inject

class NewUserFragmentPresenter @Inject constructor(private val userUseCase: UserUseCase) : NewUserFragmentMVP.Presenter {

    private lateinit var mView: NewUserFragment

    override fun onClickBtnSiguiente() {
        mView.btnSiguiente.setOnClickListener {
            val user = User()
            user.name = mView.etNombre.text.toString()
            user.lastName = mView.etApellido.text.toString()
            user.dni = mView.etDni.text.toString()

            mView.mCrudListener.oBject = user

            mView.mostrarResultado(user.name + " " + user.lastName)

            userUseCase.saveUser(user, SaveUserSubscriber())
        }
    }

    override fun onTextDniChanged() {
        mView.etDni.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                userUseCase.getUserByDni(charSequence.toString(), TextChangedSubscriber())
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }

    override fun init() {
        mView.onClickBtnListado()
        mView.onClickBtnSiguiente()
        mView.onTextDniChanged()
        mView.mCrudListener.oBject?.let { mView.loadViewModel(it) }
    }


    override fun setView(view: NewUserFragmentMVP.View) {
        mView = view as NewUserFragment
    }


    override fun saveUser() {

    }

    override fun goToNextPage() {

    }

    private inner class TextChangedSubscriber : BaseSingleObserver<User>() {
        override fun onSuccess(t: User) {
            mView.mCrudListener.oBject = t
            mView.loadViewModel(t)
        }
    }

    private inner class SaveUserSubscriber : BaseCompletableObserver() {
        override fun onComplete() {
            mView.nextPage()
        }
    }
}