package eu.sivieri.kafka.test.consumers

object PlaceConsumerMain {

    @JvmStatic
    fun main(args: Array<String>) {
        val placeConsumer = PlaceConsumer()
        placeConsumer.consume("test2")
    }

}