import Dependencies._

lazy val root = (project in file("."))
  .aggregate(voyager)

lazy val voyager = (project in file("voyager"))
  .settings(
    scalaVersion := "3.3.0",
    description  := "Scala 3",
  )

lazy val discovery = (project in file("discovery"))
  .settings(
    scalaVersion := "2.13.11",
    description  := "Scala 2",
    resolvers += "Akka library repository".at("https://repo.akka.io/maven"),
    libraryDependencies ++= akka,
  )
