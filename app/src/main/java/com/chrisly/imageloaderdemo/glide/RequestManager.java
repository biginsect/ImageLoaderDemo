package com.chrisly.imageloaderdemo.glide;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author big insect
 * @date 2019/5/31.
 */
public class RequestManager {
    private LinkedBlockingQueue<BitmapRequest> bitmapRequests = new LinkedBlockingQueue<>();;
    private BitmapDispatcher[] dispatchers;

    private RequestManager(){
        start();
    }

    public static RequestManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final RequestManager INSTANCE = new RequestManager();
    }

    void addRequest(BitmapRequest request){
        if (null == request){
            return ;
        }
        //检查当前请求是否在队列中
        if (!bitmapRequests.contains(request)){
            bitmapRequests.add(request);
        }
    }

    void start(){
        stopAllTask();
        startAllTask();
    }

    /**
     * 线程的创建有性能损耗
     * */
    void startAllTask(){
        int threadCount = Runtime.getRuntime().availableProcessors();
        dispatchers= new BitmapDispatcher[threadCount];
        for (int i = 0;i < threadCount; i++){
            BitmapDispatcher dispatcher = new BitmapDispatcher(bitmapRequests);
            dispatcher.start();
            dispatchers[i] = dispatcher;
        }
    }

    void stopAllTask(){
        if (dispatchers != null && dispatchers.length > 0){
            for (BitmapDispatcher dispatcher: dispatchers){
                if (!dispatcher.isInterrupted()){
                    dispatcher.isInterrupted();
                }
            }
        }
    }
}
