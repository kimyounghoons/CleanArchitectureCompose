package net.deali.domain.usecase

import net.deali.domain.repository.FeatureARepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLastestMovieUseCase @Inject constructor(
    private val repository: FeatureARepository
) {
    operator fun invoke() = repository.getLatestMoives()
}