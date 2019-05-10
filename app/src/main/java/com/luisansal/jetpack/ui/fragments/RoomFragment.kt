package com.luisansal.jetpack.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.luisansal.jetpack.R
import com.luisansal.jetpack.common.interfaces.ActionsViewPagerListener
import com.luisansal.jetpack.common.interfaces.CrudListener
import com.luisansal.jetpack.common.interfaces.TitleListener
import com.luisansal.jetpack.model.domain.User
import com.luisansal.jetpack.ui.mvp.RoomFragmentMVP
import com.luisansal.jetpack.ui.mvp.RoomFragmentPresenter
import com.luisansal.jetpack.ui.viewmodel.RoomViewModel

class RoomFragment : Fragment(), TitleListener, CrudListener<User>, RoomFragmentMVP.View {

    override val title = "Room Manager"
    private lateinit var mViewModel: RoomViewModel
    private var mActionsViewPagerListener: ActionsViewPagerListener? = null

    override var oBject: User?
        get() = mViewModel.user
        set(value) {
            mViewModel.user = value
        }

    override val objects: List<User>
        get() = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)

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
        ft.replace(R.id.parent_fragment_container, NewUserFragment.newInstance(this), NewUserFragment.TAG)
        ft.addToBackStack(NewUserFragment.TAG)
        ft.commit()
        mActionsViewPagerListener!!.fragmentName = NewUserFragment.TAG
    }

    fun onNewAuthor() {
        // Begin the transaction
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.parent_fragment_container, NewAuthorFragment.newInstance(this), NewUserFragment.TAG)
        ft.addToBackStack(NewUserFragment.TAG)
        ft.commit()
        mActionsViewPagerListener!!.fragmentName = NewAuthorFragment.TAG
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            mActionsViewPagerListener = context as ActionsViewPagerListener
        } catch (e : Exception) {
            throw RuntimeException(context.toString()
                    + " must implement " + mActionsViewPagerListener!!.javaClass.getSimpleName())
        }
    }

    override fun onDetach() {
        super.onDetach()
        mActionsViewPagerListener = null
    }

    override fun onEdit() {

    }

    override fun setOBjects(oBjects: List<User>) {

    }

    override fun switchNavigation() {
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.parent_fragment_container, NewUserFragment.newInstance(this), NewUserFragment.TAG)
        ft.commit()

        if (getTagFragment() != null) {
            when (getTagFragment()) {
                NewUserFragment.TAG -> {
                    onNewAuthor()
                }
                else -> {
                    onList()
                }
            }
        }
    }

    override fun switchNavigationAuthor() {
        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.replace(R.id.parent_fragment_container, NewAuthorFragment.newInstance(this), NewAuthorFragment.TAG)
        ft.commit()

        if (getTagFragment() != null) {
            when (getTagFragment()) {
                NewAuthorFragment.TAG -> {
                    onNewAuthor()
                }
                else -> {
                    onList()
                }
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
