package controller

import command.ChatCommand

import scala.collection.mutable.ListBuffer

class ChatController {

  val commandHistory = ListBuffer[ChatCommand]()

  def issueCommand(command: ChatCommand): Unit = {
    command +=: commandHistory
    command.execute()

  }

  def showHistory(): Unit = {
    println("-----------")
    commandHistory.foreach(c => println(s"${c}\n"))
    println("-----------")
  }

}