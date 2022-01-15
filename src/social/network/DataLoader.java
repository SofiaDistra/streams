package social.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static List<Person> load(String directory, String filename) {

        Path path = Paths.get(directory + "\\" + filename);

        List<Person> people = new ArrayList<>();
        try (BufferedReader reader =
                     Files.newBufferedReader(path)) {
            Person person = new Person();
            String line = reader.readLine();

            String[] splitted = line.split("-");
            person.setId(splitted[0]);

            String[] contacts = splitted[1].split(",");
            for(String c : contacts) {
                person.addContact(c);
            }

            people.add(person);

        } catch (IOException x) {
            throw new UncheckedIOException(x);
        }

        return people;
    }
}
