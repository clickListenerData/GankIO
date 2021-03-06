package com.hxz.baseui.view

import android.widget.Toast
import com.hxz.baseui.base.IBaseVM
import com.hxz.baseui.viewmodel.BaseViewModel
import com.hxz.baseui.widght.LoadingDialog

/**
 * @title com.hxz.baseui.view  GankIO
 * @author xian_zhong  admin
 * @version 1.0
 * @Des BaseMActivity
 * @DATE 2020/5/29  10:45 星期五
 */
abstract class BaseMActivity<VM: BaseViewModel> : BaseActivity(),IBaseVM<VM> {

    protected val viewModel by lazy { createVM() }

    protected val mLoading : LoadingDialog by lazy {
        LoadingDialog(this).apply {
            initLoading()
        }
    }

    override fun onStart() {
        super.onStart()
        initLiveEvent(viewModel,this)
    }

    override fun showDialog(msg: String?) {
        if (mLoading.isShowing) return
        mLoading.setMessage(msg ?: "")
        mLoading.show()
    }

    override fun dismissDialog() {
        if (mLoading.isShowing) mLoading.dismiss()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun finishThis() {
        finish()
    }

    override fun LoadingDialog.initLoading() {

    }

    override fun other(any: Any?) {

    }
}