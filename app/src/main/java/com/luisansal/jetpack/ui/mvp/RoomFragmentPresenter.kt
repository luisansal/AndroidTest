package com.luisansal.jetpack.ui.mvp

class RoomFragmentPresenter(var mView: RoomFragmentMVP.View) : RoomFragmentMVP.Presenter{

    override fun init() {
        switchNavigation()
    }

    override fun switchNavigation() {
        mView.switchNavigation()
    }

}