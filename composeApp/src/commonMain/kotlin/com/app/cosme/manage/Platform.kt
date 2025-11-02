package com.app.cosme.manage

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform