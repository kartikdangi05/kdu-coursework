
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CSVProcessor {

    public static ConcurrentMap<String,TradersData> readTraders(String path){
        ConcurrentHashMap<String,TradersData> tradersDataList = new ConcurrentHashMap<>();
        String csvFile = path;
        boolean firstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(firstLine){
                    firstLine = false; continue;
                }
                String firstName = values[1];
                String lastName = values[2];
                String phone = values[3];
                String walletAddress = values[4];

                TradersData obj = new TradersData(firstName,lastName,phone,walletAddress);
                tradersDataList.put(walletAddress,obj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tradersDataList;
    }

    public static ConcurrentMap<String,CoinsData> readCoins(String path) {
        String csvFile = path;
        ConcurrentHashMap<String,CoinsData> coinsDataList = new ConcurrentHashMap<>();
        boolean firstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(firstLine){
                    firstLine = false; continue;
                }
                int rank = Integer.parseInt(values[1]);
                String name = values[2];
                String symbol = values[3];
                double price = Double.parseDouble(values[4]);
                Long supply = Long.parseLong(values[5]);

                CoinsData obj = new CoinsData(rank,name,symbol,price,supply);
                coinsDataList.put(symbol,obj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return coinsDataList;
    }
}