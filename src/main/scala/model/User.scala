package model

import observer.Observer
import util.MessageArchive

import java.time.Instant
import scala.util.Random

case class User(id: Long,
                name: String,
                createdAt: Instant) extends Observer[PostMassage] {

  private var archive = MessageArchive.userArchive

  def create(name: String): Unit = {
    val user = User(
      id = Random.nextInt(1000),
      name = name,
      createdAt = Instant.now()
    )
    archive.+=:(user)
  }

  override def handleUpdate(subject: PostMassage): Unit = {
    println("-----------")
    System.out.println(s"${name} has received: ${subject}")
  }
}
