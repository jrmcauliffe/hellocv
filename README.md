# hellocv

A minimal cross-platform scala/sbt project for working with [OpenCV](http://www.opencv.org) 2.4.7

The project creates a single jar using the [sbt-onejar](https://github.com/sbt/sbt-onejar) plugin that has the native libraries for 32 & 64 bit Windows, 64 bit linux and 64 bit OS X.

## usage 

    sbt one-jar

    java -jar target/scala-2.10/hellocv_2.10-1.0-one-jar.jar <imgfile>

This loads the image, applys a simple blur function, then displays the image. 

