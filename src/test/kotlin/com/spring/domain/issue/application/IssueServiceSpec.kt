package com.spring.domain.issue.application

import com.spring.domain.issue.application.model.IssueParams
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.domain.Issue
import com.spring.domain.issue.infrastructure.IssueRepository
import com.spring.domain.issue.model.IssuePriority
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.model.IssueType
import com.spring.global.exception.NotFoundException
import com.spring.test.SpringBDDSpec
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldBeSameSizeAs
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields

class IssueServiceSpec(repository: IssueRepository) : SpringBDDSpec({

  val sut = IssueService(repository)

  feature("이슈를 생성한다") {
    scenario("사용자가 이슈 정보를 입력하면 해당 정보를 기반으로 이슈를 생성한다") {
      // given
      val params = IssueParams(
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

  feature("이슈를 전체 조회한다") {
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

  feature("이슈를 상세 조회한다") {
    scenario("입력된 아이디와 일치하는 이슈를 상세 조회한다") {
      // given
      val issue = createIssue()
      val issueId = repository.save(issue).id!!

      // when
      val result = sut.find(issueId)

      // then
      result shouldBe toIssueResult(issue)
    }

    scenario("입력된 아이디와 일치하지 않으면 오류가 발생한다") {
      shouldThrowExactly<NotFoundException> {
        sut.find(1)
      }
    }
  }

  feature("이슈를 전체 수정한다") {
    val params = IssueParams(
      "updated",
      "updated",
      IssueType.BUG,
      IssuePriority.HIGH,
      IssueStatus.IN_PROGRESS,
    )

    scenario("입력된 정보로 이미 존재하는 이슈를 덮어씌운다") {
      // given
      val issue = createIssue()
      val issueId = repository.save(issue).id!!

      // when
      val result = sut.edit(issueId, params)

      // then
      result shouldBe toIssueResult(toIssue(params))
    }

    scenario("존재하지 않는 이슈를 수정할경우 오류가 발생한다") {
      shouldThrowExactly<NotFoundException> {
        sut.edit(1, params)
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

    private fun toIssue(params: IssueParams) = Issue(
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
      this.zip(other).shouldForAll {
        it.first shouldBe it.second
      }
    }
  }
}
