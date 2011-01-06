package org.nopalsoft.mercury.web

import org.nopalsoft.mercury.domain.Project
import org.nopalsoft.mercury.domain.Milestone
import org.nopalsoft.mercury.domain.Issue

class MilestoneController {
  def issueService

  def index = {
    if (!session.project) {
      redirect(controller: 'home')

    } else {
      def issues
      def milestone = null
      def project = Project.load(session.project.id)
      def id = params.long('id')
      if (id) {
        milestone = Milestone.get(id)
        issues = milestone.issues.findAll{ it.status.code != 'resolved' && it.status.code != 'closed'}
      } else if(project.currentMilestone && params.id != 'pending') {
        milestone = project.currentMilestone
        issues = project.currentMilestone.issues.findAll{ it.status.code != 'resolved' && it.status.code != 'closed'}
      }else {
        issues = issueService.getIssuesNotInMilestone(project)
      }

      def milestones = Milestone.findAllByProject(project)
      [milestone: milestone, milestones: milestones, issues: issues]
    }
  }

  def addIssuesToMilestone = {
    def milestone = Milestone.load(params.milestone)
    def issueIds = params['issue']
    //Se valida si se selecciono uno o varios
    if (issueIds instanceof String) {
      addIssueToMilestone(issueIds, milestone)
    } else {
      issueIds.each {
        addIssueToMilestone(it, milestone)
      }
    }
    flash.message = "Si se pudo"
    redirect action: 'index', id: params.id
  }

  private def addIssueToMilestone(it, Milestone milestone) {
    def issue = Issue.get(it as long)
    milestone.addToIssues(issue)
    milestone.save()
  }

  def moveUp = {
    def milestone = Milestone.load(params.milestone)
    def issue = Issue.load(params.issue)
    milestone.moveUp issue
    redirect action: 'index', id: params.milestone
  }

  def moveDown = {
    def milestone = Milestone.load(params.milestone)
    def issue = Issue.load(params.issue)
    milestone.moveDown issue
    redirect action: 'index', id: params.milestone
  }

  def create = {
    def milestone = new Milestone()
    def project = Project.load(session.project.id)
    milestone.project = project
    milestone.name = params.name
    milestone.startDate = Date.parse("dd/MM/yyyy", params.startDate)
    milestone.endDate = Date.parse("dd/MM/yyyy", params.endDate)
    milestone.save()

    redirect action: 'index', id: params.actualMilestone
  }
}
