package com.spring.test

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class SpringBDDTest(body: FeatureSpec.() -> Unit = {}) : BaseBDDSpec(body) {
  override fun extensions() = listOf(SpringExtension)
}
