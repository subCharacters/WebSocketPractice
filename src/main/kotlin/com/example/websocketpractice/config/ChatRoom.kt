package com.example.websocketpractice.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.util.HashSet
import java.util.function.Predicate


class ChatRoom {
    var id: String?
    var sessions: HashSet<WebSocketSession?> = HashSet()

    constructor (room_id: String?) {
        id = room_id
    }

    @Throws(JsonProcessingException::class)
    fun handleMessage(session: WebSocketSession?, chatMessage: ChatMessage, objectMapper: ObjectMapper) {
        if (chatMessage.type == "JOIN") join(session) else send(chatMessage, objectMapper)
    }

    private fun join(session: WebSocketSession?) {
        sessions.add(session)
    }

    @Throws(JsonProcessingException::class)
    private fun <T> send(messageObject: T, objectMapper: ObjectMapper) {
        val message = TextMessage(objectMapper.writeValueAsString(messageObject))
        sessions.parallelStream().forEach { session: WebSocketSession? ->
            try {
                session?.sendMessage(message)
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
    }

    fun remove(target: WebSocketSession?) {
        val targetId = target?.id
        sessions.removeIf(Predicate { session: WebSocketSession? -> session?.id == targetId })
    }
}