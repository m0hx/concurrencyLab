package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ConcurrentEmployeeProcessor {

    private final SalaryRules salaryRules;

    public ConcurrentEmployeeProcessor(SalaryRules salaryRules) {
        this.salaryRules = salaryRules;
    }

    // thread pool + semaphore + lock
    public List<Employee> processEmployees(List<Employee> employees, int poolSize, int maxConnections) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(poolSize); // worker threads
        Semaphore semaphore = new Semaphore(maxConnections); // limit how many tasks run "inside" at once
        ReentrantLock lock = new ReentrantLock(); // only one thread adds to list at a time

        List<Employee> processedEmployees = new ArrayList<>(); // shared result (needs lock on add)

        for (Employee employee : employees) {
            fixedThreadPool.submit(() -> { // runs on a pool thread
                boolean acquired = false;
                try {
                    semaphore.acquire(); // wait for a free slot
                    acquired = true;

                    salaryRules.applyRaise(employee); // apply salary rules

                    lock.lock();
                    try {
                        processedEmployees.add(employee); // add shared ArrayList
                    } finally {
                        lock.unlock();
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt(); // catch/restore interrupt flag
                } finally {
                    if (acquired) {
                        semaphore.release(); // give slot back
                    }
                }
            });
        }

        fixedThreadPool.shutdown(); // no new tasks
        try {
            if (!fixedThreadPool.awaitTermination(60, TimeUnit.SECONDS)) { // wait for workers
                fixedThreadPool.shutdownNow(); // shutdown immediately
            }
        } catch (InterruptedException ex) {
            fixedThreadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return processedEmployees;
    }
}
