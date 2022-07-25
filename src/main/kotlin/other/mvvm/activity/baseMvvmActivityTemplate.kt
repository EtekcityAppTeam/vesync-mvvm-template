package other.mvvm.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

/**
 * Author: YulinZhang
 * Date: 2022/7/6 16:26
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * YulinZhang 2022/7/6 1.0 首次创建
 */
val baseMvvmActivityTemplate
    get() = template {
        name = "base mvvm activity"
        description = """
            适用于创建mvvm activity的模板
            请选择相对路径名，activity与viewModel文件会被同时创建在此路径名下
            创建完成后需要对新产生的文件编译
            """
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val prefixName = stringParameter {
            name = """
                组件名
                请输入组件名（用于命名后续生成的文件），如Store，没有则不用输入
            """.trimIndent()
            default = ""
        }

        val activityName = stringParameter {
            name = """
                activity名
                请输入activity名称，请不要包含组件前缀，请不要包含'Activity'后缀
            """.trimIndent()
            default = "Main"
            help = "请输入名称，请不要包含前缀名，请不要不包含'Activity'"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = """
                布局名
                请输入xml布局文件名，默认自动生成
            """.trimIndent()
            default = "activity_"
            help = "请输入布局名，默认自动生成"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { createLayoutName(prefixName.value, activityName.value) }
        }

        val packageName = stringParameter {
            name = """
                相对路径名
                可自定义相对路径，文件会被创建在此路径之下
            """.trimIndent()
            visible = { !isNewModule }
            default = "com.etekcity.vesyncplatform"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        val language = enumParameter<Language> {
            name = "请选择编程语言"
            default = Language.Kotlin
        }

        widgets(
            TextFieldWidget(prefixName),
            TextFieldWidget(activityName),
            TextFieldWidget(layoutName),
            TextFieldWidget(packageName),
            EnumWidget(language)
        )

        recipe = { data: TemplateData ->
            baseMvvmActivityRecipe(
                moduleData = data as ModuleTemplateData,
                packageName = packageName.value,
                activityName = "${prefixName.value}${activityName.value}Activity",
                layoutName = layoutName.value,
                viewModelName = "${prefixName.value}${activityName.value}ViewModel",
                bindingName = createBindingName(layoutName.value),
                language.value
            )
        }
    }

fun createLayoutName(prefixName: String, className: String): String = StringBuffer().run {
    if (prefixName.trim().isNotEmpty()) append("${prefixName.toLowerCase()}_") // 根据组件名前缀进行命名
    append("activity")
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