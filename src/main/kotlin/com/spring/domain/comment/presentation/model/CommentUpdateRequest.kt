package com.spring.domain.comment.presentation.model

data class CommentUpdateRequest(
  val userId: Long,
  val body: String,
)
