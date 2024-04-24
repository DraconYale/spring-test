package org.giangi.app.bootstrap;

import org.giangi.app.domain.Designer;
import org.giangi.app.domain.Game;
import org.giangi.app.domain.Publisher;
import org.giangi.app.domain.User;
import org.giangi.app.repositories.DesignerRepository;
import org.giangi.app.repositories.GameRepository;
import org.giangi.app.repositories.PublisherRepository;
import org.giangi.app.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

import static org.giangi.app.domain.Weight.LIGHT;
import static org.giangi.app.domain.Weight.LIGHT_MEDIUM;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final DesignerRepository designerRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(UserRepository userRepository,
                         GameRepository gameRepository,
                         DesignerRepository designerRepository,
                         PublisherRepository publisherRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.designerRepository = designerRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher repos = new Publisher("Repos Production", Collections.emptyList());
        Publisher asmodee = new Publisher("Asmodee", Collections.emptyList());
        List<Game> forBauza = new ArrayList<>();
        Designer bauza = new Designer("Antoine", "Bauza", LocalDate.parse("1998-01-01"), forBauza);

        Game wonders = new Game("7wonders", Collections.<Designer>emptyList(), repos, LocalDate.parse("2020-04-13"), LIGHT_MEDIUM);
        Game hanabi = new Game("Hanabi", Collections.emptyList(), asmodee, LocalDate.parse("2010-03-01"), LIGHT);

        List<Designer> toWond = new ArrayList<>(wonders.getDesigners());
        toWond.add(bauza);
        wonders.setDesigners(toWond);

        List<Designer> toHan = new ArrayList<>(hanabi.getDesigners());
        toHan.add(bauza);
        hanabi.setDesigners(toHan);


        List<Game> toAnt = new ArrayList<>(bauza.getGames());
        toAnt.add(wonders);
        toAnt.add(hanabi);
        bauza.setGames(toAnt);
        System.out.println("Antoine games: " + bauza.getGames().toString());

        List<Game> toRepo = new ArrayList<>(repos.getPublishedGames());
        toRepo.add(wonders);
        repos.setPublishedGames(toRepo);

        List<Game> toAsm = new ArrayList<>(asmodee.getPublishedGames());
        toAsm.add(hanabi);
        asmodee.setPublishedGames(toAsm);


        User eric = new User("Eric");
        User steve = new User("Steve");
        User sandro = new User("Sandro");

        List<Game> toEric = new ArrayList<>(eric.getCollection());
        toEric.add(wonders);
        toEric.add(hanabi);
        eric.setCollection(toEric);

        List<Game> toSteve = new ArrayList<>(steve.getCollection());
        toSteve.add(wonders);
        steve.setCollection(toSteve);

        List<Game> toSandro = new ArrayList<>(sandro.getCollection());
        toSandro.add(hanabi);
        sandro.setCollection(toSandro);

        gameRepository.save(wonders);
        gameRepository.save(hanabi);

        designerRepository.save(bauza);

        publisherRepository.save(repos);
        publisherRepository.save(asmodee);

        userRepository.save(eric);
        userRepository.save(steve);
        userRepository.save(sandro);

        System.out.println("User Count: " + userRepository.count());
        System.out.println("Game Count: " + gameRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("Designer Count: " + designerRepository.count());
    }
}
