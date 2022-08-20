package component

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

/**
 * Author: YulinZhang
 * Date: 2022/8/11 17:18
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * YulinZhang 2022/8/11 1.0 首次创建
 */
class BaseComponentTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        // component的模板
        baseComponentTemplate
    )
}