package com.spring.domain.issue.presentation

import com.spring.domain.issue.application.IssueService
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.presentation.model.IssueCreateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(private val issueService: IssueService) {

  @PostMapping
  fun create(@RequestBody request: IssueCreateRequest): ResponseEntity<IssueResult> {
    val result = issueService.create(request.toCreateParams())

    return ResponseEntity(result, HttpStatus.OK)
  }
}
