package component.gradle

fun compGradleKt(

) = """
apply from: "https://raw.githubusercontent.com/EtekcityAppTeam/AndroidVesyncEN-GlobalBuild/develop/base.gradle"
apply from: "https://raw.githubusercontent.com/EtekcityAppTeam/AndroidVesyncEN-GlobalBuild/develop/component-base-publish.gradle"
apply plugin: 'org.jetbrains.kotlin.android'

android {
    resourcePrefix "example_"

    dataBinding {
        enabled = true
    }

}
kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.getName())
    }
}
dependencies {

    implementation 'com.vesync:lib-resource:1.0.1-alpha12'
    implementation 'com.vesync:lib-common-util:1.0.0-alpha07'
    implementation 'com.vesync:lib-probe:1.0.6-alpha14'
    implementation 'com.vesync:lib-router:3.2.18-alpha04'
    implementation 'com.vesync:lib-cloud-api:1.0.2-alpha03'
    testImplementation 'junit:junit:4.12'
    kapt 'com.vesync:lib-cloud-api-compiler:1.0.2-alpha03'
    kapt 'com.vesync:lib-event-compiler:1.0.0-alpha01'
}
""".trimIndent()