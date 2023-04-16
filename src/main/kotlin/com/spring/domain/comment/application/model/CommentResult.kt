package com.spring.domain.comment.application.model

import com.spring.domain.comment.domain.Comment

data class CommentResult(
  val issueId: Long,
  val userId: Long,
  val username: String,
  val body: String,
) {
  companion object {
    fun from(comment: Comment) = CommentResult(
      issueId = comment.issue.id!!,
      userId = comment.userId,
      username = comment.username,
      body = comment.body,
    )
  }
}
