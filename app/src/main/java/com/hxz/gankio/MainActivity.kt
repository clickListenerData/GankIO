package com.hxz.gankio

import android.os.Bundle
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.hxz.baseui.view.BaseActivity
import com.hxz.gankio.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val fragmentList = arrayListOf<Fragment>()

    override fun beforeBindLayout(saveInstanceState: Bundle?) {

    }

    override fun bindLayout(): Int = R.layout.activity_main

    override fun initData() {
        rg_main.forEachIndexed { index, view ->
            view.id = index
        }
        rg_main.setOnCheckedChangeListener { group, checkedId ->
            switchFragment(checkedId)
        }
    }

    private fun switchFragment(index: Int) {
        supportFragmentManager.commit {
            fragmentList.forEach { hide(it) }
            if (fragmentList.size <= index) {
                val fragment = getFragment(index)
                add(R.id.fl_main,fragment)
                fragmentList.add(fragment)
            } else {
                show(fragmentList[index])
            }
        }
    }

    private fun getFragment(index: Int) = when(index) {
        0 -> HomeFragment()
        else -> HomeFragment()
    }
}
