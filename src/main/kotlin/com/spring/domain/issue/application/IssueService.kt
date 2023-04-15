package com.spring.domain.issue.application

import com.spring.domain.issue.application.model.IssueCreateParams
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.infrastructure.IssueRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(private val issueRepository: IssueRepository) {

  @Transactional
  fun create(params: IssueCreateParams): IssueResult {
    val issue = issueRepository.save(params.toIssue())

    return IssueResult.from(issue)
  }
}
