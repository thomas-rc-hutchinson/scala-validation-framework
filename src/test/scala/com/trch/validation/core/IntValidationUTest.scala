package com.trch.validation.core

import core.IntValidation._
import core.{IntValidation, OK}
import org.junit.Assert._
import org.junit.Test
/**
 * Created by Tom on 21/06/2015.
 */
class IntValidationUTest {

  @Test
  def test(): Unit = {
    assertEquals(IntToBig(), IntValidation.noGreaterThan(1)(2))
    assertEquals(OK(), IntValidation.noGreaterThan(1)(1))

    assertEquals(IntToSmall(), IntValidation.noLessThan(1)(0))
    assertEquals(OK(), IntValidation.noLessThan(1)(1))
  }
}
