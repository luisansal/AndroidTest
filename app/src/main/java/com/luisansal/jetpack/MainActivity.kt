package com.luisansal.jetpack

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.luisansal.jetpack.ui.adapters.MyPagerAdapter
import com.luisansal.jetpack.ui.fragments.ListUserFragment
import com.luisansal.jetpack.ui.fragments.NewUserFragment
import com.luisansal.jetpack.common.interfaces.ActionsViewPagerListener
import com.luisansal.jetpack.common.interfaces.TitleListener

import java.util.ArrayList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.luisansal.jetpack.model.repository.UserRepository
import com.luisansal.jetpack.ui.MainActivityPresenter
import com.luisansal.jetpack.ui.mvp.MainActivityMVP
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActionsViewPagerListener, MainActivityMVP.View {


    override var fragmentName: String?
        get() {
            return MainActivity.fragmentName
        }
        set(value) {
            MainActivity.fragmentName = value
        }

    @Inject
    lateinit var userRepository: UserRepository

    private var mViewPager: ViewPager? = null
    private var actionBar: ActionBar? = null
    private var tabListener: ActionBar.TabListener? = null

    override fun setupTabListener() {
        tabListener = object : ActionBar.TabListener {
            override fun onTabSelected(tab: ActionBar.Tab, ft: FragmentTransaction) {
                // show the given tab
                mViewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: ActionBar.Tab, ft: FragmentTransaction) {
                // hide the given tab
            }

            override fun onTabReselected(tab: ActionBar.Tab, ft: FragmentTransaction) {
                // probably ignore this event
            }
        }
    }

    override fun setupViewPager(fragments: ArrayList<Fragment>) {

        val mDemoCollectionPagerAdapter = MyPagerAdapter(
                supportFragmentManager, fragments)


        mViewPager!!.adapter = mDemoCollectionPagerAdapter
        mViewPager!!.setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                actionBar!!.setSelectedNavigationItem(position)
            }
        })
    }

    override fun setupActionBar(fragments: ArrayList<Fragment>) {
        actionBar = supportActionBar
        actionBar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        // Add 3 tabs, specifying the tab's text and TabListener
        for (i in fragments.indices) {
            actionBar!!.addTab(
                    actionBar!!.newTab()
                            .setText((fragments[i] as TitleListener).title)
                            .setTabListener(tabListener))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Iniciando inyeccion de dependencias
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.pager)
        val presenter = MainActivityPresenter(this)
        presenter.init()
    }

    override fun onNext() {
        val position = mViewPager!!.currentItem
        mViewPager!!.currentItem = position + 1
        actionBar!!.setSelectedNavigationItem(position + 1)
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            Log.i("MainActivity", "popping backstack")
            if (fm.getBackStackEntryAt(fm.backStackEntryCount - 1).name == NewUserFragment.TAG) {
                fragmentName = ListUserFragment.TAG
            }
            if (fm.getBackStackEntryAt(fm.backStackEntryCount - 1).name == ListUserFragment.TAG) {
                fragmentName = NewUserFragment.TAG
            }

            fm.popBackStackImmediate()
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super")
            super.onBackPressed()
        }
    }

    companion object {
        var fragmentName: String? = null
    }
}
