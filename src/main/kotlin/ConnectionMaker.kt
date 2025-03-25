package org.example

import java.sql.Connection
import java.sql.DriverManager

interface ConnectionMaker {
    fun makeConnection(): Connection
}

class DConnectionMaker: ConnectionMaker {
    override fun makeConnection(): Connection {
        Class.forName("org.postgresql.Driver")
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/nemorami", "nemorami", "j5nfants")
    }
}