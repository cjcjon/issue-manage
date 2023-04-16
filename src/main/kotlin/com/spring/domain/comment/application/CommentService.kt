package com.spring.domain.comment.application

import com.spring.domain.comment.application.model.CommentResult
import com.spring.domain.comment.domain.Comment
import com.spring.domain.comment.infrastructure.CommentRepository
import com.spring.domain.issue.infrastructure.IssueRepository
import com.spring.global.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
  private val commentRepository: CommentRepository,
  private val issueRepository: IssueRepository,
) {

  @Transactional
  fun create(issueId: Long, userId: Long, username: String, body: String): CommentResult {
    val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈를 찾을 수 없습니다")
    val comment = Comment(
      issue = issue,
      userId = userId,
      username = username,
      body = body,
    )

    issue.writeComment(comment)
    return CommentResult.from(commentRepository.save(comment))
  }

  @Transactional
  fun edit(id: Long, userId: Long, body: String): CommentResult {
    val comment = commentRepository.findByIdAndUserId(id, userId)?.updateBody(body)
      ?: throw NotFoundException("코멘트를 찾을 수 없습니다")

    return CommentResult.from(comment)
  }
}
