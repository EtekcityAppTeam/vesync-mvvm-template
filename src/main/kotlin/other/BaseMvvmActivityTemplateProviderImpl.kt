package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.mvvm.activity.baseMvvmActivityTemplate

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
        baseMvvmActivityTemplate
    )
}