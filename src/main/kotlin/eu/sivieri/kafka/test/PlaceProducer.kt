package eu.sivieri.kafka.test

import eu.sivieri.kafka.test.avro.Place
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

class PlaceProducer {

    private val producer: KafkaProducer<String, Place>

    init {
        val properties = Properties()
        properties["bootstrap.servers"] = "localhost:9092"
        properties["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        properties["value.serializer"] = "io.confluent.kafka.serializers.KafkaAvroSerializer"
        properties["compression.type"] = "snappy"
        properties["schema.registry.url"] = "http://localhost:8081"
        producer = KafkaProducer(properties)
    }

    fun publish(topic: String, place: Place) {
        val record = ProducerRecord(topic, place.getName().toString(), place)
        try {
            val result = producer.send(record).get()
            println(result)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

}