package net.deali.coredata.repository

import net.deali.coredomain.ApiException
import net.deali.coredomain.Resource
import net.deali.coredomain.entity.BaseEntity
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {
    suspend fun <Entity : BaseEntity> safeResult(
        responseFunction: suspend () -> Entity,
    ): Resource<Entity> {
        return try {
            Resource.Success(responseFunction.invoke())
        } catch (e: HttpException) {
            Resource.Fail(ApiException.HttpException(code = e.code()))
        } catch (e: IOException) {
            Resource.Fail(ApiException.NetworkException)
        } catch (e: Throwable) {
            Resource.Fail(ApiException.UnknownException)
        }
    }
}

