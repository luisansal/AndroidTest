package com.luisansal.jetpack.common.observer

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

open class BaseSingleObserver<T> : SingleObserver<T> {

    override fun onSuccess(t: T) {
        // no-op by default.
    }

    override fun onSubscribe(d: Disposable) {
        // no-op by default.
    }

    override fun onError(e: Throwable) {
        // no-op by default.
    }
}