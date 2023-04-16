package com.spring.domain.issue.domain

import com.spring.domain.comment.domain.Comment
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType
import com.spring.global.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.OneToMany

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

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
  private val mutableComments: MutableList<Comment> = mutableListOf()
  val comments: List<Comment> get() = mutableComments.toList()

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

  fun writeComment(comment: Comment) {
    mutableComments.add(comment)
  }
}
