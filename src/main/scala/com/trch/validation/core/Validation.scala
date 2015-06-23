package com.trch.validation.core

/**
 * Created by Tom on 21/06/2015.
 */
class Validation[T](test : (T => Result)*) {

  def validate(value : T) : List[Failure] =
    test.map(t => t(value)).
      filter(_.isInstanceOf[Failure]).
      map(_.asInstanceOf[Failure]).toList
}

abstract class Result();
abstract class Failure() extends Result;
final case class OK() extends Result;

object Validation {
  def apply[T](test : (T => Result)*) = new Validation(test : _*)
  
  def union[T](test : (T => Result)*) : (T => Result) =
    test.reduce((firstTest,secondTest) => (value : T) => firstTest(value) match {
      case _:OK => secondTest(value)
      case failure:Failure => failure
    })
  
}
