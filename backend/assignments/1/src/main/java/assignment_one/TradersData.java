package assignment_one;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TradersData {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String walletAddress;
    private double profitLoss;

    private ConcurrentMap<String,Long> qtyMap;
    private ConcurrentMap<String, Double> priceMap;

    public TradersData(String firstName, String lastName, String phoneNumber, String walletAddress){
        this.qtyMap = new ConcurrentHashMap<>();
        this.priceMap = new ConcurrentHashMap<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.walletAddress = walletAddress;
        this.profitLoss = 0;
    }

    public String objToString(){
        String res = "FirstName : ".concat(firstName).concat(" | LastName : ").concat(lastName)
                .concat(" | PhoneNumber : ").concat(phoneNumber).concat(" | Profit/Loss : ").
                concat(Double.toString(profitLoss)).concat("\nCoins : ");
        String neww = res;
        if(!qtyMap.isEmpty()){
            for(String key : qtyMap.keySet()){
                Long q = qtyMap.get(key);
                double p = priceMap.get(key);
                neww = neww.concat("\nCoin : ".concat(key).concat(" | Qty : ").concat(Long.toString(q))
                        .concat(" | Price : ").concat(Double.toString(p)));
            }
        }
        else{
            neww = res.concat("\n NULL");
        }
        return neww;
    }


    public double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setPrice(ConcurrentMap<String, Double> price) {
        this.priceMap = price;
    }

    public ConcurrentMap<String, Double> getPrice() {
        return priceMap;
    }

    public void setQty(ConcurrentMap<String, Long> qty) {
        this.qtyMap = qty;
    }

    public ConcurrentMap<String, Long> getQty() {
        return qtyMap;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

}
