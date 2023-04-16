package com.spring.domain.issue.application

import com.spring.domain.issue.application.model.IssueParams
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.infrastructure.IssueRepository
import com.spring.domain.issue.model.IssueStatus
import com.spring.global.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(private val issueRepository: IssueRepository) {

  @Transactional
  fun create(params: IssueParams): IssueResult {
    val issue = issueRepository.save(params.toIssue())

    return IssueResult.from(issue)
  }

  @Transactional(readOnly = true)
  fun findAll(status: IssueStatus): List<IssueResult> =
    issueRepository.findAllByStatusOrderByCreatedAtDesc(status).map(IssueResult::from)

  @Transactional(readOnly = true)
  fun find(id: Long): IssueResult {
    val issue = findIssue(id)

    return IssueResult.from(issue)
  }

  @Transactional
  fun edit(id: Long, params: IssueParams): IssueResult {
    val issue = findIssue(id)

    return IssueResult.from(
      issue.update(
        summary = params.summary,
        description = params.description,
        type = params.type,
        priority = params.priority,
        status = params.status,
      ),
    )
  }

  @Transactional
  fun delete(id: Long) {
    findIssue(id)

    issueRepository.deleteById(id)
  }

  private fun findIssue(id: Long) =
    issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다")
}
