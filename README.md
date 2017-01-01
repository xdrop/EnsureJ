# ExpectJ
A validation framework for Java focused on a clear user friendly API with lots of pre-defined tests.

## Examples

### General

Requests start with `Ensure.that(T)` where `T` is the variable being validated. Depending on the variable type several condition checks are available and can be chained using `and()` and `or()` (Note: this boolean operators are left associative). For example:

```java
Ensure.that(T).conditionCheck().and().otherConditionCheck().or().otherConditionCheck().(...)
```

Also note that expressions are *short-circuited* ie. as soon as some condition evaluates to `false` in the chain the rest will not be evaluated.

You can also prepend a condition with `not()` which will invert it's result.

Chains are finished of with `andEval()`, or it's synonym `e()`,`eval()` to get the boolean truth of the validation, **or** with `andThrow()`, `andThrow(Class<? extends Throwable>)` or it's synonym `t()` to return `void` or thrown a exception (either the default `ParamCheckFailedException` or the custom one provided.

### Generic examples
```java
Ensure.that(3).inRange(0, 4).andEval(); // yields true
Ensure.that(3).inRange(0, 4).e(); // identical to above
Ensure.that(3).inRange(0, 4).eval(); // identical to above

Ensure.that(3).not().isPositive().andThrow(); // throws exception
Ensure.that(3).not().isPositive().andThrow("Number was not positive); // throws default exception with custom message
Ensure.that(3).not().isPositive().andThrow(MyThrowable.class); // throws MyThrowable exception
Ensure.that(3).not().isPositive().andThrow(MyThrowable.class, "Number was not positive"); // throws MyThrowable exception with custom message
```

### Integers

```java
Ensure.that(3).inRange(0, 4).andEval(); // yields true
Ensure.that(-10).isNegative.e(); // yields true
Ensure.that(3).isPositive().andEval(); // yields true
Ensure.that(3).not().isPositive().andThrow(); // throws exception
Ensure.that(3).not().isPositive().andThrow(MyThrowable.class); // throws MyThrowable exception
```

### Strings

```java
Ensure.that("HELLO").isAllUppercase.e(); // yields true
Ensure.that("hello").isAllLowercase.e(); // yields true
Ensure.that("hello").isAllAlpha.e(); // yields true
Ensure.that("hello1").isAllAlpha.e(); // yields false
Ensure.that("1112").isAllNum.e(); // yields true
Ensure.that("wasd").matches("\\w+").e() // yields true (matches regex)
```
