package com.example.websocketpractice.controller

import com.example.websocketpractice.config.ChatRoom
import com.example.websocketpractice.config.ChatRoomRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/multiRoom")
class HomeController {

    @RequestMapping("/home")
    fun home(model: Model, request: HttpServletRequest): String {
        val chatRooms: Collection<ChatRoom> = ChatRoomRepository.chatRooms!!

        model.addAttribute("collection", chatRooms);
        return "home"
    }
}