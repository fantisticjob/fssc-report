package com.longfor.fsscreport.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * RabbitMq配置类
 * @author de'l'l
 *
 */
@Configuration
public class RabbitMqConfig {

    //public static final String BPM_CALLBACK_QUEUE = "FSSGXYY.CALLBACK.QUEUE";
    public static final String BPM_CALLBACK_QUEUE = "LH_BPM_QUEUE";
    public static final String MDM_CALLBACK_QUEUE = "FPR.public.ZO";

    /*
       定义连接，BPM rabbitMQ
    */
    @Bean(name = "bpmConnectionFactory")
    @Primary
    public ConnectionFactory bpmConnectionFactory(
            @Value("${spring.rabbitmq.bpm.host}") String host,
            @Value("${spring.rabbitmq.bpm.port}") int port,
            @Value("${spring.rabbitmq.bpm.virtual-host}") String virtualHost,
            @Value("${spring.rabbitmq.bpm.username}") String username,
            @Value("${spring.rabbitmq.bpm.password}") String password
    ) {
        return connectionFactory(host, port, virtualHost, username, password);
    }

    /*
   定义连接，MDM rabbitMQ
*/
    @Bean(name = "mdmConnectionFactory")
    public ConnectionFactory mdmConnectionFactory(
            @Value("${spring.rabbitmq.mdm.host}") String host,
            @Value("${spring.rabbitmq.mdm.port}") int port,
            @Value("${spring.rabbitmq.mdm.virtual-host}") String virtualHost,
            @Value("${spring.rabbitmq.mdm.username}") String username,
            @Value("${spring.rabbitmq.mdm.password}") String password
    ) {
        return connectionFactory(host, port, virtualHost, username, password);
    }


    /**
     * rabbitMQ connection 工厂类
     *
     * @param host  rabbit server host address
     * @param port  rabbit server port
     * //@param virtualHost rabbit server virtual host
     * @param username rabbit server connection username
     * @param password rabbit server connection password
     *
     * @return rabbit connectionFactory instance
     */
    private CachingConnectionFactory connectionFactory(String host, int port, String virtualHost, String username, String password){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }



    /**
     * 配置确认模式 - BPM
     * 解决spring.rabbitmq.listener.simple.acknowledge-mode=true 配置无效的问题
     * @param bpmConnectionFactory
     * @return
     */
    @Bean(name = "bpmFactory")
    @Primary
    public RabbitListenerContainerFactory<?> bpmFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("bpmConnectionFactory") ConnectionFactory bpmConnectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(bpmConnectionFactory);
        factory.setMaxConcurrentConsumers(20);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        configurer.configure(factory, bpmConnectionFactory);
        return factory;
    }

    /**
     * 配置确认模式 - MDM
     * 解决spring.rabbitmq.listener.simple.acknowledge-mode=true 配置无效的问题
     * @param mdmConnectionFactory
     * @return
     */
    @Bean(name = "mdmFactory")
    public RabbitListenerContainerFactory<?> mdmFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("mdmConnectionFactory") ConnectionFactory mdmConnectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(mdmConnectionFactory);
        factory.setMaxConcurrentConsumers(20);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        configurer.configure(factory, mdmConnectionFactory);
        return factory;
    }
}
