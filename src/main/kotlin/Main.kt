package org.example

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.sql.Connection
import java.sql.DriverManager

data class User(val id: String, val name: String, val password: String)




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //val context = AnnotationConfigApplicationContext(DaoFactory::class.java)
    val context = AnnotationConfigApplicationContext(CountingDaoFactory::class.java)
    val dao = context.getBean("userDao", UserDao::class.java)
    val user = User("John", "Peter", "123456")
//    val connectionMaker = DConnectionMaker()
//    val dao = UserDao(connectionMaker)
    //val dao = DaoFactory().userDao()

    dao.add(user)

    println("${dao.get(user.id)} 등록성공")

    val user2 = dao.get(user.id)
    println(user2?.name)

    val ccm = context.getBean("connectionMaker", CountingConnectionMaker::class.java)
    println("Connection counter: ${ccm.counter}")
}


