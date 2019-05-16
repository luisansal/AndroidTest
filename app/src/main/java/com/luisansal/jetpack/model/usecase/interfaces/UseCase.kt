package com.luisansal.jetpack.model.usecase.interfaces

import com.luisansal.jetpack.common.executor.PostExecutionThread
import com.luisansal.jetpack.common.executor.ThreadExecutor
import dagger.internal.Preconditions
import io.reactivex.*
import io.reactivex.annotations.SchedulerSupport.TRAMPOLINE
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.TrampolineScheduler
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase protected constructor
(private val threadExecutor: ThreadExecutor,
 private val postExecutionThread: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    internal inline fun <reified T> execute(
            observable: Observable<T>, observer: DisposableObserver<T>) {
        Preconditions.checkNotNull(observer)
        val disposable = observable

                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
                .subscribeWith(observer)
        addDisposable(disposable)
    }

    internal inline fun <reified T> execute(
            single: Single<T>, observer: SingleObserver<T>) : Single<T>{
        Preconditions.checkNotNull(observer)

        if (threadExecutor.scheduler is TrampolineScheduler) {
            single
                    .subscribeWith(observer)
        } else {
            single
                    .subscribeOn(threadExecutor.scheduler)
                    .observeOn(postExecutionThread.scheduler)
                    .subscribeWith(observer)
        }


        return single
    }

    internal fun execute(
            completable: Completable, observer: CompletableObserver) : Completable {
        Preconditions.checkNotNull(observer)
        if (threadExecutor.scheduler is TrampolineScheduler) {
            completable
                    .subscribeWith(observer)
        } else {
            completable
                    .subscribeOn(Schedulers.from(threadExecutor))
                    .observeOn(postExecutionThread.scheduler)
                    .subscribeWith(observer)
        }
        return completable
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