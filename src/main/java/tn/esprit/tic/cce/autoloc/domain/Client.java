package tn.esprit.tic.cce.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;

    private String telephone;

    @Column(unique = true)
    private String numPermis;

    private LocalDate dateInscription;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
}