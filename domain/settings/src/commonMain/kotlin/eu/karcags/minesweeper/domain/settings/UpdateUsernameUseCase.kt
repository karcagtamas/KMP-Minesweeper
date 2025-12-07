package eu.karcags.minesweeper.domain.settings

import eu.karcags.minesweeper.data.settings.UsernameRepository

class UpdateUsernameUseCase(
    private val usernameRepository: UsernameRepository,
) {

    suspend operator fun invoke(username: String) {
        usernameRepository.updateUsername(username)
    }
}