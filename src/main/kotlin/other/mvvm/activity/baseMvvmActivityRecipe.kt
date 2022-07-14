package other.mvvm.activity

import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest

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
    activityClass: String,
    activityBinding: String,
    viewModelClass: String,
    layoutName: String,
    packageName: String,
    language: Language
) {

    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = language.extension

    generateManifest(
        moduleData = moduleData,
        activityClass = "${activityClass}Activity",
        packageName = packageName,
        isLauncher = false,
        hasNoActionBar = false,
        generateActivityTitle = true
    )

    when (language) {
        Language.Kotlin -> {
            // application package
            val mvvmActivity = baseMvvmActivityKt(
                applicationPackage = projectData.applicationPackage,
                activityClass = activityClass,
                activityBinding = activityBinding,
                viewModelClass = viewModelClass,
                layoutName = layoutName,
                packageName = packageName
            )
            // save activity
            save(mvvmActivity, srcOut.resolve("ui/${activityClass}Activity.${ktOrJavaExt}"))
        }
        Language.Java -> {

        }
    }
}