# scala-validation-framework

## Motivation

Often or not I read Java code which couples validation with code that acts upon the results 
e.g.
```Java
public void assertSelectionIdValid(int id){
        if(id > 5) throw new SelectionIdToBigException();
     	if(id < 1) throw new SelectionIdToSmallException();     
    }
    
assertSelectionIdValid(0)    
```

Often or not the application will need to perform the same validation but in a different context e.g. getting a value by selectionId and inserting an item that contains a selectionId. In this instance the error handling is coupled with the validation. 

## Solution

The solution I developed for this is something that decouples the validation from the handling of the validation results. To use this framework a Validation object needs to be constructed with var-args of lambdas that each take T (in the selection example an Int) and return a Result. These lambdas will be used to validate the input (selectionId). Here is the code

```Scala
val selectionId = 0
    val validator = Validation(noGreaterThan(5), noLessThan(1))
    val failures : List[Failure] = validator.validate(selectionId);
    failures.map {
      case IntToBig() => println(s"selectionId [$selectionId] to big")
      case IntToSmall() => println(s"selectionId [$selectionId] to small")
    }
```





