package eu.sivieri.kafka.test

import eu.sivieri.kafka.test.avro.Place

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val stringProducer = StringProducer()
        stringProducer.publish("test", "First message", "Hello, world!")
        val placeProducer = PlaceProducer()
        placeProducer.publish("test", Place("Test1", 45.0, 11.0))
    }

}