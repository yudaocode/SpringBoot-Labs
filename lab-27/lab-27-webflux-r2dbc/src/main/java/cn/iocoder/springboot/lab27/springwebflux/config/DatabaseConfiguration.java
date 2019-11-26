package cn.iocoder.springboot.lab27.springwebflux.config;

import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

//    @Bean
//    @ConfigurationProperties("spring.jasync.r2dbc")
//    public com.github.jasync.sql.db.Configuration configuration() {
//        return new com.github.jasync.sql.db.Configuration("");
//    }

    @Bean
    public ConnectionFactory connectionFactory() {
//        com.github.jasync.sql.db.Configuration configuration
////                = URLParser.INSTANCE.parseOrDie("mysql://root:@localhost:3306/lab-27-webflux-r2dbc", StandardCharsets.UTF_8);
//                = URLParser.INSTANCE.parseOrDie("mysql://lab-27-webflux-r2dbc:0ed86@11-r2Dbc123@47.112.193.81:3306/lab-27-webflux-r2dbc", StandardCharsets.UTF_8);
        com.github.jasync.sql.db.Configuration configuration = new com.github.jasync.sql.db.Configuration(
                "lab-27-webflux-r2dbc",
                "47.112.193.81",
                3306,
                "0ed86@11-r2Dbc123",
                "lab-27-webflux-r2dbc"
        );
        return  new JasyncConnectionFactory(new MySQLConnectionFactory(configuration));
    }

}
