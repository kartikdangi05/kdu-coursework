package assignment_one;

public class CoinsData {
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private Long supply;

    public CoinsData(int rank, String name, String symbol, double price, Long supply){
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.supply = supply;
    }

    public String objToString(){
        return "Rank : ".concat(Integer.toString(rank)).concat(" | Name : ").concat(name).concat(" | Price : ").
                concat(Double.toString(price)).concat(" | Supply : ").concat(Long.toString(supply));
    }

    public int getRank() {
        return rank;
    }

    public double getPrice() {
        return price;
    }

    public Long getSupply() {
        return supply;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSupply(Long supply) {
        this.supply = supply;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
