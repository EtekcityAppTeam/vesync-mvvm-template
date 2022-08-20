package mvvm.layout

fun baseMvvmLayoutXml(
    viewModelName: String,
    packageName: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
    
        <variable
            name="${createLayoutViewModelName(viewModelName)}"
            type="${packageName}.${viewModelName}" />

    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </androidx.constraintlayout.widget.ConstraintLayout>
    
</layout>  
"""

fun createLayoutViewModelName(viewModelName: String): String = StringBuffer().run {
    viewModelName.forEachIndexed { index, c ->
        if (index == 0) append(c.toLowerCase()) else append(c)
    }
    toString()
}