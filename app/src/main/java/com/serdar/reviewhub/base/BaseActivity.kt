package com.serdar.reviewapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.serdar.reviewapp.IViewState

abstract class BaseActivity<T: BaseViewModel, DB: ViewDataBinding>: AppCompatActivity() {

    protected lateinit var viewModel: T
    protected lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(getViewModel())
        viewModel.setApplicationContext(applicationContext)
        viewModel.state.observe(this, object: Observer<IViewState> {
            override fun onChanged(t: IViewState?) {
                t?.let {
                    onStateChange(it)
                }
            }
        })
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        //binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        viewModel.init()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModel(): Class<T>

    protected abstract fun onStateChange(state: IViewState)
}