package com.app.cosme.shared.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.app.cosme.shared.db.CosmeDatabase

class AndroidDatabaseDriverFactory(private val context: Context) : DataBaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CosmeDatabase.Schema, context, "test.db")
    }
}
