package edu.akka

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.ActorSystem

object SimpleActor {
  def apply(): Behavior[String] = Behaviors.receiveMessage { msg =>
    println(s"[simple-actor]: ${msg}")

    Behaviors.same
  }
}

object SimplerActor {
  def apply(): Behavior[String] = Behaviors.receive { (context, message) =>
    context.log.info(s"[simple-actor]: ${message}")

    Behaviors.same
  }
}

object SimplestActor {
  // generally used to setup the first actor
  def apply(): Behavior[String] = Behaviors.setup { context =>
    Behaviors.receiveMessage { message =>
      context.log.info(s"[simple-actor]: ${message}")

      Behaviors.same
    }
  }

}

object ActorsIntro {

  def main(arr: Array[String]): Unit = {
    implicit val system = ActorSystem(SimplestActor(), "UserRoot")

    system ! "Hello"
  }
}
