package org.giangi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnoreProperties({"designers", "ownedBy", "publisher"})
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private List<Game> publishedGames = new ArrayList<>();


    public Publisher(String name, List<Game> publishedGames) {
        this.name = name;
        this.publishedGames = publishedGames;
    }

}
