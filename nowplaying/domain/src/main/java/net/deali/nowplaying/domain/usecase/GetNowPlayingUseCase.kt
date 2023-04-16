package net.deali.nowplaying.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.deali.coredomain.BaseUseCase
import net.deali.coredomain.Resource
import net.deali.nowplaying.domain.entity.NowPlayingEntity
import net.deali.nowplaying.domain.repository.NowPlayingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNowPlayingUseCase @Inject constructor(
    private val repository: NowPlayingRepository
) : BaseUseCase() {
    operator fun invoke(
        page: Int,
        region: String? = null,
    ): Flow<Resource<NowPlayingEntity>> = executeAndConvertToFlow {
        repository.getNowPlayingMovies(
            page,
            region
        )
    }

}