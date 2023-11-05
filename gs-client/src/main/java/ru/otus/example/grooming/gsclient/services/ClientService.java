package ru.otus.example.grooming.gsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.otus.example.grooming.gsclient.entities.ClientEntity;
import ru.otus.example.grooming.gsclient.entities.PetEntity;
import ru.otus.example.grooming.gsclient.entities.PetKindRefEntity;
import ru.otus.example.grooming.gsclient.entities.ServiceEntity;
import ru.otus.example.grooming.gsclient.exceptions.ClientNotFoundException;
import ru.otus.example.grooming.gsclient.exceptions.PetKindNotFoundException;
import ru.otus.example.grooming.gsclient.model.dto.ClientDto;
import ru.otus.example.grooming.gsclient.model.dto.PetDto;
import ru.otus.example.grooming.gsclient.model.dto.ServiceDto;
import ru.otus.example.grooming.gsclient.repositories.ClientRepository;
import ru.otus.example.grooming.gsclient.repositories.PetKindRefRepository;
import ru.otus.example.grooming.gsclient.repositories.PetRepository;
import ru.otus.example.grooming.gsclient.repositories.ServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PetKindRefRepository petKindRefRepository;
    private final PetRepository petRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         PetKindRefRepository petKindRefRepository,
                         PetRepository petRepository,
                         ServiceRepository serviceRepository) {
        this.clientRepository = clientRepository;
        this.petKindRefRepository = petKindRefRepository;
        this.petRepository = petRepository;
        this.serviceRepository = serviceRepository;
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

    public List<ServiceDto> getServiceList(String petKindName) {
        List<ServiceEntity> serviceList = new ArrayList<>();
        if (Objects.nonNull(petKindName)) {
            PetKindRefEntity petKindRefEntity = petKindRefRepository.findByName(petKindName)
                    .orElseThrow(() -> new PetKindNotFoundException(petKindName));
            serviceList = (List<ServiceEntity>) serviceRepository.findAllByPetKindId(petKindRefEntity.getId());
        } else {
            serviceList = (List<ServiceEntity>) serviceRepository.findAll();
        }
        List<ServiceDto> result = new ArrayList<>();
        serviceList.forEach(i -> result.add(serviceEntityToServiceDto(i)));
        return result;
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

    private ServiceDto serviceEntityToServiceDto(ServiceEntity serviceEntity) {
        PetKindRefEntity petKindRefEntity = petKindRefRepository.findById(serviceEntity.getPetKindId())
                .orElseThrow(() -> new PetKindNotFoundException(String.format("petKindId = %d", serviceEntity.getPetKindId())));
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setServiceId(serviceEntity.getId());
        serviceDto.setServiceName(serviceEntity.getName());
        serviceDto.setPetKindName(petKindRefEntity.getName());
        serviceDto.setTimingMinutes(serviceEntity.getTimingMinutes());
        serviceDto.setPrice(serviceDto.getPrice());
        return serviceDto;
    }

}
