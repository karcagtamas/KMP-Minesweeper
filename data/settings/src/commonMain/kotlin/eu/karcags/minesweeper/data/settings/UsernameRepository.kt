package eu.karcags.minesweeper.data.settings

import eu.karcags.minesweeper.data.core.storage.Storage
import kotlinx.coroutines.flow.Flow

interface UsernameRepository {

    val username: Flow<String>

    suspend fun updateUsername(username: String)

    data object UsernameKey : Storage.Key.StringKey("username", "Anonymous")
}