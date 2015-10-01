package actor

import akka.actor.{ Actor, ActorRef }
import event.GetWallet
import domain.Wallet

class Client(mediator: ActorRef) extends Actor {
  
  def receive = {
    case GetWallet(id: Long) =>
      mediator ! GetWallet(id)
    case Wallet(money, cards, documents) =>
      println(s"money: $money")
      println(s"cards: $cards")
      println(s"documents: $documents")
  }
}
