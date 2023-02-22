package com.training.docshelf.worker;

import com.training.docshelf.model.Document;
import com.training.docshelf.strategy.TransformStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RelationshipService {
    private final AtomicInteger inQueue = new AtomicInteger(0);

    public void setRelations(Document doc, List<Document> docList, TransformStrategy strategy){
        ExecutorService tpe = Executors.newFixedThreadPool(8);

        for (Document document: docList) {
            if(document.getCategory().equals(doc.getCategory())) {
                inQueue.incrementAndGet();
                tpe.submit(new RelationshipWorker(tpe, inQueue, doc, document, strategy));
            }
        }

        try {
            tpe.awaitTermination(1, TimeUnit.SECONDS);
        }

        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
