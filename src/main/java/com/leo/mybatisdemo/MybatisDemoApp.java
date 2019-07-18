package com.leo.mybatisdemo;


import com.leo.mybatisdemo.entity.User;
import com.leo.mybatisdemo.mapper.UserMapper;
import com.leo.mybatisdemo.po.MybatisDemoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class MybatisDemoApp {

    public static void main(String[] args) {
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(MybatisDemoUtil.loadProperties("/jdbc.properties"));
        DataSource dataSource = pooledDataSourceFactory.getDataSource();

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);

        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        log.info(mapper.toString());
    }

}
