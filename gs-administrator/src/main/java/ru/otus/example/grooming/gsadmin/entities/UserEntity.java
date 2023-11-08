package ru.otus.example.grooming.gsadmin.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    @NonNull
    private Long id;

    @Column(name = "user_role_id_")
    @NonNull
    private Long userRoleId;

    @Column(name = "login_", length = 64)
    @NonNull
    private String login;

    @Column(name = "password_", length = 255)
    @NonNull
    private String password;

    @Column(name = "enabled_")
    @NonNull
    private Boolean enabled;

}
