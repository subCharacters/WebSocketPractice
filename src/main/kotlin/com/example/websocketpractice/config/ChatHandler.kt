package com.example.websocketpractice.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class ChatHandler : TextWebSocketHandler() {

    var objectMapper = ObjectMapper()
    var repository = ChatRoomRepository()

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        var payload = message.payload
        val chatMessage = objectMapper.readValue(payload, ChatMessage::class.java)
        val chatRoom = repository.getChatRoom(chatMessage.chatRoomId)
        chatRoom!!.handleMessage(session, chatMessage, objectMapper)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        repository.remove(session);
    }
}