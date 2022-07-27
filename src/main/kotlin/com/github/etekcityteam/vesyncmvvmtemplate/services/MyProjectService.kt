package com.github.etekcityteam.vesyncmvvmtemplate.services

import com.intellij.openapi.project.Project
import com.github.etekcityteam.vesyncmvvmtemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
