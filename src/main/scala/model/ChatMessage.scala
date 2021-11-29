package model

import java.time.Instant

case class ChatMessage(id: Long,
                       text: String,
                       createdAt: Instant) {

}
