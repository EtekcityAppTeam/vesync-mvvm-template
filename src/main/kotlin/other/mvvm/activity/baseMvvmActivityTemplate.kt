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
        // version = 1
        name = "base mvvm activity"
        description = "适用于创建mvvm activity的模板"
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val activityClass = stringParameter {
            name = "activity name"
            default = "Main"
            help = "请输入名称，不包含'Activity'"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val viewModelClass = stringParameter {
            name = "viewmodel name"
            default = "Main"
            help = "请输入名称，不包含'ViewModel'"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${activityClass.value}ActivityViewModel" }
        }

        val layoutName = stringParameter {
            name = "layout name"
            default = "activity_main"
            help = "请输入布局名，默认自动生成"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(createLayoutName(activityClass.value)) }
        }

        val packageName = stringParameter {
            name = "package name"
            visible = { !isNewModule }
            default = "com.vesync.platform"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        val language = enumParameter<Language> {
            name = "programming language"
            help = "请选择编程语言"
            default = Language.Kotlin
        }

        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(viewModelClass),
            TextFieldWidget(layoutName),
            TextFieldWidget(packageName),
            EnumWidget(language)
        )

        recipe = { data: TemplateData ->
            baseMvvmActivityRecipe(
                data as ModuleTemplateData,
                activityClass = activityClass.value,
                activityBinding = "Activity${activityClass}Binding",
                viewModelClass = viewModelClass.value,
                layoutName = layoutName.value,
                packageName = packageName.value,
                language.value
            )
        }
    }

fun createLayoutName(className: String): String = StringBuffer().run {
    append("activity_")
    className.toCharArray().forEach {
        if (it.isUpperCase()) {
            if (isNotEmpty()) {
                append("_")
            }
            append(it.toLowerCase())
        } else {
            append(it)
        }
    }
    toString()
}