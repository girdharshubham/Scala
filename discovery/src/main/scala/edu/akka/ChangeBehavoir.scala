package edu.akka

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}
import Command._

sealed trait Command
object Command {
  case object Default        extends Command
  case object SwitchBehavior extends Command
  case object Initialize     extends Command
}

object Driver {
  def apply(): Behavior[Command] = Behaviors.setup { ctx =>
    Behaviors.receiveMessage { message =>
      message match {
        case SwitchBehavior =>
          ctx.log.info("Switching Behavior; Should only log once")
          Child()
      }
    }
  }
}

object Child {
  def apply(): Behavior[Command] = Behaviors.receive { (ctx, message) =>
    message match {
      case Default    => ctx.log.info("Default Behavior")
      case Initialize => ctx.log.info("Initializing")
    }
    Behaviors.same
  }
}

object ChangeBehavoir {
  def main(arr: Array[String]): Unit = {
    val system = ActorSystem(Driver(), "Root")

    // Should MatchError
    // system ! Initialize

    system ! SwitchBehavior
    system ! Initialize
    system ! Default

  }
}
