package net.deali.coredata.mapper

interface Mapper<Response, Entity> {
    fun toModel(response: Response): Entity
}
