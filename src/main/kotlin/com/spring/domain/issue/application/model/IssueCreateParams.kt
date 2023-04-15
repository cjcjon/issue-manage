package com.spring.domain.issue.application.model

import com.spring.domain.issue.domain.Issue
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType

data class IssueCreateParams(
  val summary: String,
  val description: String,
  val type: IssueType,
  val priority: IssuePriority,
  val status: IssueStatus,
) {
  fun toIssue() = Issue(
    summary = summary,
    description = description,
    type = type,
    priority = priority,
    status = status,
  )
}
