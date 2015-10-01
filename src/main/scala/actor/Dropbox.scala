package actor

import akka.actor.Actor
import event.GetDocuments
import domain.Document

class Dropbox extends Actor {

  val documentsData = Map[Long, List[Document]](
    1L -> List(Document("Passport"), Document("GNIB")),
    2L -> List(Document("Passport")))

  def receive = {
    case GetDocuments(id: Long) =>
      println(s"Get documents for user $id")

      documentsData.get(id) match {
        case Some(docs) => sender ! docs
        case None       => sender ! List.empty[Document]
      }
  }
}
