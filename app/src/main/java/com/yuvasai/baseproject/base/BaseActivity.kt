package com.yuvasai.baseproject.base

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuvasai.baseproject.BR
import com.yuvasai.baseproject.R
import com.yuvasai.baseproject.utils.observe
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<IViewModel : BaseViewModel, Binding : ViewDataBinding> :
    AppCompatActivity(), Navigator, HasSupportFragmentInjector {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    protected abstract val layoutId: Int
    protected lateinit var binding: Binding
    protected abstract val viewModelClass: KClass<IViewModel>
    private var dialog: Dialog? = null

    protected val viewModel: IViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass.java)
    }

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.navigatorListener = this

        viewModel.systemAlertListener.observe {
            when (it) {
                ErrorTemplate.NO_INTERNET -> {
                    println("=================no_internet==============")
                }
                ErrorTemplate.DEFAULT_LOADER_SHOW -> {
                    showDialog()
                }
                ErrorTemplate.DEFAULT_LOADER_DISMISS -> {
                    cancelDialog()
                }
            }
            viewModel.systemAlertListener.set(ErrorTemplate.NONE)
        }

    }

    fun showDialog() {
        dialog = Dialog(this)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(R.layout.progressbar)
        dialog?.setCancelable(false)
        dialog?.show()
    }

    fun cancelDialog() {
        if (dialog != null) {
            dialog?.dismiss()
        }
    }

}