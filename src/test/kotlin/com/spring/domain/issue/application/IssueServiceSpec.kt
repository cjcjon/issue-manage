package com.spring.domain.issue.application

import com.spring.domain.issue.application.model.IssueCreateParams
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.domain.Issue
import com.spring.domain.issue.infrastructure.IssueRepository
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType
import com.spring.test.SpringBDDTest
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields

class IssueServiceSpec(repository: IssueRepository) : SpringBDDTest({

  val sut = IssueService(repository)

  feature("이슈를 생성한다") {
    scenario("사용자가 이슈 정보를 입력하면 해당 정보를 기반으로 이슈를 생성한다") {
      // given
      val params = IssueCreateParams(
        "test",
        "test",
        IssueType.TASK,
        IssuePriority.LOW,
        IssueStatus.TODO,
      )

      // when
      val result = sut.create(params)

      result shouldBe toIssueResult(toIssue(params))
    }
  }
}) {
  companion object {
    fun toIssue(params: IssueCreateParams) = Issue(
      summary = params.summary,
      description = params.description,
      type = params.type,
      priority = params.priority,
      status = params.status,
    )

    fun toIssueResult(issue: Issue) = IssueResult(
      summary = issue.summary,
      description = issue.description,
      type = issue.type,
      priority = issue.priority,
      status = issue.status,
      createdAt = issue.createdAt,
      updatedAt = issue.updatedAt,
    )

    infix fun IssueResult.shouldBe(other: IssueResult) =
      this.shouldBeEqualToIgnoringFields(
        other,
        IssueResult::createdAt,
        IssueResult::updatedAt,
      )
  }
}
