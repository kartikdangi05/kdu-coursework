package assignment_one;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final String COIN_PATH = "src/main/resources/coins.csv";
    private static ConcurrentMap<String,CoinsData> coinsDataList;
    private static ConcurrentMap<String,TradersData> tradersDataList;
    private static Map<String, Lock> coinLocks = new ConcurrentHashMap<>();
    private static Map<String, Lock> walletAddressLocks = new ConcurrentHashMap<>();

    public static ConcurrentMap<String, CoinsData> getCoinsDataList() {
        return coinsDataList;
    }

    public static void setCoinsDataList(ConcurrentMap<String, CoinsData> coinsDataList) {
        Main.coinsDataList = coinsDataList;
    }

    public static ConcurrentMap<String, TradersData> getTradersDataList() {
        return tradersDataList;
    }

    public static void setTradersDataList(ConcurrentMap<String, TradersData> tradersDataList) {
        Main.tradersDataList = tradersDataList;
    }

    public static Lock getCoinLock(String coin) {
        return coinLocks.computeIfAbsent(coin, k -> new ReentrantLock());
    }

    public static Lock getWalletAddressLock(String walletAddress) {
        return walletAddressLocks.computeIfAbsent(walletAddress, k -> new ReentrantLock());
    }


    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for(JsonNode jsonNode : jsonTransactions){
            executorService.submit(new ExecuteTransaction(jsonNode));
            latch.countDown();
        }

        executorService.shutdown();

    }
    public static void main(String[] args) {
        coinsDataList = CSVProcessor.readCoins(COIN_PATH,"coin");
        ConcurrentMap<String,CoinsData> coinsDataListByName = CSVProcessor.readCoins(COIN_PATH,"name");
        tradersDataList = CSVProcessor.readTraders("src/main/resources/traders.csv");
        CSVProcessor.readCoinsAsString(COIN_PATH); // FOR TEST

        JsonNode jsonTransactions = JSONProcessor.readJSON("src/main/resources/small_transaction.json");
        CountDownLatch latch = new CountDownLatch(50);

        MenuOption menuOption = new MenuOption();
        menuOption.start();

        if(jsonTransactions == null){
            Logging.logInfo("Null json file!!");
        }
        else
            executeTransactions(jsonTransactions, latch);


        try{
            latch.await();
        }catch (InterruptedException e){
            Logging.logWarn("Error shutting down all threads!!");
        }


    }
}
