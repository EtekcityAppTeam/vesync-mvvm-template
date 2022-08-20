package mvvm.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import mvvm.layout.baseMvvmLayout
import mvvm.viewmodel.baseMvvmViewModelKt

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
    description: String
) {

    val (projectData, srcOut, resOut) = moduleData

    generateManifest(
        moduleData = moduleData,
        activityClass = activityName,
        packageName = packageName,
        isLauncher = false,
        hasNoActionBar = false,
        generateActivityTitle = false
    )

    // save activity
    val mvvmActivity = baseMvvmActivityKt(
        applicationPackage = projectData.applicationPackage,
        activityName = activityName,
        bindingName = bindingName,
        viewModelName = viewModelName,
        layoutName = layoutName,
        packageName = packageName,
        description = description
    )
    save(mvvmActivity, srcOut.resolve("${activityName}.kt"))
    // save layout
    val mvvmLayout = baseMvvmLayout(
        viewModelName = viewModelName,
        packageName = packageName
    )
    save(mvvmLayout, resOut.resolve("layout/${layoutName}.xml"))
    // save viewmodel
    val mvvmViewModel = baseMvvmViewModelKt(
        viewModelName = viewModelName,
        packageName = packageName
    )
    save(mvvmViewModel, srcOut.resolve("${viewModelName}.kt"))
}