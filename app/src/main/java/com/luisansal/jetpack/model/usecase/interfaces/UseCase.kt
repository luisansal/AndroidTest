package com.luisansal.jetpack.model.usecase.interfaces

import dagger.internal.Preconditions
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

abstract class UseCase {

    private val disposables: CompositeDisposable = CompositeDisposable()

    internal inline fun <reified T> execute(
            observable: Observable<T>, observer: DisposableObserver<T>) {
        Preconditions.checkNotNull(observer)
        val disposable = observable

//                .subscribeOn(Schedulers.from(threadExecutor))
//                .observeOn(postExecutionThread.scheduler)
                .subscribeWith(observer)
        addDisposable(disposable)
    }

    internal inline fun <reified T> execute(
            single: Single<T>, observer: SingleObserver<T>) {
        Preconditions.checkNotNull(observer)
        single

//                .subscribeOn(threadExecutor.scheduler)
//                .observeOn(postExecutionThread.scheduler)
                .subscribeWith(observer)
    }

    internal fun execute(
            completable: Completable, observer: CompletableObserver) {
        Preconditions.checkNotNull(observer)
        completable
//                .subscribeOn(Schedulers.from(threadExecutor))
//                .observeOn(postExecutionThread.scheduler)
                .subscribeWith(observer)
    }

    fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }

}