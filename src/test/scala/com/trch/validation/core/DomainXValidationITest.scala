package com.trch.validation.core

import core.{Failure, OK, Validation}
import org.junit.Assert._
import org.junit.Test
/**
 * Created by Tom on 21/06/2015.
 */

class DomainXValidationITest {

  @Test
  def testDomainX = {
    //Domain for application x
    case class User(name:String, age:Int)
    case class TooYoung() extends Failure
    case class BadName() extends Failure

    //Validations
    val tooYoung = (user : User) => if(user.age < 18) TooYoung() else OK()
    val badName = (user : User) => if(user.name.equals("tom")) BadName() else OK()

    assertEquals(List(TooYoung()), Validation(tooYoung).validate(User("tom", 17)))
    assertEquals(List(), Validation(tooYoung).validate(User("tom", 27)))

    assertEquals(List(BadName()), Validation(badName).validate(User("tom", 17)))
    assertEquals(List(), Validation(badName).validate(User("thomas", 17)))

    assertEquals(List(BadName(), TooYoung()), Validation(badName, tooYoung).validate(User("tom", 17)))
    assertEquals(List(BadName()), Validation(badName, tooYoung).validate(User("tom", 27)))
    assertEquals(List(TooYoung()), Validation(badName, tooYoung).validate(User("thomas", 17)))
    assertEquals(List(), Validation(badName, tooYoung).validate(User("thomas", 27)))


  }
}
