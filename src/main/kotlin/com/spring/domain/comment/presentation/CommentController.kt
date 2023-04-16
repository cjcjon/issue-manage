package com.spring.domain.comment.presentation

import com.spring.domain.comment.application.CommentService
import com.spring.domain.comment.application.model.CommentResult
import com.spring.domain.comment.presentation.model.CommentRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(private val commentService: CommentService) {

  @PostMapping
  fun create(
    @PathVariable issueId: Long,
    @RequestBody request: CommentRequest,
  ): ResponseEntity<CommentResult> {
    val result = commentService.create(issueId, request.userId, request.username, request.body)

    return ResponseEntity(result, HttpStatus.OK)
  }
}
