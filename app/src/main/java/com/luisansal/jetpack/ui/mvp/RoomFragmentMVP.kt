package com.luisansal.jetpack.ui.mvp

interface RoomFragmentMVP{
    interface View {
        fun switchNavigation()
        fun getTagFragment() : String?
    }

    interface Presenter{
        fun init()
        fun switchNavigation()
    }
}