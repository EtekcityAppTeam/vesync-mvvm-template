package com.github.houweiandroid.vesyncmvvmtemplate.services

import com.intellij.openapi.project.Project
import com.github.houweiandroid.vesyncmvvmtemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
