package component.src.main.res.layout

fun componentMainActivityLayoutXml(
    componentName: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.vesync.${componentName}MainActivity">

    <androidx.appcompat.widget.AppCompatTextView
        android:gravity="center"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="Hello VeSync"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()