package other.mvvm.viewmodel

fun baseMvvmViewModelKt(
    viewModelName: String
) = """
package com.vesync.viewmodel

import android.app.Application
import com.vesync.base.BaseViewModel

class ${viewModelName}(application: Application) : BaseViewModel(application) {

}
"""