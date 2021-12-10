package com.app.marvelapp.base.domain

interface UseCaseSuspend<Params, Return> {
    suspend operator fun invoke(params: Params): Return
}
