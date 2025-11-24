package com.app.cosme.shared.database

import app.cash.sqldelight.db.SqlDriver

interface DataBaseDriverFactory {
    fun createDriver(): SqlDriver
}