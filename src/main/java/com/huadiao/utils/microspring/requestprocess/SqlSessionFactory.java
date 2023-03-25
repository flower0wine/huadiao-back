package com.huadiao.utils.microspring.requestprocess;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * @projectName 花凋
 * @author  flowerwine
 * @version  1.1
 * @description 返回一个 sqlSession 对象, SqlSession 工厂对象
 */
public class SqlSessionFactory {
    private static final String resource = "huadiaoconfig/mybatis-config.xml";

    private static InputStream inputStream;

    private static org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory;

    /**
     * 生成一个访问数据库的 sqlSession, 默认不自动提交事务
     * @return 返回一个 sqlSession
     * @throws IOException 可能的异常
     */
    public static SqlSession generateSqlSession() throws IOException {
        return generateSqlSession(false);
    }

    /**
     * 生成一个访问数据库的 sqlSession
     * @param autoCommit 是否自动提交事务
     * @return 返回一个 sqlSession
     * @throws IOException 可能的异常
     */
    protected static SqlSession generateSqlSession(boolean autoCommit) throws IOException {
        if (sqlSessionFactory == null) {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        // 对于不同用户，一个 sqlSessionFactory 就可以应对，但 session 必须要有多个
        return sqlSessionFactory.openSession(autoCommit);
    }



}
