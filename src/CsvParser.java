import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public static List<Country> ParseCsv(List<String> csv){
        var countryList = csv.stream().skip(1).map(x -> parseString(x)).filter(x -> x != null).toList();

        return countryList;
    }

    private static Country parseString(String singleString){
        var country = new Country();
        var splitted = singleString.split(",");
        var clear = new ArrayList<String>();

        var inStr = false;
        var buff = "";
        for (var item: splitted) {
            item = item.replace("'","''");
            if (!item.contains("\"") && !inStr){
                clear.add(item);
            }
            else if(item.startsWith("\"")){
                inStr = true;
                buff += item.substring(1);
            } else if (item.endsWith("\"")) {
                inStr = false;
                buff += item.substring(0, item.length() - 2);
                clear.add(buff);
                buff = "";
            } else if (inStr) {
                buff += item;
            }
        }
        if (clear.size() < 5)
            return null;

        System.out.println(String.join(", ", clear));
        country.setName(clear.get(0));
        country.setSubregion(clear.get(1));
        country.setRegion(clear.get(2));
        country.setInternetUsers(Integer.parseInt(clear.get(3)));
        country.setPopulation(Integer.parseInt(clear.get(4)));

        return country;
    }
}
