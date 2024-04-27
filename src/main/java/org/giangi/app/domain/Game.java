package org.giangi.app.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.*;

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

    public Game() {
    }

    public Game(String name, List<Designer> designers, Publisher publisher, LocalDate pubDate, Weight weight) {
        this.name = name;
        this.designers = designers;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(List<Designer> designers) {
        this.designers = designers;
    }

    public void setOwnedBy(List<User> ownedBy) {
        this.ownedBy = ownedBy;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
