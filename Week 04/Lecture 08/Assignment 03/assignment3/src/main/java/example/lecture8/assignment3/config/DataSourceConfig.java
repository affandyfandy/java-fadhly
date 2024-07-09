package example.lecture8.assignment3.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class DataSourceConfig {

    @Value("${datasource1.driverClassName}")
    private String driverClassName1;

    @Value("${datasource1.url}")
    private String url1;

    @Value("${datasource1.username}")
    private String username1;

    @Value("${datasource1.password}")
    private String password1;

    @Value("${datasource2.driverClassName}")
    private String driverClassName2;

    @Value("${datasource2.url}")
    private String url2;

    @Value("${datasource2.username}")
    private String username2;

    @Value("${datasource2.password}")
    private String password2;

    @Bean(name = "dataSource1")
    public DataSource dataSource1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName1);
        dataSource.setUrl(url1);
        dataSource.setUsername(username1);
        dataSource.setPassword(password1);
        return dataSource;
    }

    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName2);
        dataSource.setUrl(url2);
        dataSource.setUsername(username2);
        dataSource.setPassword(password2);
        return dataSource;
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("dataSource1") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("dataSource2") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "transactionManager1")
    public DataSourceTransactionManager transactionManager1(@Qualifier("dataSource1") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean(name = "transactionManager2")
    public DataSourceTransactionManager transactionManager2(@Qualifier("dataSource2") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}
