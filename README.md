Sample Gradle Config for JMH
============================

Performance tests, just like unit tests, should be kept apart from the real code.
Here's a sample of how this can be done with [gradle](http://gradle.org) and
[JMH](http://openjdk.java.net/projects/code-tools/jmh/)

A bit more detailed article can be found [in my blog](http://gvsmirnov.ru/blog/tech/2014/03/10/keeping-your-benchmarks-separate.html).

Usage:

```
$ gradle benchmarks

$ java -jar build/distributions/jmh-gradle-sample-0.0.1-benchmarks.jar
```
