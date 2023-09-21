public class Country {
    private int Id;
    private String Name;
    private String Subregion;
    private String Region;
    private int InternetUsers;
    private int Population;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSubregion() {
        return Subregion;
    }

    public void setSubregion(String subregion) {
        Subregion = subregion;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public int getInternetUsers() {
        return InternetUsers;
    }

    public void setInternetUsers(int internetUsers) {
        InternetUsers = internetUsers;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    public Country(){}

    public Country(String country, String subregion, String region, int internetUsers, int population){
        Name = country;
        Subregion = subregion;
        Region = region;
        InternetUsers = internetUsers;
        Population = population;
    }
}
