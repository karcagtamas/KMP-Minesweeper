package eu.karcags.minesweeper.domain.settings

import eu.karcags.minesweeper.data.settings.UsernameRepository
import kotlinx.coroutines.flow.Flow

class GetUsernameAsFlowUseCase(
    private val usernameRepository: UsernameRepository
) {
    operator fun invoke(): Flow<String> = usernameRepository.username
}