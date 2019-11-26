package cn.iocoder.springboot.lab27.springwebflux.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableTransactionManagement // 开启事务的支持
public class DatabaseConfiguration {

    @Bean
    public ConnectionFactory connectionFactory(R2dbcProperties properties) throws URISyntaxException {
        // 从 R2dbcProperties 中，解析出 host、port、database
        URI uri = new URI(properties.getUrl());
        String host = uri.getHost();
        int port = uri.getPort();
        String database = uri.getPath().substring(1); // 去掉首位的 / 斜杠
        // 创建 jasync Configuration 配置配置对象
        com.github.jasync.sql.db.Configuration configuration = new com.github.jasync.sql.db.Configuration(
                properties.getUsername(), host, port, properties.getPassword(), database);
        // 创建 JasyncConnectionFactory 对象
        return  new JasyncConnectionFactory(new MySQLConnectionFactory(configuration));
    }

    @Bean
    public ReactiveTransactionManager transactionManager(R2dbcProperties properties) throws URISyntaxException {
        return new R2dbcTransactionManager(this.connectionFactory(properties));
    }

}
