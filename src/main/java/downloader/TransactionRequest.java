package downloader;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class TransactionRequest {
    @NotNull
    @JsonProperty(value = "id")
    String transactionId;

    @NotNull
    @JsonProperty(value = "date")
    String date;

    @NotNull
    @JsonProperty(value = "amount")
    String amount;
}
