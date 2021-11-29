package model

import observer.Observable
import util.MessageArchive

import java.time.Instant
import scala.util.Random

case class PostMassage(user: User, massage: ChatMessage) extends Observable[PostMassage] {
  var messages = MessageArchive.messageArchive

  def addMessage(massage: ChatMessage): Unit = {
    messages.+=:(massage)
  }

  def addComment(recipient: User, comment: ChatMessage): Unit = {
    addMessage(comment)
    addObserver(recipient)
    notifyObservers()
  }

  def send(text: String): Unit = {
    val message = ChatMessage(
      id = Random.nextInt(1000),
      text = text,
      createdAt = Instant.now()
    )
    addMessage(message)
    notifyObservers()
  }

  def copy(id: Long): String = {
    val text: String = messages.find(_.id == id).map(_.text) match {
      case None => "/error/"
      case Some(t) => t
    }
    text
  }

  def reSend(id: Long): Unit = {
    send(copy(id))
    notifyObservers()
  }

  def delete(recipient: User, id: Long): Unit = {
    messages = messages.filter(_.id == id)
    addObserver(recipient)
    notifyObserversAboutDelete(s"msg with id #$id was deleted")
  }


}
