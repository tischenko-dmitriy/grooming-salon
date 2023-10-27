package ru.otus.example.grooming.gsclient.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pet_kind_ref")
public class PetKindRefEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @Column(name = "name_")
    private String name;

}
