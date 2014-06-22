## Sample Gradle Config for JMH [![Build Status](https://travis-ci.org/gvsmirnov/jmh-gradle-sample.svg?branch=master)](https://travis-ci.org/gvsmirnov/jmh-gradle-sample)

Performance tests, just like unit tests, should be kept apart from the real code.
Here's a sample of how this can be done with [gradle](http://gradle.org) and
[JMH](http://openjdk.java.net/projects/code-tools/jmh/)

A bit more detailed article can be found [in my blog](http://gvsmirnov.ru/blog/tech/2014/03/10/keeping-your-benchmarks-separate.html).

Usage:

```
$ gradle benchmarks

$ java -jar build/libs/jmh-gradle-sample-0.0.1-benchmarks.jar
```

**UPDATE**: There's now a specialized
[jmh plugin for gradle](https://github.com/melix/jmh-gradle-plugin)
