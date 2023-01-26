package net.deali.domain.repository

import kotlinx.coroutines.flow.Flow
import net.deali.domain.model.LastestMovie
import net.deali.domain.model.Resource

interface FeatureARepository {
    fun getLatestMoives(): Flow<Resource<LastestMovie>>
}