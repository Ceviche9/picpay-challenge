package com.picpaychallenger.domain.controller;

import com.picpaychallenger.domain.service.TransactionService;
import com.picpaychallenger.domain.transaction.Transaction;
import com.picpaychallenger.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;


    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO request) throws Exception {
        Transaction transaction = this.service.createTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll());
    }
}
