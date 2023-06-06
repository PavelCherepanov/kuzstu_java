<<<<<<< HEAD
<h2>Лабораторная работа: калькулятор</h2>
<hr>
<p>Нужно создать калькулятор. Можно консольный или с интерфейсом. Также написать unit-тесты к нему.</p>

<h3>Написание unit-тестов. Быстрый и краткий гайд</h3>
<hr>
<b>JUnit</b> - популярный и мощный фреймворк для тестирования. С помощью него можно быстро и легко написать тесты для программы.<br>
<h3>Для тестирования необходимо: </h3>
<ul>
    <li>Создать в папке test класс: НазваниеКлассаTest (такое название считается хорошей практикой)</li>
    <li>В этом классе нужно писать методы: testНазваниеМетода</li>
</ul>
<h3>Для тестирования применяются следующие аннотации:</h3>
<ul>
    <li>Аннотация <code>@Before</code> обозначает методы, которые будут вызваны перед исполнением тестов.</li>
    <li>Аннотация <code>@Test</code> обозначает тестовые методы.</li>
    <li>Аннотация <code>@After</code> обозначает методы, которые будут вызваны после выполнения тестов.</li>
</ul>
<h3>Общая структура и логика довольно проста: </h3>
Аннотацией <code>@Before</code> можно обозначить участок кода, где создается экземпляр класса, который тестируется.
Аннотацией <code>@Test</code> обозначается тестовый метод. В этом методе объявляются значения, которые идут на вход теста, также значения которые должны получиться.
<br> Далее вызов метода тестируемого класса. И в конце вызывется метод assertEquals класса Assert с двумя параметрами: ожидаемое значение, то что получилось.
=======
<h1>Очень краткая методичка</h1>
<h2>Создание Spring-приложения</h2><hr>   
<p>Для быстрого создания Spring Boot приложения, можно воспользоваться сайтом: <a>https://start.spring.io/</a></p>
<p><img src="images/init.png" alt="init project"></p>
<h2>ORM vs SQL</h2><hr>
<p>Существует два основных способа взаимодействия с базой данных: ORM и SQL.
<p><img src="images/block.png" alt="ORM vs SQL"></p>
В ORM сопоставляются классы Java с таблицами в базе данных. Hibernate — это инструмент, который как раз работает по ORM. 

В JDBC (Java Database Connectivity) нужно вручную писать sql-запросы. Вот пример классического JDBC.


```java
Connection conn = null;
try {
    String url = "jdbc:sqlite:path-to-db-file/bd/users.db";
    conn = DriverManager.getConnection(url);
    Statement stmt = null;
    String query = "SELECT * FROM users";
    try {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("name");
        }
    } catch (SQLException e) {
        throw new Error("Error", e);
    } finally {
        if (stmt != null) {
            stmt.close();
        }
    }
} catch (SQLException e) {
    throw new Error("Error", e);
} finally {
    try {
        if (conn != null) {
            conn.close();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
```

Код довольно многословный поэтому придумали JDBCTemplate. JDBCTemplate - удобная обертка.

```java
public List<User> list() {
    String sql = "SELECT user_id, name, email, password, registration_date_time FROM users";
    return jdbcTemplate.query(sql, rowMapper);
}
```


</p>
>>>>>>> 690e885 (add README)
