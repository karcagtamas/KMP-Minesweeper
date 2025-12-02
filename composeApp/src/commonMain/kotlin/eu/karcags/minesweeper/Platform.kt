package eu.karcags.minesweeper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform