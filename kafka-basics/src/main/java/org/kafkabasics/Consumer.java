package org.kafkabasics;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
    public static void main(String[] args) {
        LOGGER.info("Starting Kafka Application");

        // create producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", "first-consumer-group");
        properties.setProperty("auto.offset.reset", "earliest");
        properties.setProperty("partition.assignment.strategy", CooperativeStickyAssignor.class.getName());

        // create producer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(List.of("fourth_topic"));

        // get a reference to the current thread
        final Thread mainThread = Thread.currentThread();
        // add the shutdown hook -- thread we're going to run when we shut down
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                LOGGER.info("Detected a shut down, let's exit by calling consumer.wakeup()");
                consumer.wakeup();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // poll data
        try {
            while (true) {
                LOGGER.info("Polling Data");
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(5000));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    LOGGER.info("key: {}, value: {}, partition: {}, offset: {}, topic: {} \n", consumerRecord.key(), consumerRecord.value(), consumerRecord.partition(),
                            consumerRecord.offset(), consumerRecord.topic());
                }
            }
        } catch(WakeupException e){
            LOGGER.info("Consumer is starting to shut down");
        } catch(Exception e){
            LOGGER.info("Unexpected exception thrown");
        } finally {
            consumer.close(); // close the consumer and this will also commit the offsets
            LOGGER.info("Consumer is gracefully shut down");
        }
    }
}