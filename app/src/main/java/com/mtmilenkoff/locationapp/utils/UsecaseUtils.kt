package com.mtmilenkoff.locationapp.utils

import com.mtmilenkoff.domain.resource.DataResult
import com.mtmilenkoff.domain.resource.ErrorModel
import kotlinx.coroutines.flow.Flow


suspend inline fun<T> executeUseCase(
    useCase: Flow<DataResult<T?>>,
    crossinline onSuccess: (T?) -> Unit,
    crossinline onLoading: () -> Unit,
    crossinline onError: suspend (ErrorModel) -> Unit
) {
    useCase.collect { result ->
        when (result) {
            is DataResult.Success -> onSuccess(result.data)
            is DataResult.Loading -> onLoading()
            is DataResult.Failed -> onError(result.error)
        }
    }
}
