package eu.sivieri.kafka.test

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val producer = Producer()
        producer.publish("test", "First message", "Hello, world!")
    }

}