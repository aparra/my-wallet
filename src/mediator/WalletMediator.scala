package mediator

import scala.concurrent.ExecutionContext
import akka.actor.{ Actor, ActorRef }

class WalletMediator extends Actor {

  implicit val ec: ExecutionContext = context.dispatcher
  
}