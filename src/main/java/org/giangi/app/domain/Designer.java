package org.giangi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first name")
    @NotNull
    private String firstName;

    @Column(name = "last name")
    @NotNull
    private String lastName;

    @Column(name = "birth date")
    private LocalDate birthDate;

    @JsonIgnoreProperties({"designers", "ownedBy", "publisher"})
    @Column(name = "games")
    @ManyToMany(cascade = CascadeType.ALL , mappedBy = "designers")
    private List<Game> games = new ArrayList<>();

    public Designer(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Designer(String firstName, String lastName, LocalDate birthDate, List<Game> games) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.games = games;
    }

}
