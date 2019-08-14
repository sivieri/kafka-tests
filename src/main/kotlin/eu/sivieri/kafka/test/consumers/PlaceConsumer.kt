package eu.sivieri.kafka.test.consumers

import eu.sivieri.kafka.test.avro.Place
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class PlaceConsumer {

    private val consumer: KafkaConsumer<String, Place>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = "localhost:9092"
        properties["key.deserializer"] = StringDeserializer::class.java
        properties["value.deserializer"] = KafkaAvroDeserializer::class.java
        properties["group.id"] = "Strings"
        properties["schema.registry.url"] = "http://localhost:8081"
        properties["specific.avro.reader"] = "true"
        consumer = KafkaConsumer(properties)
    }

    fun consume(topic: String) {
        consumer.subscribe(listOf(topic))
        consumer.use { consumer ->
            while (true) {
                val records = consumer.poll(Duration.ofMillis(100))
                records.forEach {
                    println("${it.key()} - ${it.value()}")
                }
            }
        }
    }

}