package util

import model._

import scala.collection.mutable.ListBuffer

object MessageArchive {
  var messageArchive=ListBuffer[ChatMessage]()
  var userArchive=ListBuffer[User]()
}
