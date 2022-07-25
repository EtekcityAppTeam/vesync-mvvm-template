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
            请选择相对路径名，创建后的activity会放置在packageName.activity，viewmodel会放置在packageName.viewmodel包下
            创建完成后会自动编译
            """
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val prefixName = stringParameter {
            name = "component name"
            default = ""
            help = "请输入组件资源前缀名，比如store，不需要则不用输入"
        }

        val activityName = stringParameter {
            name = "activity name"
            default = "Main"
            help = "请输入名称，请不要包含前缀名，请不要不包含'Activity'"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "layout name"
            default = "activity_"
            help = "请输入布局名，默认自动生成"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { createLayoutName(prefixName.value, activityName.value) }
        }

        val packageName = stringParameter {
            name = "package name"
            visible = { !isNewModule }
            default = "com.etekcity.vesyncplatform"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        val language = enumParameter<Language> {
            name = "programming language"
            help = "请选择编程语言"
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