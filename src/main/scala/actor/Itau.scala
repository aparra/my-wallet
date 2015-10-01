package actor

import akka.actor.Actor
import event.GetBalance

class Itau extends Actor {

  val accountData = Map[Long, BigDecimal](
    1L -> 1000,
    2L -> 6000)

  def receive = {
    case GetBalance(id: Long) =>
      println(s"Get balance for user $id")

      accountData.get(id) match {
        case Some(money) => sender ! money
        case None        => sender ! BigDecimal(0)
      }
  }
}
