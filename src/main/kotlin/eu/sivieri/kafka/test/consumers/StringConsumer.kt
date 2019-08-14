package eu.sivieri.kafka.test.consumers

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class StringConsumer {

    private val consumer: KafkaConsumer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = "localhost:9092"
        properties["key.deserializer"] = StringDeserializer::class.java
        properties["value.deserializer"] = StringDeserializer::class.java
        properties["group.id"] = "Strings"
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