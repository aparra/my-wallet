package mediator

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import akka.util.Timeout
import akka.actor.{ Actor, ActorRef }
import akka.pattern.ask
import domain.{ Wallet, Card, Document }
import event.{ GetWallet, GetBalance, GetCards, GetDocuments }

class WalletMediator(itau: ActorRef, cielo: ActorRef, dropbox: ActorRef) extends Actor {

  implicit val timeout: Timeout = 100 milliseconds
  implicit val ec: ExecutionContext = context.dispatcher
  
  def receive = {
    case GetWallet(id) =>
      val moneyPromise = itau ? GetBalance(id)
      val cardsPromise = cielo ? GetCards(id)
      val documentsPromise = dropbox ? GetDocuments(id)
      
      val wallet = for {
        money <- moneyPromise.mapTo[BigDecimal]
        cards <- cardsPromise.mapTo[List[Card]]
        documents <- documentsPromise.mapTo[List[Document]]
      } yield Wallet(money, cards, documents)

      val client = sender
      wallet map (client ! _)
  }
}