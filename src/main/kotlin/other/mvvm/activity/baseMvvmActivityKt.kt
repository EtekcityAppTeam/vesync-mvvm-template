package other.mvvm.activity

import other.mvvm.layout.createLayoutViewModelName

fun baseMvvmActivityKt(
    applicationPackage: String?,
    activityName: String,
    bindingName: String,
    viewModelName: String,
    layoutName: String,
    packageName: String
) = """
package ${packageName}.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.vesync.base.BaseMvvmActivity
import ${applicationPackage}.BR
import ${applicationPackage}.R
import ${applicationPackage}.databinding.$bindingName
import ${packageName}.viewmodel.${viewModelName}

class $activityName : BaseMvvmActivity<$bindingName, $viewModelName>() {

    companion object {

        fun start(context: Context) = context.startActivity(Intent(context, ${activityName}::class.java).apply { })
    }

    override fun initParam() {
        super.initParam()
    }

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
    }

    override fun initViewObservable() {
        super.initViewObservable()
    }

    override fun initContentView(p0: Bundle?): Int = R.layout.$layoutName

    override fun initVariableId(): Int = BR.${createLayoutViewModelName(viewModelName)}

    override fun createViewModel(activity: FragmentActivity): $viewModelName = ViewModelProviders.of(activity).get($viewModelName::class.java)
}
"""