package org.giangi.app.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @JsonIgnoreProperties("games")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "designer_game", joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "designer_id"))
    private List<Designer> designers = new ArrayList<>();

    @JsonIgnoreProperties("collection")
    @ManyToMany(mappedBy = "collection")
    private List<User> ownedBy = new ArrayList<>();

    @JsonIgnoreProperties("publishedGames")
    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;

    @Column(name = "published")
    private LocalDate pubDate;

    @Enumerated(value = EnumType.STRING)
    private Weight weight;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }


    public Game(String name, LocalDate pubDate, Weight weight) {
        this.name = name;
        this.pubDate = pubDate;
        this.weight = weight;
    }

    public Game(String name, List<Designer> designers, Publisher publisher, LocalDate pubDate, Weight weight) {
        this.name = name;
        this.designers = designers;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.weight = weight;
    }

}
