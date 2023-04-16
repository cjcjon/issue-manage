package com.spring.domain.comment.domain

import com.spring.domain.issue.domain.Issue
import com.spring.global.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "Comments")
class Comment(
  issue: Issue,
  userId: Long,
  username: String,
  body: String,
) : BaseEntity() {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "issue_id")
  val issue: Issue = issue

  @Column(nullable = false)
  val userId: Long = userId

  @Column(nullable = false)
  val username: String = username

  @Column(nullable = false)
  var body: String = body
    private set

  fun updateBody(body: String): Comment {
    this.body = body

    return this
  }
}
