package edu.akka

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors

trait Message {
  val content: String
}

sealed trait Destination { this: Message =>
  val to: String
}

final case class Email(to: String, content: String) extends Destination with Message
final case class Slack(to: String, content: String) extends Destination with Message

object Worker {
  def apply(): Behavior[Destination] = Behaviors.receiveMessage { message =>
    println(s"Sending message: ${message}")
    Behaviors.same
  }

}
object NotificationDispatcherMain {
  final case class Dispatch(message: Destination)

  def apply(): Behavior[Dispatch]    = Behaviors.setup { ctx =>
    lazy val worker = ctx.spawn(Worker(), "worker")

    Behaviors.receiveMessage { dispatch: Dispatch =>
      dispatch.message match {
        case email: Email =>
          worker ! email
          Behaviors.same
        case slack: Slack =>
          worker ! slack
          Behaviors.same
      }
    }
  }
  def main(arr: Array[String]): Unit = {
    implicit val system = ActorSystem(NotificationDispatcherMain(), "user-root")
    system ! Dispatch(Slack("http://webhook.slack.com", "Hello."))
    system ! Dispatch(Email("girdharshubham@hotmail.com", "Hello."))
    system.terminate()
  }
}
