package net.deali.core.ui.uimodel

import net.deali.nativecore.BaseModel
import net.deali.nativecore.exception.ApiException

class ErrorModel(val exception: ApiException) : BaseModel()