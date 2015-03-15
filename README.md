# THIS PROJECT HAS ENDED #

There're some other usefull opensource projects which can manipulate classloaders and mock static methods.

  * for Unit Testing, "powermock" recommended :)
    * https://code.google.com/p/powermock/
    * As well as mocking static methods, surprisingly, powermock can mock system classes like java.lang.String !
  * for internal classloader separation, may "kamranzafar/JCL" be worth trying.
    * https://github.com/kamranzafar/JCL
    * kamranzafar/JCL provides ".jar" classloaders which can easily customize jar class loading, and make embedding customized classloader to your project more easier.

## (old description) ##

Imagine a situation that you must write jUnit test code and pass tests for "Legacy" java classes, and these classes use some utility class which are collections of static utility methods.

You will want to replace utility classes (= static methods) with special classes you wrote for unit testing.

This project enables you to replace "Foo" class to "Bar" class under classloading mechanizm, so you don't have to replace source code directly.

note: this project supports only for "static method utility class". this sandbox mechanizm doesn't support classes which will be instanciating. (May be future release enables that.)