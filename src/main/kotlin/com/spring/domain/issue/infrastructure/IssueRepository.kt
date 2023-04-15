package com.spring.domain.issue.infrastructure

import com.spring.domain.issue.domain.Issue
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long>
