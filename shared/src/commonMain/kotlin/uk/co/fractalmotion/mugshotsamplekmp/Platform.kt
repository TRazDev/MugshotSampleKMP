package uk.co.fractalmotion.mugshotsamplekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform