package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.entities.ClientEntity;
import ru.otus.example.grooming.gsclient.model.dto.ClientDto;
import ru.otus.example.grooming.gsclient.repositories.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;


    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Long userId, String name) {
        ClientEntity clientEntity = new ClientEntity(userId, name, "+70000000000");
        clientRepository.save(clientEntity);
    }

    public void updateClient(ClientDto clientDto) {
        clientRepository.save(clientDtoToClientEntity(clientDto));
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

}
