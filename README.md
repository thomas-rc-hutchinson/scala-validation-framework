# scala-validation-framework

## Motivation

Often I read Java code which couples validation with code that acts upon the results 
e.g.
```Java
public void assertSelectionIdValid(int id){
        if(id > 5) throw new SelectionIdToBigException();
     	if(id < 1) throw new SelectionIdToSmallException();     
    }
    
assertSelectionIdValid(0)    
```

Often or not the application will need to perform the same validation but in a different context e.g. getting a value by selectionId and inserting an item that contains a selectionId. In this instance the error handling is coupled with the validation. When working on applications that require a lot of validation this approach can leave you with a lot of bloated classes and duplicate code.

## Solution

The solution I developed for this is something that decouples the validation from the handling of the validation results thus allowing you to reuse validation code. To use this framework a Validation object needs to be constructed with var-args of lambdas that each take T (in the selection example an Int) and return a Result. These lambdas will be used to validate the input (selectionId). Here is the code

```Scala
val selectionId = 0
    val validator = Validation(noGreaterThan(5), noLessThan(1))
    val failures : List[Failure] = validator.validate(selectionId);
    failures.map {
      case IntToBig(max) => println(s"selectionId [$selectionId] to big, maximum size is $max")
      case IntToSmall(min) => println(s"selectionId [$selectionId] to small, smallest size is $min")
    }
```

You can use this framework to apply validation to classes in your domain e.g.
```Scala

    //Domain for application x
    case class User(name:String, age:Int)

    //Define failures. Each one must extend Failure
    case class TooYoung() extends Failure
    case class BadName() extends Failure

    //If fail return your failure, if it passed return OK()
    val tooYoung = (user : User) => if(user.age < 18) TooYoung() else OK()
    val badName = (user : User) => if(user.name.equals("tom")) BadName() else OK()

    //Plug in your validations, call validate with the instance and assert both failures are returned
    assertEquals(List(BadName(), TooYoung()), Validation(badName, tooYoung).validate(User("tom", 17)))
```

You can easily test the validations that you write. For inspiration see the test classes that I wrote.

