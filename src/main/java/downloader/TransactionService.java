package downloader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionRequestToEntityConverter transactionRequestToEntityConverter;

    public void addTransactions(ConcurrentLinkedQueue<TransactionRequest> concurrentLinkedQueue){
        int queueSize = concurrentLinkedQueue.size();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < queueSize; i++){
            Runnable thread = new CustomThread(concurrentLinkedQueue, transactionRepository, transactionRequestToEntityConverter);
            executor.execute(thread);
        }

        executor.shutdown();
    }



}
