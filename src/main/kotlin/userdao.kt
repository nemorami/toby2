package org.example

/**
 * User dao
 *
 * @property connectionMaker
 * @constructor Create empty User dao
 */
class UserDao(val connectionMaker: ConnectionMaker) {
    /**
     * Add
     *
     * @param user
     *///private val simpleConnectionMaker = SimpleConnectionMaker()
    fun add(user: User) {
        connectionMaker.makeConnection().use { c ->
            c.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)").use { ps ->
                ps.setString(1, user.id)
                ps.setString(2, user.name)
                ps.setString(3, user.password)

                ps.executeUpdate()
            }
        }
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    fun get(id: String): User? {
        connectionMaker.makeConnection().use { c ->
            c.prepareStatement("select * from users where id = ?").use { ps ->
                ps.setString(1, id)
                ps.executeQuery().use { rs ->
                    rs.next()
                    return User(rs.getString("id"), rs.getString("name"), rs.getString("password"))
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
