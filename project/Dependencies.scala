import sbt._

object Dependencies {
  lazy val AkkaVersion           = "2.8.4"
  lazy val LogbackClassicVersion = "1.2.12"
  lazy val ScalaTestVersion      = "3.2.16"

  lazy val `scala-test` = "org.scalatest" %% "scalatest" % ScalaTestVersion % Test
  lazy val scalactic    = "org.scalactic" %% "scalactic" % ScalaTestVersion % Test

  lazy val logback = "ch.qos.logback" % "logback-classic" % LogbackClassicVersion

  lazy val `akka-typed`         = "com.typesafe.akka" %% "akka-actor-typed"         % AkkaVersion
  lazy val `akka-typed-testkit` = "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test

  lazy val akka = Seq(`akka-typed`, `akka-typed-testkit`, logback)
}
