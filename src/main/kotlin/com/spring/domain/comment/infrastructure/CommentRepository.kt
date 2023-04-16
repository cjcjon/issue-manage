package com.spring.domain.comment.infrastructure

import com.spring.domain.comment.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>
