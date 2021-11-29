
import command._
import controller.ChatController
import model._

import java.time.Instant
import scala.util.Random

object App extends {

  def main(args: Array[String]): Unit = {

    val instant = Instant.now()
    val random = Random.nextInt(1000)

    val chatController = new ChatController

    val userEd = User(1, "Ed", instant)
    val userAlf = User(2, "Alf", instant)
    val userAnn = User(3, "Ann", instant)
    val userJack = User(4, "Jack", instant)

    val message1 = ChatMessage(random, "Hello", instant)
    val message2 = ChatMessage(random, "Hi", instant)
    val message3 = ChatMessage(random, "What',s Up", instant)
    val message4 = ChatMessage(random, "Howdy", instant)
    val messageFromJackAgain = ChatMessage(random, "hey", instant)

    val postMassageFromEd = PostMassage(userEd, message1)
    val postMassageFromAlf = PostMassage(userAlf, message2)
    val postMassageFromAnn = PostMassage(userAnn, message3)
    val postMassageFromJack = PostMassage(userJack, message4)
    val postAnotherMessageFromJack = PostMassage(userJack, messageFromJackAgain)

    postMassageFromAlf.addObserver(userEd)
    postMassageFromAlf.addObserver(userAnn)
    postMassageFromAlf.addObserver(userJack)

    postMassageFromEd.addObserver(userJack)
    postMassageFromEd.addObserver(userAnn)
    postMassageFromEd.addObserver(userAlf)

    postMassageFromAnn.addObserver(userAlf)
    postMassageFromAnn.addObserver(userEd)
    postMassageFromAnn.addObserver(userJack)

    postMassageFromJack.addObserver(userAlf)
    postMassageFromJack.addObserver(userEd)
    postMassageFromJack.addObserver(userAnn)

    chatController.issueCommand(SendMessage(postMassageFromEd))
    chatController.issueCommand(SendMessage(postMassageFromAlf))
    chatController.issueCommand(SendMessage(postMassageFromAnn))
    chatController.issueCommand(SendMessage(postMassageFromJack))

    chatController.issueCommand(ReSendMessage(postMassageFromEd))

    chatController.issueCommand(DeleteMessage(postMassageFromEd, userEd))

    chatController.issueCommand(AddComment(postMassageFromEd, postAnotherMessageFromJack))

    chatController.issueCommand(AddComment(postMassageFromAnn, postAnotherMessageFromJack))

    chatController.showHistory()

  }

}
