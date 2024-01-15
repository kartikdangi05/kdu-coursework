package assignment_one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    public static ConcurrentMap<String,CoinsData> readCoins(String path,String type) {
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
                if(type.equals("coin"))
                    coinsDataList.put(symbol,obj);
                else
                    coinsDataList.put(name,obj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return coinsDataList;
    }

    public static ArrayList<String[]> readCoinsAsString(String path){
        String csvFile = path;
        ArrayList<String[]> res = new ArrayList<>();
        boolean firstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(firstLine){
                    firstLine = false; continue;
                }
                System.out.println(values);
                res.add(values);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}