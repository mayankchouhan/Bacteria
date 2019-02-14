name := "Bacteria"

version := "0.1"

scalaVersion := "2.12.8"


lazy val dependencies = Seq(
  "org.slf4j" % "log4j-over-slf4j" % "1.7.25",
  "com.typesafe" % "config" % "1.3.3",
  "com.github.scopt" %% "scopt" % "3.5.0",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.slf4j" % "slf4j-simple" % "1.7.7"
)

lazy val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.1",
  "org.scalacheck" %% "scalacheck" % "1.13.4"
) map (_ % "test")


libraryDependencies ++= (dependencies ++ testDependencies)
