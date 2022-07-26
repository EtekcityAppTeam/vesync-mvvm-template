package other.mvvm.fragment

import other.mvvm.layout.createLayoutViewModelName

fun baseMvvmFragmentKt(
    applicationPackage: String?,
    packageName: String,
    fragmentName: String,
    bindingName: String,
    viewModelName: String,
    layoutName: String
) = """
package com.vesync.store.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vesync.base.BaseMvvmFragment
import ${applicationPackage}.BR
import ${applicationPackage}.R
import ${applicationPackage}.databinding.$bindingName

class $fragmentName : BaseMvvmFragment<$bindingName, $viewModelName>() {

    companion object {

    }

    override fun initContentView(p0: LayoutInflater?, p1: ViewGroup?, p2: Bundle?): Int = R.layout.$layoutName

    override fun initVariableId(): Int = BR.${createLayoutViewModelName(viewModelName)}

    override fun createViewModel(fragment: Fragment): $viewModelName = ViewModelProviders.of(fragment).get($viewModelName::class.java)
}
""".trimIndent()