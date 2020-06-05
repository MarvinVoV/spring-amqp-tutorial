
package com.example.springamqpexample.producer;

import com.example.springamqpexample.constants.Exchanges;
import com.example.springamqpexample.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hufeng
 * @version : MyTest.java, v 0.1 2020年06月02日 9:18 下午 hufeng Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyProducerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {

        Order order = new Order(1L, "T");

        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().getHeaders().put("key-one", 1);
                message.getMessageProperties().getHeaders().put("key-two", 2);
                message.getMessageProperties().getHeaders().put("key-three", 3);
                return message;
            }
        };

        MessagePostProcessor wrapper = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("hello=======");
                return messagePostProcessor.postProcessMessage(message);
            }
        };

        rabbitTemplate.convertAndSend(Exchanges.HEADER_EXCHANGE, null,
            order,wrapper);
    }

    @Test
    public void testExchangeBindExchange() {
        Order order = new Order(2L, "T");
        rabbitTemplate.convertAndSend(Exchanges.TOPIC_EXCHANGE, "rabbit.match",
            order, message -> {
                message.getMessageProperties().getHeaders().put("key-one", 0);
                message.getMessageProperties().getHeaders().put("key-two", 2);
                message.getMessageProperties().getHeaders().put("key-three", 3);
                return message;
            });
    }

}