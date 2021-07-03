package ebgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class GameRepository {
    private List<Game> games = new ArrayList<>();

    public void addGame(Game game) {
        games.add(game);
    }

    public void loadFile() {
        try (BufferedReader bf = Files.newBufferedReader(Path.of("src/main/resources/results.csv"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] result = line.split(";");
                games.add(new Game(result[0], result[1], Integer.parseInt(result[2]), Integer.parseInt(result[3])));
            }
        } catch (IOException e) {
            throw new IllegalStateException("file error");
        }
    }

    public Game biggestScoreDiff() {
        return games.stream()
                .max(Comparator.comparing(Game::getScoreDiff))
                .get();
    }

    public int goalnumber(String s) {
        return countryList().stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(s))
                .mapToInt(c -> c.getScore())
                .sum();
    }

    private List<Country> countryList() {
        List<Country> c1 = (List<Country>) games.stream()
                .map(g -> new Country(g.getFirstCountry(), g.getFirstCountryScore()))
                .collect(Collectors.toList());
        List<Country> c2 = (List<Country>) games.stream()
                .map(g -> new Country(g.getSecondCountry(), g.getSecondCountryScore()))
                .collect(Collectors.toList());
        c1.addAll(c2);
        return c1;
    }

    public String maxScore() {
        Map<String, Integer> stat = new HashMap<>();
        for (Country item : countryList()) {
            if (!stat.containsKey(item.getCountry())) {
                stat.put(item.getCountry(), 0);
            }
            stat.put(item.getCountry(), stat.get(item.getCountry()) + item.getScore());
        }
        return stat.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get().getKey();
    }
}

