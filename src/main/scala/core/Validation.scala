package core


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
case class OK() extends Result;

object Validation {
  def apply[T](test : (T => Result)*) = new Validation(test : _*)
}
