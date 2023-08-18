import sbt._

object Dependencies {
  lazy val AkkaVersion           = "2.8.4"
  lazy val LogbackClassicVersion = "1.2.12"

  lazy val `akka-typed` =
    "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion

  lazy val `akka-typed-testkit` =
    "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test

  lazy val logback = "ch.qos.logback" % "logback-classic" % LogbackClassicVersion

  lazy val akka = Seq(`akka-typed`, `akka-typed-testkit`, logback)
}
