package other.mvvm.activity

import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import other.mvvm.layout.baseMvvmLayout
import other.mvvm.viewmodel.baseMvvmViewModelKt

/**
 * Author: YulinZhang
 * Date: 2022/7/6 16:42
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * YulinZhang 2022/7/6 1.0 首次创建
 */
fun RecipeExecutor.baseMvvmActivityRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    activityName: String,
    layoutName: String,
    viewModelName: String,
    bindingName: String,
    language: Language
) {

    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = language.extension

    generateManifest(
        moduleData = moduleData,
        activityClass = activityName,
        packageName = packageName,
        isLauncher = false,
        hasNoActionBar = false,
        generateActivityTitle = true
    )

    when (language) {
        Language.Kotlin -> {
            // save activity
            val mvvmActivity = baseMvvmActivityKt(
                applicationPackage = projectData.applicationPackage,
                activityName = activityName,
                bindingName = bindingName,
                viewModelName = viewModelName,
                layoutName = layoutName,
                packageName = packageName
            )
            save(mvvmActivity, srcOut.resolve("activity/${activityName}.${ktOrJavaExt}"))
            // save layout
            val mvvmLayout = baseMvvmLayout(
                viewModelName = viewModelName,
                packageName = packageName
            )
            save(mvvmLayout, resOut.resolve("layout/${layoutName}.xml"))
            // save viewmodel
            val mvvmViewModel = baseMvvmViewModelKt(viewModelName = viewModelName)
            save(mvvmViewModel, srcOut.resolve("viewmodel/${viewModelName}.${ktOrJavaExt}"))
        }
        Language.Java -> {

        }
    }
}