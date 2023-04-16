package com.spring.domain.issue.presentation.model

import com.spring.domain.issue.application.model.IssueParams
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType

data class IssueRequest(
  val summary: String,
  val description: String,
  val type: IssueType,
  val priority: IssuePriority,
  val status: IssueStatus,
) {
  fun toParams() = IssueParams(
    summary = summary,
    description = description,
    type = type,
    priority = priority,
    status = status,
  )
}
