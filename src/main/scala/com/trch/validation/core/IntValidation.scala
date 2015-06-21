package com.trch.validation.core

/**
 * Created by Tom on 21/06/2015.
 */
object IntValidation {

  final case class IntToBig(max:Int) extends Failure;
  final case class IntToSmall(min:Int) extends Failure;

  final def noGreaterThan(size : Int) : (Int => Result) = (int : Int) => if(int > size) IntToBig(size) else OK()
  final def noLessThan(size : Int) = (int : Int) => if(int < size) IntToSmall(size) else OK()


}
