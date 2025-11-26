package com.app.cosme.shared.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.app.cosme.shared.db.CosmeDatabase

class IOSDataBaseDriverFactory : DataBaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CosmeDatabase.Schema, "test.db")
    }
}