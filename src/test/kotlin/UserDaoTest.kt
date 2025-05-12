import org.example.CountingDaoFactory
import org.example.User
import org.example.UserDao
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.dao.EmptyResultDataAccessException


class UserDaoTest {
    lateinit var dao: UserDao
//    val dao: UserDao by lazy {
//        setUp()
//    }
    @BeforeEach
    fun setUp() {
        val context  = AnnotationConfigApplicationContext(CountingDaoFactory::class.java)
        dao = context.getBean("userDao", UserDao::class.java)
    }

    //    @AfterEach
//    fun tearDown() {
//        TODO("Not yet implemented")
//    }
    @Test
    fun addAndGet() {


        val user1 = User("gyumee", "박성철", "springno1")
        val user2 = User("leegw700", "이길원", "springno2")
//    val connectionMaker = DConnectionMaker()
//    val dao = UserDao(connectionMaker)
        //val dao = DaoFactory().userDao()

        dao.deleteAll()
        assertEquals(dao.getCount(), 0)

        dao.add(user1)
        assertEquals(dao.getCount(), 1)
        dao.add(user2)
        assertEquals(dao.getCount(), 2)
        val userget1 = dao.get(user1.id)
        assertEquals(userget1!!.name, user1.name)
        assertEquals(userget1.password, user1.password)

        val userget2 = dao.get(user2.id)
        assertEquals(userget2!!.name, user2.name)
        assertEquals(userget2.password, user2.password)

    }
    @Test
    fun getCount(): Unit {

        val user1 = User("gyumee", "박성철", "springno1")
        val user2 = User("leegw700", "이길원", "springno2")
        val user3 = User("bumjin", "박범진", "springno3")

        dao.deleteAll()
        assertEquals(dao.getCount(), 0)
        dao.add(user1)
        assertEquals(dao.getCount(), 1)
        dao.add(user2)
        assertEquals(dao.getCount(), 2)
        dao.add(user3)
        assertEquals(dao.getCount(), 3)
    }
    @Test /* (expected=EmptyResultDataAccessException::class.java) */
    fun getException() {

        dao.deleteAll()
        assertEquals(dao.getCount(), 0)
        val e = assertThrows<EmptyResultDataAccessException>(){
            dao.get("unknown_id")
        }
        println("exception: ${e.message!!}")


    }
    @Test
    fun add() {
    }

    @Test
    fun get() {
    }

    @Test
    fun getDataSource() {
    }

}