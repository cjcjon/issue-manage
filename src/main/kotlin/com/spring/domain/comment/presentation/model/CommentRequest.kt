package com.spring.domain.comment.presentation.model

data class CommentRequest(
  val userId: Long,
  val username: String,
  val body: String,
)
