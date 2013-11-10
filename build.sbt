seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

name := "hellocv"

version := "1.0"

organization := "org.birchavenue"

scalaVersion := "2.10.3"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= Seq("org.scala-lang" % "scala-swing" % "2.10.3")

mainClass in (Compile,run) := Some("hellocv.Main")

mappings in oneJar <++= baseDirectory map { b => Seq((b / "binlib/linux64/libopencv_java247.so") -> "binlib/linux64/libopencv_java247.so", 
                                                     (b / "binlib/windows32/opencv_java247.dll") -> "binlib/windows32/opencv_java247.dll",
                                                     (b / "binlib/windows64/opencv_java247.dll") -> "binlib/windows64/opencv_java247.dll",
                                                     (b / "binlib/macosx/libopencv_java247.dylib") -> "binlib/macosx/libopencv_java247.dylib")}
