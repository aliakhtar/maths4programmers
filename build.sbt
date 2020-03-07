name := "maths4programmers"

version := "0.1"

scalaVersion := "2.13.1"


libraryDependencies ++= Seq(
    //Logging:
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" % "scala-logging_2.13" % "3.9.2",

    //Breeze:
    "org.scalanlp" %% "breeze" % "1.0",

    // Native libraries are not included by default. add this if you want them
    // Native libraries greatly improve performance, but increase jar sizes.
    // It also packages various blas implementations, which have licenses that may or may not
    // be compatible with the Apache License. No GPL code, as best I know.
    "org.scalanlp" %% "breeze-natives" % "1.0",

    // The visualization library is distributed separately as well.
    // It depends on LGPL code
    "org.scalanlp" %% "breeze-viz" % "1.0",


    //test
    "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)


initialCommands :=
"""
      |import breeze.linalg._
      |import breeze.plot._
      |import breeze.numerics._
      |""".stripMargin