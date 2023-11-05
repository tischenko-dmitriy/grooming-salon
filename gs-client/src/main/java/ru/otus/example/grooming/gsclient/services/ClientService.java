package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.entities.ClientEntity;
import ru.otus.example.grooming.gsclient.entities.PetEntity;
import ru.otus.example.grooming.gsclient.entities.PetKindRefEntity;
import ru.otus.example.grooming.gsclient.exceptions.ClientNotFoundException;
import ru.otus.example.grooming.gsclient.exceptions.PetKindNotFoundException;
import ru.otus.example.grooming.gsclient.model.dto.ClientDto;
import ru.otus.example.grooming.gsclient.model.dto.PetDto;
import ru.otus.example.grooming.gsclient.repositories.ClientRepository;
import ru.otus.example.grooming.gsclient.repositories.PetKindRefRepository;
import ru.otus.example.grooming.gsclient.repositories.PetRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PetKindRefRepository petKindRefRepository;
    private final PetRepository petRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         PetKindRefRepository petKindRefRepository,
                         PetRepository petRepository) {
        this.clientRepository = clientRepository;
        this.petKindRefRepository = petKindRefRepository;
        this.petRepository = petRepository;
    }

    public void createClient(Long userId, String name) {
        ClientEntity clientEntity = new ClientEntity(userId, name, "+70000000000");
        clientRepository.save(clientEntity);
    }

    public void updateClient(ClientDto clientDto) {
        clientRepository.save(clientDtoToClientEntity(clientDto));
    }

    public void createPet(PetDto petDto) {
        PetEntity petEntity = petDtoToPetEntity(petDto);
        petRepository.save(petEntity);
    }

    private ClientEntity clientDtoToClientEntity(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientDto.getClientId());
        clientEntity.setName(clientDto.getName());
        clientEntity.setAddress(clientDto.getAddress());
        clientEntity.setPhone(clientDto.getPhone());
        clientEntity.setEmail(clientEntity.getEmail());
        return clientEntity;
    }

    private PetEntity petDtoToPetEntity(PetDto petDto) {
        ClientEntity clientEntity = clientRepository.findByName(petDto.getClient())
                .orElseThrow(() -> new ClientNotFoundException(petDto.getClient()));
        PetKindRefEntity petKindRefEntity = petKindRefRepository.findByName(petDto.getKind())
                .orElseThrow(() -> new PetKindNotFoundException(petDto.getKind()));

        return new PetEntity(petDto.getName(), clientEntity.getId(), petKindRefEntity.getId());
    }

}
