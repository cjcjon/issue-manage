package com.spring

import com.spring.test.SpringIntegrationBDDTest
import io.kotest.matchers.shouldBe

class SpringApplicationTests : SpringIntegrationBDDTest({
  feature("스프링을 실행한다") {
    scenario("스프링의 context가 정상적으로 불러와진다") {
      true shouldBe true
    }
  }
})
