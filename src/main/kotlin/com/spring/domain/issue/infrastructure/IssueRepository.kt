package com.spring.domain.issue.infrastructure

import com.spring.domain.issue.domain.Issue
import com.spring.domain.issue.model.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {

  fun findAllByStatusOrderByCreatedAtDesc(status: IssueStatus): List<Issue>
}
