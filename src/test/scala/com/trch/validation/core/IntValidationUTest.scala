package com.trch.validation.core

import com.trch.validation.core.IntValidation.{IntToSmall, IntToBig}
import org.junit.Assert._
import org.junit.Test
/**
 * Created by Tom on 21/06/2015.
 */
class IntValidationUTest {

  @Test
  def test(): Unit = {
    assertEquals(IntToBig(1), IntValidation.noGreaterThan(1)(2))
    assertEquals(OK(), IntValidation.noGreaterThan(1)(1))

    assertEquals(IntToSmall(1), IntValidation.noLessThan(1)(0))
    assertEquals(OK(), IntValidation.noLessThan(1)(1))
  }
}
