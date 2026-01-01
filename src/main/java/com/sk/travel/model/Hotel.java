package com.sk.travel.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(length = 10)
    private String  phonenumber;

    private String name;

    private String city;

    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    private String description;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Room> rooms=new LinkedList<>();

    private boolean active;

    private int rating;

    @OneToMany
    private List<Review> reviews=new LinkedList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
}
