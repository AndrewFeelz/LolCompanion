package com.feelydev.shroompoint.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class APIExecutors {

    private static APIExecutors instance;

    public static APIExecutors getInstance(){
        if(instance == null){
            instance = new APIExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService NetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getNetworkIO(){ return NetworkIO;}


}
