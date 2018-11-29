package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.metaworks.multitenancy.persistence.MultitenantRepositoryImpl;
import org.metaworks.multitenancy.tenantawarefilter.TenantAwareFilter;
import org.metaworks.springboot.configuration.Metaworks4BaseApplication;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.uengine.five.service.DefinitionService;
import org.uengine.five.service.SemanticEntityService;

import javax.persistence.PostUpdate;
import javax.sql.DataSource;

/**
 * Created by uengine on 2017. 10. 5..
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = MultitenantRepositoryImpl.class)
@EnableFeignClients(basePackageClasses = {SharedCalendarService.class}) //TODO
public class Application extends Metaworks4BaseApplication {

    private static Log logger = LogFactory.getLog(Application.class);

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    protected static ApplicationContext applicationContext;

    protected Application(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManagerProvider, transactionManagerCustomizers);
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Application.class, args);
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



    @Autowired
    ClazzRepository clazzRepository;

    @RequestMapping(method = RequestMethod.POST, path="/clazzAndCourse/{clazzId}")
    public Object updateClazzAndCourse(@RequestBody Clazz clazzIn, @PathVariable("clazzId") Long clazzId){

        Clazz clazz = clazzRepository.findOne(clazzId);
        clazz.setStates(clazzIn.getStates());
        clazz.setTitle(clazzIn.getTitle());

        clazzRepository.save(clazz);

        Streams streams = Application.getApplicationContext().getBean(Streams.class);

        MessageChannel messageChannel = streams.outboundChannel();
        messageChannel.send(MessageBuilder
                .withPayload(clazzIn)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        return clazz;

    }


}