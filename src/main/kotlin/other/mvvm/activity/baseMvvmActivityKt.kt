package other.mvvm.activity

fun baseMvvmActivityKt(
    applicationPackage: String?,
    activityClass: String,
    activityBinding: String,
    viewModelClass: String,
    layoutName: String,
    packageName: String
) = """
package ${packageName}.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.vesync.base.BaseMvvmActivity
import ${applicationPackage}.BR
import ${applicationPackage}.R
import ${applicationPackage}.databinding.$activityBinding

class $activityClass : BaseMvvmActivity<$activityBinding, $viewModelClass>() {

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

    override fun initVariableId(): Int = BR.${viewModelClass.toCharArray()[0].toLowerCase()}

    override fun createViewModel(activity: FragmentActivity): $viewModelClass = ViewModelProviders.of(activity).get($viewModelClass::class.java)
}
"""