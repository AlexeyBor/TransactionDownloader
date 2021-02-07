package downloader;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @RequestMapping(value = "/add", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBooks (@RequestBody @Valid List<TransactionRequest> request) {
        transactionService.addTransactions(new ConcurrentLinkedQueue<>(request));
    }
}
