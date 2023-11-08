package ru.otus.example.grooming.gsclient.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @Column(name = "pet_kind_id_")
    private Long petKindId;

    @Column(name = "name_")
    private String name;

    @Column(name = "timing_minutes_")
    private int timingMinutes;

    @Column(name = "price_")
    private double price;

}
