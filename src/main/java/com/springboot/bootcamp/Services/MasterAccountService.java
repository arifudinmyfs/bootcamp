package com.springboot.bootcamp.Services;

import com.springboot.bootcamp.Repositories.MasterAccountRepositories;
import com.springboot.bootcamp.Repositories.MasterUserRepository;
import com.springboot.bootcamp.models.MasterAccountModel;
import com.springboot.bootcamp.models.MasterUserModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MasterAccountService {
    private JdbcTemplate jdbcTemplate;
    private MasterAccountRepositories masterAccountRepositories;
    private MasterUserRepository masterUserRepository;

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

    public MasterAccountModel createAccount(MasterAccountModel accountModel) {
        return masterAccountRepositories.save(accountModel);
    }
}
