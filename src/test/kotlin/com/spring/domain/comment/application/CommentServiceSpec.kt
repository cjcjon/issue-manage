package com.spring.domain.comment.application

import com.spring.domain.comment.application.model.CommentResult
import com.spring.domain.comment.infrastructure.CommentRepository
import com.spring.domain.issue.domain.Issue
import com.spring.domain.issue.infrastructure.IssueRepository
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType
import com.spring.global.exception.NotFoundException
import com.spring.test.SpringBDDSpec
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe

class CommentServiceSpec(
  issueRepository: IssueRepository,
  commentRepository: CommentRepository,
) : SpringBDDSpec({

  val sut = CommentService(commentRepository, issueRepository)

  feature("코멘트를 생성한다") {
    scenario("사용자가 이슈에 코멘트를 입력한다") {
      // given
      val issue = createIssue()
      val issueId = issueRepository.save(issue).id!!

      val userId = 1L
      val userName = "me"
      val body = "test comment"

      // when
      val result = sut.create(issueId, userId, userName, body)

      // then
      result shouldBe CommentResult(issueId, userId, userName, body)
    }

    scenario("사용자가 삭제된 이슈에 코멘트를 입력할경우 오류가 발생한다") {
      shouldThrowExactly<NotFoundException> {
        sut.create(1, 1L, "me", "test comment")
      }
    }
  }
}) {
  companion object {
    private fun createIssue(
      summary: String = "summary",
      description: String = "description",
      type: IssueType = IssueType.TASK,
      priority: IssuePriority = IssuePriority.LOW,
      status: IssueStatus = IssueStatus.TODO,
    ) = Issue(
      summary = summary,
      description = description,
      type = type,
      priority = priority,
      status = status,
    )
  }
}
