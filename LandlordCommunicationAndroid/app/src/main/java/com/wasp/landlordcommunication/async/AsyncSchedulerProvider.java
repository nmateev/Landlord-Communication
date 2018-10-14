package com.wasp.landlordcommunication.async;

import com.wasp.landlordcommunication.async.base.SchedulerProvider;

import java.util.Objects;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AsyncSchedulerProvider implements SchedulerProvider {
    private static SchedulerProvider sSchedulerProvider;

    private AsyncSchedulerProvider() {

    }

    public static SchedulerProvider getInstance() {

        if (Objects.equals(sSchedulerProvider, null)) {
            sSchedulerProvider = new AsyncSchedulerProvider();
        }
        return sSchedulerProvider;
    }

    @Override
    public Scheduler uiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.io();
    }
}
