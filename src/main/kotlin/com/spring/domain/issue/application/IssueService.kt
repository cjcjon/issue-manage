package com.spring.domain.issue.application

import com.spring.domain.issue.application.model.IssueCreateParams
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.infrastructure.IssueRepository
import com.spring.domain.issue.model.IssueStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(private val issueRepository: IssueRepository) {

  @Transactional
  fun create(params: IssueCreateParams): IssueResult {
    val issue = issueRepository.save(params.toIssue())

    return IssueResult.from(issue)
  }

  @Transactional(readOnly = true)
  fun findAll(status: IssueStatus): List<IssueResult> =
    issueRepository.findAllByStatusOrderByCreatedAtDesc(status).map(IssueResult::from)
}
