package org.example

import org.springframework.dao.EmptyResultDataAccessException
import javax.sql.DataSource

/**
 * User dao
 *
 * @property connectionMaker
 * @constructor Create empty User dao
 */
class UserDao(val dataSource: DataSource/*val connectionMaker: ConnectionMaker*/) {


    /**
     * Add
     *
     * @param user
     *///private val simpleConnectionMaker = SimpleConnectionMaker()
    fun add(user: User) {
        dataSource.getConnection().use { c ->
            c.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)").use { ps ->
                ps.setString(1, user.id)
                ps.setString(2, user.name)
                ps.setString(3, user.password)

                ps.executeUpdate()
            }
        }
    }
    fun deleteAll(): Unit {
        dataSource.connection.use { c ->
            c.prepareStatement("DELETE FROM users").use { ps ->
                ps.executeUpdate()
            }
        }

    }
    fun getCount(): Int {
        var count = 0
        dataSource.connection.use { c ->
            c.prepareStatement("SELECT count(*) FROM users").use { ps ->
                val rs = ps.executeQuery()
                rs.next()
                rs.getInt(1).also { count = it }
            }
        }
        return count
    }
    /**
     * Get
     *
     * @param id
     * @return
     */
    fun get(id: String): User? {
        dataSource.getConnection().use { c ->
            c.prepareStatement("select * from users where id = ?").use { ps ->
                ps.setString(1, id)
                ps.executeQuery().use { rs ->
                    if(rs.next())
                        return User(rs.getString("id"), rs.getString("name"), rs.getString("password"))
                    else
                        throw EmptyResultDataAccessException(1)
                }
            }
        }
    } // end of fun get()

}

//abstract class UserDao {
//    fun add(user: User) {
//        getConnection().use {c ->
//            c.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)").use {ps ->
//                ps.setString(1, user.id)
//                ps.setString(2, user.name)
//                ps.setString(3, user.password)
//
//                ps.executeUpdate()
//            }
//        }
//    }
//
//    fun get(id: String): User? {
//        getConnection().use { c ->
//            c.prepareStatement("select * from users where id = ?").use { ps ->
//                ps.setString(1, id)
//                ps.executeQuery().use { rs ->
//                    rs.next()
//                    return User(rs.getString("id"), rs.getString("name"), rs.getString("password"))
//                }
//            }
//        }
//    } // end of fun get()
//
//    abstract fun getConnection(): Connection
//
////    private fun getConnection(): Connection {
////        Class.forName("org.postgresql.Driver")
////        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/nemorami", "nemorami", "j5nfants")
////    }
//}
//class NUserDao : UserDao() {
//    override fun getConnection(): Connection {
//        Class.forName("org.postgresql.Driver")
//        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/nemorami", "nemorami", "j5nfants")
//    }
//}
