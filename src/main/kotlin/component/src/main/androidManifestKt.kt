package component.src.main

fun androidManifestKt(

) = """
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.vesync.comp_example">

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <application android:networkSecurityConfig="@xml/network_security_config">
        <activity
            android:name="com.vesync.CompMainActivity"
            android:exported="false" />
    </application>

</manifest>    
""".trimIndent()