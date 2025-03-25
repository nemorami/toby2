package org.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DaoFactory {
    @Bean
    fun userDao(): UserDao {
        return UserDao(DConnectionMaker())
    }
}