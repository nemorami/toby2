package org.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import javax.sql.DataSource

@Configuration
class DaoFactory {
    @Bean
    fun userDao(dataSource: DataSource): UserDao {
        return UserDao(dataSource())
    }

    @Bean
    fun dataSource(): DataSource {
        return SimpleDriverDataSource(org.postgresql.Driver(),
            "jdbc:postgresql://localhost/nemorami",
            "nemorami",
            "j5nfants")
    }

}

@Configuration
class CountingDaoFactory {
    @Bean
    fun userDao(): UserDao {
        return UserDao(dataSource())

    }
    @Bean
    fun dataSource(): DataSource {
        return SimpleDriverDataSource(org.postgresql.Driver(),
            "jdbc:postgresql://localhost/nemorami",
            "nemorami",
            "j5nfants")
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