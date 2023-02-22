package com.training.docshelf.worker;

import com.training.docshelf.model.Document;
import com.training.docshelf.model.Relationship;
import com.training.docshelf.strategy.TransformStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class RelationshipWorker implements  Runnable{
    ExecutorService tpe;
    AtomicInteger inQueue;
    Document newDoc;
    Document registeredDoc;
    TransformStrategy strategy;

    public RelationshipWorker(ExecutorService tpe, AtomicInteger inQueue, Document newDoc,
                              Document registeredDoc, TransformStrategy strategy) {
        this.tpe = tpe;
        this.inQueue = inQueue;
        this.newDoc = newDoc;
        this.registeredDoc = registeredDoc;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        String description = strategy.transform(newDoc, registeredDoc);
        registeredDoc.addRelationship(new Relationship(newDoc.getName(), description));

        int left = inQueue.decrementAndGet();

        if(left == 0){
            tpe.shutdown();
        }
    }
}
