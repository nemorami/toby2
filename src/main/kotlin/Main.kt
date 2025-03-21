package org.example

import java.sql.Connection
import java.sql.DriverManager

data class User(val id: String, val name: String, val password: String)

abstract class UserDao {
    fun add(user: User) {
        getConnection().use {c ->
            c.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)").use {ps ->
                ps.setString(1, user.id)
                ps.setString(2, user.name)
                ps.setString(3, user.password)

                ps.executeUpdate()
            }
        }
    }

    fun get(id: String): User? {
        getConnection().use { c ->
            c.prepareStatement("select * from users where id = ?").use { ps ->
                ps.setString(1, id)
                ps.executeQuery().use { rs ->
                    rs.next()
                    return User(rs.getString("id"), rs.getString("name"), rs.getString("password"))
                }
            }
        }
    } // end of fun get()

    abstract fun getConnection(): Connection

//    private fun getConnection(): Connection {
//        Class.forName("org.postgresql.Driver")
//        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/nemorami", "nemorami", "j5nfants")
//    }
}
class NUserDao : UserDao() {
    override fun getConnection(): Connection {
        Class.forName("org.postgresql.Driver")
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/nemorami", "nemorami", "j5nfants")
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val user = User("John", "Peter", "123456")
    val dao = NUserDao()
    dao.add(user)

    println("${dao.get(user.id)} 등록성공")

    val user2 = dao.get(user.id)
    println(user2?.name)
}