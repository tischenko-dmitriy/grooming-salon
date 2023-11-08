package ru.otus.example.grooming.gsclient.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_client_addresses")
public class ClientAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @Column(name = "client_id_")
    private Long clientId;

    @Column(name = "address_")
    private String address;

}
