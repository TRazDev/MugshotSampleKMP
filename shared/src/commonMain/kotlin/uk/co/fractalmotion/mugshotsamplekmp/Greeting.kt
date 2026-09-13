package uk.co.fractalmotion.mugshotsamplekmp

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return sayHello(platform.name)
    }
}