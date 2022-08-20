package mvvm.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import mvvm.layout.baseMvvmLayout
import mvvm.viewmodel.baseMvvmViewModelKt

/**
 * Author: YulinZhang
 * Date: 2022/7/26 14:14
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * YulinZhang 2022/7/26 1.0 首次创建
 */
fun RecipeExecutor.baseMvvmFragmentRecipe(
    moduleData: ModuleTemplateData,
    packageName: String,
    fragmentName: String,
    layoutName: String,
    viewModelName: String,
    bindingName: String,
    description: String
) {

    val (projectData, srcOut, resOut) = moduleData

    // save fragment
    val mvvmFragment = baseMvvmFragmentKt(
        applicationPackage = projectData.applicationPackage,
        packageName = packageName,
        fragmentName = fragmentName,
        bindingName = bindingName,
        viewModelName = viewModelName,
        layoutName = layoutName,
        description = description
    )
    save(mvvmFragment, srcOut.resolve("${fragmentName}.kt"))
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