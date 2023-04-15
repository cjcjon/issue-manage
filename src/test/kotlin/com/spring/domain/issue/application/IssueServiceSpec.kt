package com.spring.domain.issue.application

import com.spring.domain.issue.application.model.IssueCreateParams
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.domain.Issue
import com.spring.domain.issue.infrastructure.IssueRepository
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType
import com.spring.test.SpringBDDSpec
import io.kotest.matchers.collections.shouldBeSameSizeAs
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields

class IssueServiceSpec(repository: IssueRepository) : SpringBDDSpec({

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

      // then
      result shouldBe toIssueResult(toIssue(params))
    }
  }

  feature("이슈를 조회한다") {
    scenario("상태와 일치하는 이슈를 생성된 순서의 역순으로 전체 조회한다") {
      // given
      val issue1 = createIssue(summary = "summary1", status = IssueStatus.TODO)
      val issue2 = createIssue(summary = "summary2", status = IssueStatus.IN_PROGRESS)
      val issue3 = createIssue(summary = "summary3", status = IssueStatus.TODO)
      val issue4 = createIssue(summary = "summary4", status = IssueStatus.RESOLVED)

      repository.save(issue1)
      repository.save(issue2)
      repository.save(issue3)
      repository.save(issue4)

      // when
      val result = sut.findAll(IssueStatus.TODO)

      // then
      result shouldBe listOf(toIssueResult(issue3), toIssueResult(issue1))
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

    private fun toIssue(params: IssueCreateParams) = Issue(
      summary = params.summary,
      description = params.description,
      type = params.type,
      priority = params.priority,
      status = params.status,
    )

    private fun toIssueResult(issue: Issue) = IssueResult(
      summary = issue.summary,
      description = issue.description,
      type = issue.type,
      priority = issue.priority,
      status = issue.status,
      createdAt = issue.createdAt,
      updatedAt = issue.updatedAt,
    )

    private infix fun IssueResult.shouldBe(other: IssueResult) =
      this.shouldBeEqualToIgnoringFields(
        other,
        IssueResult::createdAt,
        IssueResult::updatedAt,
      )

    private infix fun List<IssueResult>.shouldBe(other: List<IssueResult>) {
      this.shouldBeSameSizeAs(other)
      this.forEachIndexed { idx, issueResult ->
        issueResult shouldBe other[idx]
      }
    }
  }
}
