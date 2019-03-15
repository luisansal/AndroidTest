package com.luisansal.jetpack.ui.fragments.mvp

class RoomFragmentPresenter(var mView: RoomFragmentMVP.View) : RoomFragmentMVP.Presenter{

    override fun init() {
        switchNavigation()
    }

    override fun switchNavigation() {
        mView.switchNavigation()
    }

}