package com.yhy.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void testDBConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
