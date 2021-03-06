package com.hxz.gankio.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.commit
import com.hxz.baseui.view.BaseActivity
import com.hxz.gankio.R
import com.hxz.gankio.fragment.ListFragment
import com.hxz.gankio.utils.startTActivity
import kotlinx.android.synthetic.main.activity_main.*

class ArticleListActivity : BaseActivity() {

    companion object {
        fun startArticleList(context: Context,category: String,type: String) {
            context.startTActivity<ArticleListActivity>{
                putExtra(ListFragment.LIST_CATEGORY,category)
                putExtra(ListFragment.LIST_TYPE,type)
            }
        }
    }

    private val category by lazy { intent.getStringExtra(ListFragment.LIST_CATEGORY) }
    private val type by lazy { intent.getStringExtra(ListFragment.LIST_TYPE) }

    override fun bindLayout(): Int = R.layout.activity_article_list

    override fun initData() {
        setSupportActionBar(tool_bar)
        initToolBar()
        val fragment =
            supportFragmentManager.findFragmentByTag(getFragmentTag(category, type)) ?: ListFragment.newListFragment(category,type)
        supportFragmentManager.commit {
            replace(R.id.fl_article,fragment,getFragmentTag(category, type))
        }
    }

    private fun initToolBar() {
        supportActionBar?.title = category
        tool_bar.setNavigationIcon(R.drawable.ic_back_wh)
        tool_bar.titleMarginStart = 50
        tool_bar.subtitle = type

        tool_bar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getFragmentTag(category: String,type: String) : String {
        return "article:$category  $type"
    }

}