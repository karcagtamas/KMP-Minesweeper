package eu.karcags.minesweeper.data.settings

import eu.karcags.minesweeper.data.core.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultUsernameRepository(
    private val storage: Storage,
) : UsernameRepository {
    override val username: Flow<String> = storage.getAsFlow(UsernameRepository.UsernameKey)
        .map {
            it.orEmpty()
        }

    override suspend fun updateUsername(username: String) {
        storage.writeValue(UsernameRepository.UsernameKey, username)
    }
}