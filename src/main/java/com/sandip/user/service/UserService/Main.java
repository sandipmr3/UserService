package com.sandip.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static Map<String, ThreadPoolExecutor> threadPoolMap = new HashMap<String, ThreadPoolExecutor>();

    static ArrayList<String> factoryCode;

    private static final long KEEP_ALIVE_TIME_MS = 100;


    public void createExecutor() {


        factoryCode = new ArrayList<String>();

        factoryCode.add("TAV1");
        factoryCode.add("TAV2");
        factoryCode.add("TAV3");
        factoryCode.add("TAV4");


            factoryCode.forEach(factory -> {

                BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);

                ThreadPoolExecutor executor = new ThreadPoolExecutor(
                        0, // Minimum number of threads
                        10, // Maximum number of threads
                        100, // Time to keep excess threads alive
                        TimeUnit.MILLISECONDS, // Time unit for keep alive time
                        queue // Task queue
                );

                // Set the rejection policy for when the queue is full
                executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

                threadPoolMap.put(factory, executor);
    });
    }

    public static ThreadPoolExecutor getDynamicThread(String factoryCode) {

        return threadPoolMap.get(factoryCode);

    }

    public void printThreadPoolUsage(ThreadPoolExecutor executor) {
        System.out.println("ThreadPool Usage:");
        System.out.println("Core pool size: " + executor.getCorePoolSize());
        System.out.println("Maximum pool size: " + executor.getMaximumPoolSize());
        System.out.println("Current pool size: " + executor.getPoolSize());
        System.out.println("Active threads: " + executor.getActiveCount());
        System.out.println("Completed tasks: " + executor.getCompletedTaskCount());
        System.out.println("Total tasks: " + executor.getTaskCount());
        System.out.println("Tasks in queue: " + executor.getQueue().size());
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        new Main().createExecutor();

        ArrayList factory = new ArrayList<String>();

        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");

         factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");

        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");

        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");
        factory.add("TAV1");

        factory.forEach(factory1 -> {

            System.out.println("Hello and welcome!" + factory1);

            ThreadPoolExecutor executorService = getDynamicThread(factory1.toString());
            new Main().printThreadPoolUsage(executorService);

            AmmisReportProcess ammisReportProcess = new AmmisReportProcess("TAV1");
            Future<AmmisReportProcess> future = executorService.submit(ammisReportProcess);

         /*   try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/

        });
        System.out.println(" ---> : "+threadPoolMap.size());

    }

}
class AmmisReportProcess implements Callable<AmmisReportProcess> {

    String threadInput;
    public  AmmisReportProcess(String inputThread){
        this.threadInput = inputThread;
    }

    @Override
    public AmmisReportProcess call() throws Exception {

       System.out.println("Start"+ Thread.currentThread().getName());
        Thread.sleep(12000);
       System.out.println("End");



        return null;
    }
}