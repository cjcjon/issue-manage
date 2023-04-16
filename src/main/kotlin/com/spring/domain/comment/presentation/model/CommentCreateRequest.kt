package com.spring.domain.comment.presentation.model

data class CommentCreateRequest(
  val userId: Long,
  val username: String,
  val body: String,
)
