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

class CountingConnectionMaker(val realConnectionMaker: ConnectionMaker): ConnectionMaker {
    var counter: Int = 0

    override fun makeConnection(): Connection {
        counter++
        return realConnectionMaker.makeConnection()
    }
//    fun get_Counter(): Int {
//        return counter
//    }
}