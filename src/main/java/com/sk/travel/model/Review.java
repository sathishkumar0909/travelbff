package com.sk.travel.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreationTimestamp
    private Date createdDate;

    private String message;

}
