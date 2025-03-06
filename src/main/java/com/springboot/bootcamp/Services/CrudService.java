package com.springboot.bootcamp.Services;

import com.springboot.bootcamp.Configs.KafkaProducer;
import com.springboot.bootcamp.Repositories.CrudRepository;
import com.springboot.bootcamp.models.Crud;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CrudService {
    private final CrudRepository crudRepository;
    private final KafkaProducer kafkaProducerService;

    public CrudService(CrudRepository crudRepository, KafkaProducer kafkaProducerService) {
        this.crudRepository = crudRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<Crud> getAllCruds() {
        return crudRepository.findAll();
    }

    public Optional<Crud> getCrudById(UUID id) {
        return crudRepository.findById(id);
    }

    public Crud createCrud(Crud crud) {
        Crud savedCrud = crudRepository.save(crud);
        kafkaProducerService.sendMessage("belajarkfk", "Tambah data: " + savedCrud.getId());
        return savedCrud;
    }

    public Crud updateCrud(UUID id, Crud crudDetails) {
        return crudRepository.findById(id).map(crud -> {
            crud.setName(crudDetails.getName());
            crud.setEmail(crudDetails.getEmail());
            Crud updatedCrud = crudRepository.save(crud);
            kafkaProducerService.sendMessage("belajarkfk", "Update Data: " + updatedCrud.getId());
            return updatedCrud;
        }).orElseThrow(() -> new RuntimeException("Crud not found"));
    }

    public void deleteCrud(UUID id) {
        crudRepository.deleteById(id);
        kafkaProducerService.sendMessage("belajarkfk", "data ini di hapus: " + id);
    }

    public List<Crud> searchCrudsByName(String name) {
        kafkaProducerService.sendMessage("belajarkfk", "Cari data ini: " + name);
        return crudRepository.findByNameContaining(name);
    }
}
