package ru.otus.example.grooming.gsclient.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_clients")
@Getter
@Setter
@NoArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @Column(name = "user_id_")
    private Long userId;

    @Column(name = "name_")
    private String name;

    @Column(name = "phone_")
    private String phone;

    @Column(name = "email_")
    private String email;

    @Column(name = "address_")
    private String address;

    public ClientEntity(Long userId) {
        this.userId = userId;
    }
}
