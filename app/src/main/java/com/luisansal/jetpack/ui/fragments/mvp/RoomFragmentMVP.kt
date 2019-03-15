package com.luisansal.jetpack.ui.fragments.mvp

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
