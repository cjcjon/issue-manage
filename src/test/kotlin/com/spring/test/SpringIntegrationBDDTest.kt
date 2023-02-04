package com.spring.test

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
abstract class SpringIntegrationBDDTest(body: FeatureSpec.() -> Unit = {}) : FeatureSpec(body) {
  override fun extensions() = listOf(SpringExtension)
}
