package countries;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

import java.time.ZoneId;
import java.util.function.Function;

public class Homework2 {

    private List<Country> countries;

    public Homework2() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns the longest country name translation.
     */
    public Optional<String> streamPipeline1() {
        return countries.stream().flatMap(c -> c.getTranslations().values().stream()).max(Comparator.comparingInt(n -> n.length()));
    }

    /**
     * Returns the longest Italian (i.e., {@code "it"}) country name translation.
     */
    public Optional<String> streamPipeline2() {
        return countries.stream().flatMap(c -> c.getTranslations().entrySet().stream()).filter(i -> i.getKey() == "it").max(Comparator.comparingInt(n -> n.getValue().length())).map(g -> g.getValue());
    }

    /**
     * Prints the longest country name translation together with its language code in the form language=translation.
     */
    public void streamPipeline3() {
        System.out.println(countries.stream().flatMap(c -> c.getTranslations().entrySet().stream()).max(Comparator.comparingInt(n -> n.getValue().length())).get());
    }

    /**
     * Prints single word country names (i.e., country names that do not contain any space characters).
     */
    public void streamPipeline4() {
        countries.stream().map(n -> n.getName()).filter(c -> !c.contains(" ")).forEach(System.out::println);
    }

    /**
     * Returns the country name with the most number of words.
     */
    public Optional<String> streamPipeline5() {
        return countries.stream().max(Comparator.comparingInt(d -> d.getName().split(" ").length)).map(Country::getName);
    }

    /**
     * Returns whether there exists at least one capital that is a palindrome.
     */
    public boolean streamPipeline6() {
        return countries.stream().map(Country::getCapital).anyMatch(f -> f.toLowerCase().equals(new StringBuilder(f.toLowerCase()).reverse().toString()));
    }

    /**
     * Returns the country name with the most number of {@code 'e'} characters ignoring case.
     */
    private int charCount(String s, char c){
        int count = 0;
        for (char item : s.toCharArray()) {
            if (item == c) {
                count++;
            }
        }
        return count;
    }
    public Optional<String> streamPipeline7() {
        return countries.stream().map(Country::getName).max(Comparator.comparingInt(n -> charCount(n, 'e')));
    }

    private int vowelCount(String s){
        return charCount(s, 'a') +
                charCount(s, 'e') +
                charCount(s, 'i') +
                charCount(s, 'o') +
                charCount(s, 'u');
    }
    /**
     *  Returns the capital with the most number of English vowels (i.e., {@code 'a'}, {@code 'e'}, {@code 'i'}, {@code 'o'}, {@code 'u'}).
     */
    public Optional<String> streamPipeline8() {
        return countries.stream().map(Country::getCapital).max(Comparator.comparingInt(f -> vowelCount(f)));
    }

    /**
     * Returns a map that contains for each character the number of occurrences in country names ignoring case.
     */
    public Map<Character, Long> streamPipeline9() {
        return countries.stream().map(Country::getName).flatMap(name -> name.toLowerCase().chars().mapToObj(c -> (char)c)).collect(groupingBy(Function.identity(), counting()));
    }

    /**
     * Returns a map that contains the number of countries for each possible timezone.
     */
    public Map<ZoneId, Long> streamPipeline10() {
        return countries.stream().map(Country::getTimezones).flatMap(t -> t.stream()).collect(groupingBy(Function.identity(),counting()));
    }

    /**
     * Returns the number of country names by region that starts with their two-letter country code ignoring case.
     */
    public Map<Region, Long> streamPipeline11() {
        return countries.stream().filter(c -> c.getCode().equalsIgnoreCase(c.getName().substring(0,2))).collect(groupingBy(c -> c.getRegion(), counting()));
    }

    /**
     * Returns a map that contains the number of countries whose population is greater or equal than the population average versus the the number of number of countries with population below the average.
     */
    public Map<Boolean, Long> streamPipeline12() {
        // TODO
        return null;
    }

    /**
     * Returns a map that contains for each country code the name of the corresponding country in Portuguese ({@code "pt"}).
     */
    public Map<String, String> streamPipeline13() {
        return countries.stream().collect(toMap(p -> p.getCode(), g -> g.getTranslations().get("pt")));
    }

    /**
     * Returns the list of capitals by region whose name is the same is the same as the name of their country.
     */
    public Map<Region, List<String>> streamPipeline14() {
        // TODO
        return null;
    }

    /**
     *  Returns a map of country name-population density pairs.
     */
    public Map<String, Double> streamPipeline15() {
        // TODO
        return null;
    }

}
