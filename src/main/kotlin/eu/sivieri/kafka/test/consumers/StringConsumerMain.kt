package eu.sivieri.kafka.test.consumers

object StringConsumerMain {

    @JvmStatic
    fun main(args: Array<String>) {
        val stringConsumer = StringConsumer()
        stringConsumer.consume("test")
    }

}