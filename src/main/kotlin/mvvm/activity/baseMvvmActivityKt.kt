package mvvm.activity

import org.apache.commons.lang3.SystemUtils.USER_NAME
import mvvm.BaseMvvmConstant.Companion.HEADER_DATE_FORMAT
import mvvm.BaseMvvmConstant.Companion.HEADER_TIME_FORMAT
import mvvm.layout.createLayoutViewModelName
import java.text.SimpleDateFormat

fun baseMvvmActivityKt(
    applicationPackage: String?,
    activityName: String,
    bindingName: String,
    viewModelName: String,
    layoutName: String,
    packageName: String,
    description: String
) = """
package $packageName

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.vesync.base.BaseMvvmActivity${
    if (!applicationPackage.isNullOrBlank()) {
        "\nimport ${applicationPackage}.BR\nimport ${applicationPackage}.R"
    } else ""
}
import ${applicationPackage ?: packageName}.databinding.$bindingName

/**
 * Author: $USER_NAME
 * Date: ${SimpleDateFormat(HEADER_TIME_FORMAT).format(System.currentTimeMillis())}
 * Description: $description
 * History:
 * <author> <time> <version> <desc>
 * $USER_NAME ${SimpleDateFormat(HEADER_DATE_FORMAT).format(System.currentTimeMillis())} 1.0 首次创建
 */
class $activityName : BaseMvvmActivity<$bindingName, $viewModelName>() {

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
    
    companion object {

        fun start(context: Context) = context.startActivity(Intent(context,     ${activityName}    ::class.java).apply { })
    }
}
""".trimIndent()