package io.icednut.study

import cats.Id
import org.scalatest.funsuite.AnyFunSuite

class RepositoryRankTest extends AnyFunSuite {

  test("테스트 코드를 작성해봅시다.") {
    val value = new RepositoryRank[Id].getPoint("icednut/typeclass-refactoring")

    assert(value == 0)
  }
}
