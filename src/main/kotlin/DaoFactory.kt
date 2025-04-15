package org.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
//class DaoFactory {
//    @Bean
//    fun userDao(): UserDao {
//        return UserDao(DConnectionMaker())
//    }
//}

@Configuration
class CountingDaoFactory {
    @Bean
    fun userDao(): UserDao {
        val userDao = UserDao()
        userDao.connectionMaker = connectionMaker()
        return userDao
    }

    @Bean
    fun connectionMaker(): ConnectionMaker {
        return CountingConnectionMaker(realConnectionMaker())
    }

    @Bean
    fun realConnectionMaker(): ConnectionMaker {
        return DConnectionMaker()
    }
}