package com.example.websocketpractice.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/multiRoom")
class RoomController {

    @RequestMapping("/room")
    fun room(model: Model, request: HttpServletRequest): String {
        var roomId = request.getParameter("id")

        model.addAttribute("roomId", roomId)
        return "room"
    }
}