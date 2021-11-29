package command

import model._

trait ChatCommand {
  def execute(): Unit
}

case class SendMessage(postMassage: PostMassage) extends ChatCommand {

  override def execute(): Unit = {
    postMassage.send(postMassage.massage.text)
  }
}

case class ReSendMessage(postMassage: PostMassage) extends ChatCommand {

  override def execute(): Unit = {
    postMassage.reSend(postMassage.massage.id)
  }
}

case class DeleteMessage(postMassage: PostMassage, recipient: User) extends ChatCommand {

  override def execute(): Unit = {
    postMassage.delete(postMassage.user, postMassage.massage.id)
    postMassage.addObserver(recipient)
  }

}

case class AddComment(postMassage: PostMassage, msgForRecipient: PostMassage) extends ChatCommand {

  override def execute(): Unit = {
    msgForRecipient.addComment(postMassage.user, postMassage.massage)
  }

}

