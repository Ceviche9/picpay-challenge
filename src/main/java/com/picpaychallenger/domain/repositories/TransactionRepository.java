package com.picpaychallenger.domain.repositories;

import com.picpaychallenger.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
