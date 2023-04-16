package com.spring.domain.issue.domain

import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType
import com.spring.global.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "Issues")
class Issue(
  summary: String,
  description: String,
  type: IssueType,
  priority: IssuePriority,
  status: IssueStatus,
) : BaseEntity() {

  @Column(nullable = false)
  var summary: String = summary
    private set

  @Column(nullable = false)
  var description: String = description
    private set

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  var type: IssueType = type
    private set

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  var priority: IssuePriority = priority
    private set

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  var status: IssueStatus = status
    private set

  fun update(
    summary: String,
    description: String,
    type: IssueType,
    priority: IssuePriority,
    status: IssueStatus,
  ): Issue {
    this.summary = summary
    this.description = description
    this.type = type
    this.priority = priority
    this.status = status

    return this
  }
}
