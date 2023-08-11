package com.idemia.wearherapp.domain.usecase.base

import androidx.lifecycle.LiveData
import com.idemia.wearherapp.data.source.network.response.Resource
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<T: Any?, R: Any?> {
    suspend operator fun invoke(param: T): Flow<Resource<R>>
}