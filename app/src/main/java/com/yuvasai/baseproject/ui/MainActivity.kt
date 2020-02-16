package com.yuvasai.baseproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.yuvasai.baseproject.R
import com.yuvasai.baseproject.base.BaseActivity
import com.yuvasai.baseproject.base.MainArguments
import com.yuvasai.baseproject.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutId: Int = R.layout.activity_main

    override val viewModelClass: KClass<MainViewModel> = MainViewModel::class

    override fun navigate(navigateTo: String, arguments: Any?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.callApi()
        viewModel.callForecastApi()
    }

    companion object {

        fun createActivity(context: Context, arguments: MainArguments): Intent {
            val i = Intent(context, MainActivity::class.java)
            i.putExtra("arguments", arguments)
            return i
        }
    }
}
