package app

import akka.actor.{ ActorSystem, Props }
import actor.{ Itau, Cielo, Dropbox, Client }
import mediator.WalletMediator
import event.GetWallet

object Run {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("SystemTest")
    import system.dispatcher
    
    val itau = system.actorOf(Props[Itau], "itau") 
    val cielo = system.actorOf(Props[Cielo], "cielo")
    val dropbox = system.actorOf(Props[Dropbox], "dropbox")

    val walletMediator = system.actorOf(Props(new WalletMediator(itau, cielo, dropbox)), "wallet-mediator")
    val client = system.actorOf(Props(new Client(walletMediator)), "client")

    client ! GetWallet(3)
  }
}