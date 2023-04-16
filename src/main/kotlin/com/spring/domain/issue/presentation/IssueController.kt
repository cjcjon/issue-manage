package com.spring.domain.issue.presentation

import com.spring.domain.issue.application.IssueService
import com.spring.domain.issue.application.model.IssueResult
import com.spring.domain.issue.model.IssueStatus
import com.spring.domain.issue.presentation.model.IssueRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(private val issueService: IssueService) {

  @PostMapping
  fun create(@RequestBody request: IssueRequest): ResponseEntity<IssueResult> {
    val result = issueService.create(request.toParams())

    return ResponseEntity(result, HttpStatus.OK)
  }

  @GetMapping
  fun findAll(
    @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus,
  ): ResponseEntity<List<IssueResult>> {
    val result = issueService.findAll(status)

    return ResponseEntity(result, HttpStatus.OK)
  }

  @GetMapping("/{id}")
  fun find(@PathVariable id: Long): ResponseEntity<IssueResult> {
    val result = issueService.find(id)

    return ResponseEntity(result, HttpStatus.OK)
  }

  @PutMapping("/{id}")
  fun edit(
    @PathVariable id: Long,
    @RequestBody request: IssueRequest,
  ): ResponseEntity<IssueResult> {
    val result = issueService.edit(id, request.toParams())

    return ResponseEntity(result, HttpStatus.OK)
  }
}
