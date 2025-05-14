import org.example.ConnectionMaker
import org.example.CountingConnectionMaker
import org.example.DConnectionMaker
import org.example.UserDao
import org.postgresql.Driver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import javax.sql.DataSource


@Configuration
open class TestDaoFactory {
    @Bean
    fun userDao(): UserDao {
        return UserDao(dataSource())

    }
    @Bean
    fun dataSource(): DataSource {
        return SimpleDriverDataSource(
            Driver(),
            "jdbc:postgresql://localhost/nemorami",
            "nemorami",
            "j5nfants")
    }
}