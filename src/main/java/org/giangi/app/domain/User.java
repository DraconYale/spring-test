package org.giangi.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nickname")
    @NotNull
    private String nickname;

    @JsonIgnoreProperties({"designers", "ownedBy", "publisher"})
    @ManyToMany
    @OrderBy("name")
    @JoinTable(name = "collection", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> collection = new ArrayList<>();

    public User(String nickname) {
        this.nickname = nickname;
    }

    public User(String nickname, List<Game> collection) {
        this.nickname = nickname;
        this.collection = collection;
    }

}
