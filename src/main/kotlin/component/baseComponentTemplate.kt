package component

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

/**
 * Author: YulinZhang
 * Date: 2022/8/20 14:23
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * YulinZhang 2022/8/20 1.0 首次创建
 */
val baseComponentTemplate
    get() = template {
        name = "base component"
        description = """
            适用于创建component的模板
        """.trimIndent()
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val componentName = stringParameter {
            name = """
                组件名
            """.trimIndent()
            default = ""
        }

        widgets(
            TextFieldWidget(componentName)
        )

        recipe = { data: TemplateData ->
            baseComponentRecipe(
                moduleData = data as ModuleTemplateData,
                packageName = "",
                componentName = componentName.value
            )
        }
    }