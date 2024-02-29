package com.picpaychallenger.domain.transactions.service;

import com.picpaychallenger.domain.transactions.repository.TransactionRepository;
import com.picpaychallenger.domain.notifications.service.NotificationService;
import com.picpaychallenger.domain.transactions.entity.Transaction;
import com.picpaychallenger.domain.users.entity.User;
import com.picpaychallenger.domain.users.service.UserService;
import com.picpaychallenger.domain.transactions.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userServicel;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO data) throws Exception {
        User sender = this.userServicel.findUserById(data.senderId());
        User receiver = this.userServicel.findUserById(data.receiverId());
        userServicel.validateTransaction(sender.getId(), data.value());

        if (!this.authorizeTransaction(sender, data.value())) {
            throw new Exception("Transaction not authorized");
        }

        System.out.println("Indo");
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(data.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setCreatedAt(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(data.value()));
        receiver.setBalance(receiver.getBalance().add(data.value()));

        this.repository.save(newTransaction);
        this.userServicel.saveUser(sender);
        this.userServicel.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transaction sended");
        this.notificationService.sendNotification(receiver, "Transaction received");
        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
       ResponseEntity<Map> authorization = this.restTemplate
               .getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if (authorization.getStatusCode() == HttpStatus.OK) {
            return true;
        } else return false;
    }

    public List<Transaction> getAll() {
        return this.repository.findAll();
    }

}
