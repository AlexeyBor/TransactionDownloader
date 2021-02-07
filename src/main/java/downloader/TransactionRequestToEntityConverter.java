package downloader;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class TransactionRequestToEntityConverter implements Converter<TransactionRequest, TransactionEntity> {

    @Override
    public TransactionEntity convert(TransactionRequest transactionRequest) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transactionRequest.getTransactionId());
        transactionEntity.setDate(Timestamp.valueOf(transactionRequest.getDate()));
        transactionEntity.setAmount(new BigDecimal(transactionRequest.getAmount()));

        return transactionEntity;
    }
}
