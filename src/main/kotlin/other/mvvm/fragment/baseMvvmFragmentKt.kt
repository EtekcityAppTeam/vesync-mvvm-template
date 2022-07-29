package other.mvvm.fragment

import org.apache.commons.lang3.SystemUtils
import other.mvvm.BaseMvvmConstant
import other.mvvm.layout.createLayoutViewModelName
import java.text.SimpleDateFormat

fun baseMvvmFragmentKt(
    applicationPackage: String?,
    packageName: String,
    fragmentName: String,
    bindingName: String,
    viewModelName: String,
    layoutName: String,
    description: String
) = """
package $packageName

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vesync.base.BaseMvvmFragment${
    if (!applicationPackage.isNullOrBlank()) {
        "\nimport ${applicationPackage}.BR\nimport ${applicationPackage}.R"
    } else ""
}
import ${applicationPackage ?: packageName}.databinding.$bindingName

/**
 * Author: ${SystemUtils.USER_NAME}
 * Date: ${SimpleDateFormat(BaseMvvmConstant.HEADER_TIME_FORMAT).format(System.currentTimeMillis())}
 * Description: $description
 * History:
 * <author> <time> <version> <desc>
 * ${SystemUtils.USER_NAME} ${SimpleDateFormat(BaseMvvmConstant.HEADER_DATE_FORMAT).format(System.currentTimeMillis())} 1.0 首次创建
 */
class $fragmentName : BaseMvvmFragment<$bindingName, $viewModelName>() {

    override fun initContentView(p0: LayoutInflater?, p1: ViewGroup?, p2: Bundle?): Int = R.layout.$layoutName

    override fun initVariableId(): Int = BR.${createLayoutViewModelName(viewModelName)}

    override fun createViewModel(fragment: Fragment): $viewModelName = ViewModelProviders.of(fragment).get($viewModelName::class.java)
    
    companion object {

    }
}
""".trimIndent()