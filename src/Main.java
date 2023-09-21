import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Введите путь до csv");
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var path = reader.readLine();
        var strings = Files.readAllLines(Path.of(path));
        var classes = CsvParser.ParseCsv(strings);
        DbConnect.Conn();
        DbConnect.CreateDB();
        DbConnect.WriteCountries(classes);
        var grouping = classes.stream().collect(Collectors.groupingBy(Country::getSubregion));
        for (var subregion:
             grouping.keySet()) {
            var items = grouping.get(subregion);
            var internet = items.stream().collect(Collectors.summingInt(Country::getInternetUsers));
            var overall = items.stream().collect(Collectors.summingInt(Country::getPopulation));
            System.out.println(String.format("%s - %s", subregion, (internet.doubleValue()/overall)));
        }

        var countries = grouping.get("Eastern Europe");
        var max = countries.stream().collect(Collectors.maxBy(Comparator.comparingInt(Country::getInternetUsers)));
        System.out.println(max.get().getName());
        var ct = classes.stream().filter(x -> ((Integer)x.getInternetUsers()).doubleValue()/x.getPopulation() > 0.75
                && ((Integer)x.getInternetUsers()).doubleValue()/x.getPopulation() < 0.85).findFirst();
        System.out.println(ct.get().getName());
    }
}