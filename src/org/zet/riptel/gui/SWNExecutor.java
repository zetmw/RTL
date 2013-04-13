/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zet.riptel.gui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author zet
 */
public class SWNExecutor {

    private static final int MAX_WORKER_THREAD = 1;
    private static final SWNExecutor executor = new SWNExecutor();
// Thread pool for worker thread execution
    private ExecutorService workerThreadPool = Executors.newFixedThreadPool(MAX_WORKER_THREAD);

    /**
     *
     * Private constructor required for the singleton pattern.
     *
     */
    public SWNExecutor() {
    }

    /**
     *
     * Returns the singleton instance.
     *
     * @return SwingWorkerExecutor - Singleton.
     *
     **/
    public static SWNExecutor getInstance() {

        return executor;

    }

    /**
     *
     * Adds the SwingWorker to the thread pool for execution.
     *
     * @param worker - The SwingWorker thread to execute.
     *
     */
    public void execute(SwingWorker worker) {

        workerThreadPool.submit(worker); 

    }
    
    public void terminate(){
        try {
            workerThreadPool.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(SWMExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}