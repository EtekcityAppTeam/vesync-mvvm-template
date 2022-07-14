package com.github.houweiandroid.vesyncmvvmtemplate.listeners

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.github.houweiandroid.vesyncmvvmtemplate.services.MyProjectService

internal class MyProjectManagerListener : ProjectManagerListener {

    companion object {
        var projectInstance: Project? = null
    }

    override fun projectOpened(project: Project) {
        projectInstance = project
        project.service<MyProjectService>()
    }

    override fun projectClosed(project: Project) {
        projectInstance = null
        super.projectClosed(project)
    }
}
