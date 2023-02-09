package net.deali.coredomain.entity

import net.deali.coredomain.ApiException

class ErrorEntity(val exception: ApiException) : BaseEntity()