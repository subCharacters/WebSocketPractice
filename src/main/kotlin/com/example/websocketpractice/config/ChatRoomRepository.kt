package com.example.websocketpractice.config

import org.springframework.web.socket.WebSocketSession
import java.util.*


class ChatRoomRepository {
    var chatRoomMap = HashMap<String?, ChatRoom>()

    companion object {
        var chatRooms: Collection<ChatRoom>? = null
    }

    constructor() {
        for (i in 0..1) {
            val uuid = "room" + i // UUID.randomUUID().toString()
            val chatRoom = ChatRoom(uuid)
            chatRoomMap[chatRoom.id] = chatRoom
            println("chatRoom 클래스를 복제하고 있습니다.")
            println("chatRoom -> $chatRoom")
        }
        chatRooms = chatRoomMap.values
    }

    fun getChatRoom(id: String?): ChatRoom? {
        return chatRoomMap[id]
    }

    fun getChatRooms(): Map<String?, ChatRoom?>? {
        return chatRoomMap
    }

    fun remove(session: WebSocketSession?) {
        chatRooms!!.parallelStream().forEach { chatRoom: ChatRoom ->
            chatRoom.remove(
                session
            )
        }
    }
}