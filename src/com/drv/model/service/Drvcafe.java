package com.drv.model.service;

import com.drv.model.Order;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.IntStream;

public class Drvcafe extends Thread{

    private final ScheduledExecutorService executorService;
    private final List<Buyer> buyers;
    private final List<Cashbox> cashboxes;
    private final BlockingQueue<Order> allOrders;

    public Drvcafe(int buyersNumber, int cashboxesNumber){
        this.executorService = Executors.newScheduledThreadPool(3);
        this.allOrders = new ArrayBlockingQueue<>(cashboxesNumber * 10);
        this.buyers = createBuyers(buyersNumber);
        this.cashboxes = createCashboxes(cashboxesNumber);
    }

    @Override
    public void run(){

    }

    private List<Buyer> createBuyers(int buyersNumber) {
        return IntStream.range(0, buyersNumber)
                .mapToObj(i -> new Buyer(allOrders))
                .toList();
    }

    private List<Cashbox> createCashboxes(int cashboxesNumber) {
        return IntStream.range(0, cashboxesNumber)
                .mapToObj(i -> new Cashbox(allOrders))
                .toList();
    }

}
