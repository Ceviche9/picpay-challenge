package com.picpaychallenger.domain.service;

import com.picpaychallenger.domain.repositories.UserRepository;
import com.picpaychallenger.domain.user.User;
import com.picpaychallenger.domain.user.UserType;
import com.picpaychallenger.dto.CreateUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(UUID id, BigDecimal amount) throws Exception {
        Optional<User> user = Optional.ofNullable(
                this.repository.findById(id)
                        .orElseThrow(() -> new Exception("User not found!"))
        );

        if (user.get().getUserType() == UserType.MERCHANT) {
            throw new Exception("Users of type MERCHANT can not make this tape of transaction!");
        }

        if (user.get().getBalance().compareTo(amount) < 0) {
            throw new Exception("User does not have enough balance!");
        }
    }

    public User findUserById(UUID id) throws Exception {
        Optional<User> user = Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new Exception("User not found")));

        return user.get();
    }

    public User saveUser(User user) {
        return this.repository.save(user);
    }

    public User createUser(CreateUserRequestDTO user) {
        System.out.println(user);
        User newUser = new User();
        newUser.setEmail(user.email());
        newUser.setBalance(user.balance());
        newUser.setFirstName(user.firstName());
        newUser.setLastName(user.lastName());
        newUser.setPassword(user.password());
        newUser.setUserType(user.userType());
        newUser.setDocument(user.document());

        this.repository.save(newUser);

        return newUser;
    }

    public List<User> getAll() {
        return this.repository.findAll();
    }

}
