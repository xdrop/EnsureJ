
<img src="http://i.imgur.com/cYFDzZO.png" width="129px"/>

[![Build Status](https://travis-ci.org/xdrop/PassLock.svg?branch=master)](https://travis-ci.org/xdrop/passlock)

A validation framework for Java focused on a clear user friendly API with lots of pre-defined tests. The framework is still in a very early stage with lots of checks that can be added, so **contributions are encouraged and very welcomed!**

## Examples

### General

Requests start with `Ensure.that(T)` where `T` is the variable being validated. Depending on the variable type several condition checks are available and can be chained using `and()` and `or()` (Note: these boolean operators are left associative). For example:

```java
Ensure.that(T).conditionCheck().and().otherConditionCheck().or().otherConditionCheck().(...)
```

~~Also note that expressions are *short-circuited* ie. as soon as some condition evaluates to `false` in the chain the rest will not be evaluated.~~

You can also prepend a condition with `not()` which will invert it's result.

Chains are finished of with `andEval()`, or it's synonym `e()`,`eval()` to get the boolean truth of the validation, **or** with `andThrow()`, `andThrow(Class<? extends Throwable>)` or it's synonym `t()` to return `void` or thrown a exception (either the default `ParamCheckFailedException` or the custom one provided.

### Generic examples
```java
Ensure.that(3).inRange(0, 4).andEval(); // yields true
Ensure.that(3).inRange(0, 4).e(); // identical to above
Ensure.that(3).inRange(0, 4).eval(); // identical to above

Ensure.that(3).not().isPositive().andThrow(); // throws exception
Ensure.that(3).not().isPositive().andThrow("Number was not positive"); // throws default exception with custom message
Ensure.that(3).not().isPositive().andThrow(MyThrowable.class); // throws MyThrowable exception
Ensure.that(3).not().isPositive().andThrow(MyThrowable.class, "Number was not positive"); // throws MyThrowable exception with custom message
```

### Integers

```java
Ensure.that(3).inRange(0, 4).andEval(); // yields true
Ensure.that(-10).isNegative.eval(); // yields true
Ensure.that(-10).isNegative.e(); // yields true
Ensure.that(3).isPositive().andEval(); // yields true
Ensure.that(3).not().isPositive().andThrow(); // throws exception
Ensure.that(3).not().isPositive().andThrow(MyThrowable.class); // throws MyThrowable exception
```

### Strings

```java
Ensure.that("HELLO").isAllUppercase.eval(); // yields true

Ensure.that("hello").isAllLowercase.eval(); // yields true

Ensure.that("hello").hasOnlyLetters.eval(); // yields true
Ensure.that("hello1").hasOnlyLetters.eval(); // yields false

Ensure.that("  h").not().hasWhitespaceStart.eval(); // yields false
Ensure.that("h   ").not().hasWhitespaceEnd.eval(); // yields false

Ensure.that("  h  ").isTrimmed().eval(); // yields false

Ensure.that("1112").hasOnlyDigits.eval(); // yields true

Ensure.that("wasd").matches("\\w+").eval() // yields true (matches regex)
```

### Lists
```java
List<Object> lst;
Ensure.that(lst).all(CheckObject.equal(other)).eval(); // checks whether all list elements .equals(other)

List<Integer> lst;
Ensure.that(lst).all(CheckInt.isPositive()).eval();
Ensure.that(lst).all(CheckInt.isNegative()).eval();
Ensure.that(lst).all(CheckInt.inRange(0, 100)).eval();

// Java >= 8
Ensure.that(lst).all(x -> x > 3).eval();

// Java <= 8
Ensure.that(lst).all(new Predicate<Integer>() {
    public boolean pass(Integer in) {
        return in > 3;
    }
}).eval();
```

## Shorthand notation

`EnsureJ` can also be imported statically and used using the short `val()` notation.

```java
import static me.xdrop.ensurej.Ensure.*;

val("aaaa").isAllUppercase().e();
```
