package ru.otus.example.grooming.gsmaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsmaster.entities.MasterEntity;
import ru.otus.example.grooming.gsmaster.repositories.MasterRepository;

@Service
public class MasterService {

    private final MasterRepository masterRepository;

    @Autowired
    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public void createMaster(Long userId, String name) {
        MasterEntity masterEntity = new MasterEntity(userId, name, "+70000000000");
        masterRepository.save(masterEntity);
    }


}
