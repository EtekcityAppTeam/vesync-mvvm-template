package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.mvvm.activity.baseMvvmActivityTemplate
import other.mvvm.fragment.baseMvvmFragmentTemplate

/**
 * Author: YulinZhang
 * Date: 2022/7/6 16:12
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * YulinZhang 2022/7/6 1.0 首次创建
 */
class BaseMvvmActivityTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        // activity的模板
        baseMvvmActivityTemplate,
        // fragment的模板
        baseMvvmFragmentTemplate
    )
}

fun createLayoutName(componentName: String, prefixName: String, className: String): String = StringBuffer().run {
    if (componentName.trim().isNotEmpty()) append("${componentName.toLowerCase()}_") // 根据组件名前缀进行命名
    append(prefixName)
    className.toCharArray().forEachIndexed { index, c ->
        if (c.isUpperCase() || index == 0) append("_${c.toLowerCase()}")
        else append(c)
    }
    toString()
}

fun createBindingName(layoutName: String): String = StringBuffer().run {
    try {
        val stringList = layoutName.split("_")
        stringList.forEach { string ->
            string.forEachIndexed { index, c ->
                if (index == 0) append(c.toUpperCase()) else append(c)
            }
        }
        append("Binding")
    } catch (e: IndexOutOfBoundsException) {
        e.printStackTrace()
    }
    toString()
}