package com.springboot.bootcamp.Services;

import com.springboot.bootcamp.Exceptions.CustomException;
import com.springboot.bootcamp.Repositories.MasterAccountRepositories;
import com.springboot.bootcamp.Repositories.MasterUserRepository;
import com.springboot.bootcamp.models.CreateAccountRequest;
import com.springboot.bootcamp.models.MasterAccountModel;
import com.springboot.bootcamp.models.MasterUserModel;
import com.springboot.bootcamp.models.ResponseAcount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MasterAccountService {
    private JdbcTemplate jdbcTemplate;
    private final MasterAccountRepositories masterAccountRepositories;
    private final MasterUserRepository masterUserRepository;

    @Autowired
    public MasterAccountService(JdbcTemplate jdbcTemplate, MasterUserRepository masterUserRepository, MasterAccountRepositories masterAccountRepositories) {
        this.jdbcTemplate = jdbcTemplate;
        this.masterUserRepository = masterUserRepository;
        this.masterAccountRepositories = masterAccountRepositories;
    }

    public Double getTotalBalanceByUserId(UUID userId) {
        String sql = "SELECT COALESCE(SUM(BALANCE), 0) FROM MASTER_ACCOUNT WHERE USER_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Double.class);
    }

    public MasterUserModel createUser(MasterUserModel user) {
        return masterUserRepository.save(user);
    }

    public ResponseAcount createAccount(CreateAccountRequest request) {
        MasterUserModel user = masterUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User tidak ditemukan"));

        MasterAccountModel account = new MasterAccountModel(user, request.getBalance());
        MasterAccountModel savedAccount = masterAccountRepositories.save(account);

        return new ResponseAcount(savedAccount.getId(), user.getId(), savedAccount.getBalance());
    }

    public MasterUserModel getUserById(UUID userId) {
        return masterUserRepository.findById(userId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User tidak ditemukan"));
    }
}
