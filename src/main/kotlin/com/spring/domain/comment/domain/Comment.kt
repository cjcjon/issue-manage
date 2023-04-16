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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "issue_id")
  val issue: Issue,
  @Column(nullable = false)
  val userId: Long,
  @Column(nullable = false)
  val username: String,
  @Column(nullable = false)
  val body: String,
) : BaseEntity()
