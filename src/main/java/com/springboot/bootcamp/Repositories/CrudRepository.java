package com.springboot.bootcamp.Repositories;

import com.springboot.bootcamp.models.Crud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CrudRepository extends JpaRepository<Crud, UUID> {
    @Query("FROM Crud u WHERE u.name LIKE %:name%")
    List<Crud> findByNameContaining(String name);
}
