package com.luisansal.jetpack.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.luisansal.jetpack.R
import com.luisansal.jetpack.interfaces.ActionsViewPagerListener
import com.luisansal.jetpack.interfaces.CrudListener
import com.luisansal.jetpack.interfaces.TitleListener
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.ui.fragments.mvp.RoomFragmentMVP
import com.luisansal.jetpack.ui.fragments.mvp.RoomFragmentPresenter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel

class RoomFragment : Fragment(), TitleListener, CrudListener<User>, RoomFragmentMVP.View {

    override val title = "Room Manager"
    private var mViewModel: RoomViewModel? = null
    private var mActionsViewPagerListener: ActionsViewPagerListener? = null

    override var oBject: User?
        get() = mViewModel?.user
        set(oBject) {
            mViewModel!!.user = oBject
        }

    override val objects: List<User>?
        get() = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java!!)

        // Begin the transaction
        mActionsViewPagerListener = activity as ActionsViewPagerListener?
        val presenter = RoomFragmentPresenter(this)
        presenter.init()
    }

    override fun onList() {
        // Begin the transaction
        val fm = activity!!.supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.parent_fragment_container, ListUserFragment.newInstance(this), ListUserFragment.TAG)
        ft.addToBackStack(ListUserFragment.TAG)
        ft.commit()
        mActionsViewPagerListener!!.fragmentName = ListUserFragment.TAG
    }

    override fun onNew() {
        // Begin the transaction
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.parent_fragment_container, NewUserFragment.newInstance(activity as ActionsViewPagerListener?, this), NewUserFragment.TAG)
        ft.addToBackStack(NewUserFragment.TAG)
        ft.commit()
        mActionsViewPagerListener!!.fragmentName = NewUserFragment.TAG
    }

    override fun onEdit() {

    }

    override fun setOBjects(oBjects: List<User>) {

    }

    override fun switchNavigation() {
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.parent_fragment_container, NewUserFragment.newInstance(mActionsViewPagerListener, this), NewUserFragment.TAG)
        ft.commit()

        if (getTagFragment() != null) {
            if (getTagFragment() == NewUserFragment.TAG) {
                onNew()
            } else if (getTagFragment() == ListUserFragment.TAG) {
                onList()
            }
        }
    }

    override fun getTagFragment(): String? {
        return mActionsViewPagerListener!!.fragmentName
    }

    companion object {

        fun newInstance(): RoomFragment {
            return RoomFragment()
        }
    }
}
