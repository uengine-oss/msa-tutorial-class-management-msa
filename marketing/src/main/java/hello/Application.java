package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.metaworks.multitenancy.persistence.MultitenantRepositoryImpl;
import org.metaworks.multitenancy.tenantawarefilter.TenantAwareFilter;
import org.metaworks.springboot.configuration.Metaworks4BaseApplication;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by uengine on 2017. 10. 5..
 */
@Configuration
@EnableKafka
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
@EnableCircuitBreaker
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = MultitenantRepositoryImpl.class)

public class Application extends Metaworks4BaseApplication {

    private static Log logger = LogFactory.getLog(Application.class);

    protected static ApplicationContext applicationContext;

    protected Application(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManagerProvider, transactionManagerCustomizers);
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Application.class, args);

//        try {
//            EventConsumer.main(new String[]{"localhost:9092"});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Bean
    public TenantAwareFilter tenantAwareFilter(){
        TenantAwareFilter tenantAwareFilter = new TenantAwareFilter();
        tenantAwareFilter.setAllowAnonymousTenant(true);
        //tenantAwareFilter.setDevMode(true);

        return tenantAwareFilter;
    }

    @Override
    @Bean
    @Primary
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }


//    @KafkaListener(topics = "${kafka.topic.bpm}")
    public void receive(String payload) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Schedule schedule = objectMapper.readValue(payload, Schedule.class);

        System.out.println(payload);
        //scheduleRepository.save(schedule);
    }

}