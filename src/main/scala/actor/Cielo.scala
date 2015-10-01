package actor

import akka.actor.Actor
import domain.Card
import event.GetCards

class Cielo extends Actor {

  val cardsData = Map[Long, List[Card]](
    1L -> List(Card("Visa"), Card("MasterCard")),
    2L -> List(Card("Visa")))

  def receive = {
    case GetCards(id: Long) =>
      println(s"Get cards for user $id")

      cardsData.get(id) match {
        case Some(cards) => sender ! cards
        case None        => sender ! List.empty[Card]
      }
  }
}
