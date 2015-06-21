package com.trch.validation.core

import com.trch.validation.core.Validation
import org.junit.Assert._
import org.junit.Test
/**
 * Created by Tom on 21/06/2015.
 */
class IntValidationITest {

  @Test
  def testWithIntValidation = {
     assertEquals(List(IntToBig()), Validation(noGreaterThan(10)).validate(11))
     assertEquals(List(), Validation(noGreaterThan(10)).validate(10))

    assertEquals(List(IntToSmall()), Validation(noLessThan(1)).validate(0))
    assertEquals(List(), Validation(noLessThan(1)).validate(1))

    assertEquals(List(IntToBig()), Validation(noGreaterThan(10), noLessThan(1)).validate(11))
    assertEquals(List(IntToSmall()), Validation(noGreaterThan(10), noLessThan(1)).validate(0))
  }

}
