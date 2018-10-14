package com.wasp.landlordcommunication.async.base;


import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler uiThread();

    Scheduler backgroundThread();
}