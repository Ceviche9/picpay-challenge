package com.picpaychallenger.domain.transactions.repository;

import com.picpaychallenger.domain.transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
