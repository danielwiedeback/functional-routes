name := "FunctionalRoutes"
version := "1.0.0"
scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.0-RC2",
  "com.typesafe.akka" %% "akka-stream" % "2.5.9",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.0-RC2",
  "org.scalamock" %% "scalamock" % "4.1.0" % Test
)
