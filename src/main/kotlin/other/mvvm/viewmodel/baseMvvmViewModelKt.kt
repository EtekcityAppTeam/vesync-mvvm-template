package other.mvvm.viewmodel

fun baseMvvmViewModelKt(
    viewModelName: String,
    packageName: String
) = """
package $packageName

import android.app.Application
import com.vesync.base.BaseViewModel

class ${viewModelName}(application: Application) : BaseViewModel(application) {

}
"""