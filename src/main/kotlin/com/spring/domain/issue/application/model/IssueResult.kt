package com.spring.domain.issue.application.model

import com.spring.domain.issue.domain.Issue
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType
import java.time.LocalDateTime

data class IssueResult(
  val summary: String,
  val description: String,
  val type: IssueType,
  val priority: IssuePriority,
  val status: IssueStatus,
  val createdAt: LocalDateTime?,
  val updatedAt: LocalDateTime?,
) {
  companion object {
    fun from(issue: Issue) = IssueResult(
      summary = issue.summary,
      description = issue.description,
      type = issue.type,
      priority = issue.priority,
      status = issue.status,
      createdAt = issue.createdAt,
      updatedAt = issue.updatedAt,
    )
  }
}
