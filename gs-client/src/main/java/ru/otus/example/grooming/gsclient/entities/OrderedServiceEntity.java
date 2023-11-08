package ru.otus.example.grooming.gsclient.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_ordered_services")
public class OrderedServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_")
    private Long id;

    @Column(name = "order_id_")
    private Long orderId;

    @Column(name = "service_id_")
    private Long serviceId;

}
