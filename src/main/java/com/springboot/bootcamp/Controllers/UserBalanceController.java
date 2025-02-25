package com.springboot.bootcamp.Controllers;

import com.springboot.bootcamp.Exceptions.CustomException;
import com.springboot.bootcamp.Repositories.MasterAccountRepositories;
import com.springboot.bootcamp.Repositories.MasterUserRepository;
import com.springboot.bootcamp.Services.MasterAccountService;
import com.springboot.bootcamp.models.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserBalanceController {
    private final MasterUserRepository masterUserRepository;
    private final MasterAccountService masterAccountService;
    private final MasterAccountRepositories masterAccountRepositories;

    public UserBalanceController(MasterUserRepository masterUserRepository, MasterAccountService masterAccountService, MasterAccountRepositories masterAccountRepositories) {
        this.masterUserRepository = masterUserRepository;
        this.masterAccountService = masterAccountService;
        this.masterAccountRepositories = masterAccountRepositories;
    }

    @PostMapping
    public MasterUserModel addUser(@RequestBody MasterUserModel masterUserModel) {
        return masterAccountService.createUser(masterUserModel);
    }

    @PostMapping("/account")
    public ResponseEntity<ResponseAcount> addAccount(@Valid @RequestBody CreateAccountRequest request) {
        ResponseAcount response = masterAccountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}/total-balance")
    public ResponseEntity<?> getUserBalance(@PathVariable UUID userId) {
        MasterUserModel user = masterAccountService.getUserById(userId);

        Double totalBalance = masterAccountService.getTotalBalanceByUserId(userId);

        if (totalBalance == null || totalBalance <= 0.0) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Total balance tidak ditemukan");
        }

        return ResponseEntity.ok(Map.of(
                "fullName", user.getFullName(),
                "userId", user.getId(),
                "totalBalance", totalBalance
        ));
    }

    @GetMapping("/{userId}/balance")
    public ResponseEntity<DataResponse<List<ResponseAcount>>> getUserBalances(@PathVariable UUID userId) {
        MasterUserModel user = masterAccountService.getUserById(userId);

        List<ResponseAcount> responseAccounts = masterAccountRepositories.findByUserId(userId)
                .stream()
                .map(account -> new ResponseAcount(
                        account.getId(),
                        user.getId(),
                        account.getBalance()))
                .toList();

        if (responseAccounts.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Data tidak ditemukan");
        }

        return ResponseEntity.ok(new DataResponse<>(responseAccounts));
    }

}
