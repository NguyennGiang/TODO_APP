package com.example.todo

import java.time.LocalDateTime

class CardInfo(
    var title: String,
    var priority: String,
    var id: String? = LocalDateTime.now().toString()
)

