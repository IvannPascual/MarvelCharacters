package com.app.marvelapp.base.domain

interface UseCase<Params, Return> {
    operator fun invoke(params: Params): Return
}