package net.deali.coredata.repository

import net.deali.coredata.mapper.Mapper
import net.deali.coredata.response.BaseResponse
import net.deali.coredomain.ApiException
import net.deali.coredomain.Resource
import net.deali.coredomain.entity.BaseEntity
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {
    suspend fun <Response : BaseResponse, Entity : BaseEntity> safeResult(
        mapper: Mapper<Response, Entity>,
        responseFunction: suspend () -> Response,
    ): Resource<Entity> {
        return try {
            Resource.Success(mapper.toModel(responseFunction.invoke()))
        } catch (e: HttpException) {
            Resource.Fail(ApiException.HttpException(code = e.code()))
        } catch (e: IOException) {
            Resource.Fail(ApiException.NetworkException)
        } catch (e: Exception) {
            Resource.Fail(ApiException.UnknownException)
        }
    }
}

