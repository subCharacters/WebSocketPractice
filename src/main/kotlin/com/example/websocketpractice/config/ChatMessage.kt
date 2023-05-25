package com.example.websocketpractice.config

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatMessage(
    @JsonProperty("chatRoomId") var chatRoomId: String?,
    @JsonProperty("message") var message: String?,
    @JsonProperty("type") var type: String?
)
