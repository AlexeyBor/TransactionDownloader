package downloader;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentLinkedQueue;

@AllArgsConstructor
public class CustomThread implements Runnable{
    private final ConcurrentLinkedQueue<TransactionRequest> transactions;
    private final TransactionRepository transactionRepository;
    private final TransactionRequestToEntityConverter transactionRequestToEntityConverter;

    @Override
    public void run() {
        TransactionRequest transaction = transactions.poll();
        if (transaction != null) {
            addTransaction(transaction);
        }
    }

    public void addTransaction(TransactionRequest request){
        TransactionEntity existedTransaction = transactionRepository.findByTransactionId(request.getTransactionId());
        TransactionEntity currentTransaction = transactionRequestToEntityConverter.convert(request);

        if (existedTransaction != null) {
            if (!existedTransaction.getAmount().equals(currentTransaction.getAmount())){
                existedTransaction.setDate(currentTransaction.getDate());
                existedTransaction.setAmount(currentTransaction.getAmount());
                transactionRepository.save(existedTransaction);
            }
        } else {
            transactionRepository.save(currentTransaction);
        }
    }


}
