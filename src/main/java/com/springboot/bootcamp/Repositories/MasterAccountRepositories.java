package com.springboot.bootcamp.Repositories;

import com.springboot.bootcamp.models.MasterAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MasterAccountRepositories extends JpaRepository<MasterAccountModel, UUID> {
    List<MasterAccountModel> findByUserId(UUID userId);

}
