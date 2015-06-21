package core

/**
 * Created by Tom on 21/06/2015.
 */
object IntValidation {

  case class IntToBig() extends Failure;
  case class IntToSmall() extends Failure;

  final def noGreaterThan(size : Int) : (Int => Result) = (int : Int) => if(int > size) IntToBig() else OK()
  final def noLessThan(size : Int) = (int : Int) => if(int < size) IntToSmall() else OK()


}
