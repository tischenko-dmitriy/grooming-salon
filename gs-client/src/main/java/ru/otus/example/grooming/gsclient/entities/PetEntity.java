package ru.otus.example.grooming.gsclient.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pets")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @Column(name = "name_")
    private String name;

    @Column(name = "client_id_")
    private Long clientId;

    @Column(name = "pet_kind_id_")
    private Long petKindId;

    public PetEntity(String name, Long clientId, Long petKindId) {
        this.name = name;
        this.clientId = clientId;
        this.petKindId = petKindId;
    }

}
