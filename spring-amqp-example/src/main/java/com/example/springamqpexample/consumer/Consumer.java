
package com.example.springamqpexample.consumer;

import java.util.Map;

import com.example.springamqpexample.constants.Exchanges;
import com.example.springamqpexample.constants.Queues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author hufeng
 * @version : Consumer.java, v 0.1 2020年06月02日 8:53 下午 hufeng Exp $
 */
@Service
@Configuration
@EnableCaching
@Slf4j
public class Consumer {

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = Queues.HEADER_QUEUE_ONE, durable = "true",
            arguments = {@Argument(name = "x-delay-policy", value = "immutable"),
                @Argument(name = "x-delay", value = "1000", type = "java.lang.Integer")}),
        exchange = @Exchange(value = Exchanges.HEADER_EXCHANGE, type = ExchangeTypes.HEADERS),
        key = {"my_routing_key"},
        arguments = {
            @Argument(name = "key-one", value = "1", type = "java.lang.Integer"),
            @Argument(name = "key-two", value = "2", type = "java.lang.Integer"),
            @Argument(name = "x-match", value = "any")
        }))
    @RabbitHandler
    public void anyMatchOnMessage(@Payload String message, @Headers Map<String, Object> headers) {
        log.info("Receive " + headers.get(AmqpHeaders.CONSUMER_QUEUE) + " Message:" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = Queues.HEADER_QUEUE_TWO, durable = "true",
            arguments = {@Argument(name = "x-delay-policy", value = "immutable"),
                @Argument(name = "x-delay", value = "1000", type = "java.lang.Integer")}),
        exchange = @Exchange(value = Exchanges.HEADER_EXCHANGE, type = ExchangeTypes.HEADERS),
        arguments = {
            @Argument(name = "key-one", value = "1", type = "java.lang.Integer"),
            @Argument(name = "x-match", value = "all")
        }))
    @RabbitHandler
    public void allMatchOnMessage(@Payload String message, @Headers Map<String, Object> headers) {
        log.info("Receive " + headers.get(AmqpHeaders.CONSUMER_QUEUE) + " Message:" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = Queues.TOPIC_QUEUE_ONE, durable = "true",
            arguments = {@Argument(name = "x-delay-policy", value = "immutable"),
                @Argument(name = "x-delay", value = "1000", type = "java.lang.Integer")}),
        exchange = @Exchange(value = Exchanges.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
        key = {"rabbit.#"}
    ))
    @RabbitHandler
    public void matchTopicOnMessage(@Payload String message, @Headers Map<String, Object> headers) {
        log.info("Receive " + headers.get(AmqpHeaders.CONSUMER_QUEUE) + " Message:" + message);
    }
}