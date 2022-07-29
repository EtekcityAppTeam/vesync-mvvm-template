package other.mvvm.fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import other.createBindingName
import other.createLayoutName
import other.mvvm.BaseMvvmConstant

/**
 * Author: YulinZhang
 * Date: 2022/7/26 13:54
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * YulinZhang 2022/7/26 1.0 首次创建
 */
val baseMvvmFragmentTemplate
    get() = template {
        name = "base mvvm fragment"
        description = """
            适用于创建mvvm fragment的模板
            请选择相对路径名，fragment与viewModel文件会被同时创建在此路径名下
            创建完成后需要对新产生的文件编译
        """.trimIndent()
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val componentName = stringParameter {
            name = """
                组件名
                请输入组件名（用于命名后续生成的文件），如Store，没有则不用输入
            """.trimIndent()
            default = ""
        }

        val fragmentName = stringParameter {
            name = """
                fragment名
                请输入fragment名称，请不要包含组件前缀，请不要包含'fragment'后缀
            """.trimIndent()
            default = "Main"
            help = "请输入名称，请不要包含前缀名，请不要不包含'Fragment'"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
        }

        val description = stringParameter {
            name = """
                文件描述
                请输入该文件的描述，作为文件头部'Description'属性
            """.trimIndent()
            default = ""
            help = "请输入该文件的描述，作为文件头部'Description'属性"
        }

        val layoutName = stringParameter {
            name = """
                布局名
                请输入xml布局文件名，默认自动生成
            """.trimIndent()
            default = "activity_"
            help = "请输入布局名，默认自动生成"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { createLayoutName(componentName.value, BaseMvvmConstant.PREFIX_LAYOUT_FRAGMENT, fragmentName.value) }
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

        widgets(
            TextFieldWidget(componentName),
            TextFieldWidget(fragmentName),
            TextFieldWidget(description),
            TextFieldWidget(layoutName),
            TextFieldWidget(packageName)
        )

        recipe = { data: TemplateData ->
            baseMvvmFragmentRecipe(
                moduleData = data as ModuleTemplateData,
                packageName = packageName.value,
                fragmentName = "${componentName.value}${fragmentName.value}${BaseMvvmConstant.POSTFIX_FRAGMENT}",
                layoutName = layoutName.value,
                viewModelName = "${componentName.value}${fragmentName.value}${BaseMvvmConstant.POSTfIX_VIEWMODEL}",
                bindingName = createBindingName(layoutName.value),
                description = description.value
            )
        }
    }