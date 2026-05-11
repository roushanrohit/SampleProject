package org.kafkabasics;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    public static void main(String[] args) {
        LOGGER.info("Starting Kafka Application");

        // create producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // send data
        for(int i =0; i<10; i++){

            String topic = "fourth_topic";
            String key = "id_" + i; // same key goes to the same partition
            String value = "Hello " + i + " from Kafka";

            // create a producer record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // executes every time a record is successfully sent or an exception is thrown
                    if(e == null){
                        LOGGER.info("Successfully sent message to Kafka \n Topic : {} \n Parition: {} \n Offset: {} \n Timestamp {}",
                                recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp());
                    } else {
                        LOGGER.error("Error while sending message to Kafka");
                    }
                }
            });

            // timeout of 5 s
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                LOGGER.error("Thread Interrupted while timeout: {}", e.getMessage());
            }
        }

        // flush and close the producer
        producer.close();
    }
}