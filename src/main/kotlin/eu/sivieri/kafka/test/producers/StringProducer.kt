package eu.sivieri.kafka.test.producers

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class StringProducer {

    private val producer: KafkaProducer<String, String>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = "localhost:9092"
        properties["key.serializer"] = StringSerializer::class.java
        properties["value.serializer"] = StringSerializer::class.java
        properties["compression.type"] = "snappy"
        producer = KafkaProducer(properties)
    }

    fun publish(topic: String, key: String, value: String) {
        val record = ProducerRecord(topic, key, value)
        try {
            val result = producer.send(record).get()
            println(result)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

}