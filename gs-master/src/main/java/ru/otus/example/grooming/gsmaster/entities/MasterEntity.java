package ru.otus.example.grooming.gsmaster.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_masters")
public class MasterEntity {

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

    public MasterEntity(Long userId, String name, String phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

}
