import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecuteTransaction implements Runnable {

    private JsonNode jsonNode;

    private static final Map<String, Lock> coinLocks = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Queue<JsonNode>> pendingBuyRequests = new ConcurrentHashMap<>();



    public ExecuteTransaction(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    public ExecuteTransaction(){}

    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();

        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }

    private void processPendingBuyRequests(String coin) {
        Queue<JsonNode> pendingRequests = pendingBuyRequests.get(coin);
        if (pendingRequests != null && !pendingRequests.isEmpty()) {
            while (!pendingRequests.isEmpty()) {
                JsonNode pendingRequest = pendingRequests.poll();
                processBuy(pendingRequest, coin, pendingRequest.get("data").get("wallet_address").asText());
            }
        }
    }


    private void processBuy(JsonNode data, String coin, String walletAddress) {
        ConcurrentMap<String, TradersData> tradersDataList = Main.getTradersDataList();
        ConcurrentMap<String, CoinsData> coinsDataList = Main.getCoinsDataList();

        Long buyQty = data.get("quantity").asLong();
        CoinsData coinsData = coinsDataList.get(coin);
        Long currQty = coinsData.getSupply();

        TradersData trader = tradersDataList.get(walletAddress);
        ConcurrentMap<String, Long> quantity = trader.getQty();
        ConcurrentMap<String, Double> price = trader.getPrice();

        Long existingQty = quantity.getOrDefault(coin, 0L);

        if (currQty >= buyQty) {
            quantity.put(coin, existingQty + buyQty);
            price.put(coin, coinsData.getPrice());

            trader.setQty(quantity);
            trader.setPrice(price);
            coinsData.setSupply(currQty - buyQty);

            tradersDataList.put(walletAddress, trader);
            coinsDataList.put(coin, coinsData);

            Main.setTradersDataList(tradersDataList);
            Main.setCoinsDataList(coinsDataList);

            getBlockHash();
            Logging.logInfo("Trader ".concat(trader.getFirstName()).concat(" bought ").concat(coin)
                    .concat(" successfully!"));
        } else {
            pendingBuyRequests.computeIfAbsent(coin, k -> new ConcurrentLinkedQueue<>()).add(data);
            Logging.logInfo("Trader ".concat(trader.getFirstName()).concat(" has a pending BUY request for ").concat(coin)
                    .concat(". Waiting for sufficient coins or volume increase."));
        }
    }

    private void processSell(JsonNode data, String coin, String walletAddress){
        Long sellQty = data.get("quantity").asLong();
        ConcurrentMap<String, CoinsData> coinsDataList = Main.getCoinsDataList();
        ConcurrentMap<String, TradersData> tradersDataList = Main.getTradersDataList();

        TradersData trader = tradersDataList.get(walletAddress);
        CoinsData coinsData = coinsDataList.get(coin);

        if(!trader.getQty().containsKey(coin)){
            Logging.logErr("Cant find this coin in traders portfolio!");
            return;
        }

        ConcurrentMap<String, Long> quantity = trader.getQty();
        Long currQty = quantity.get(coin);

        if (sellQty <= currQty) {
            ConcurrentMap<String, Double> price = trader.getPrice();
            trader.setProfitLoss(trader.getProfitLoss() + (coinsData.getPrice() - price.get(coin))*sellQty);
            quantity.put(coin, currQty - sellQty);
            trader.setQty(quantity);
            coinsData.setSupply(currQty + sellQty);
        }
        else{
            Logging.logWarn("Don't have enough coins to sell!");
            return;
        }

        tradersDataList.put(walletAddress, trader);
        coinsDataList.put(coin, coinsData);

        Main.setTradersDataList(tradersDataList);
        Main.setCoinsDataList(coinsDataList);

        getBlockHash();
        Logging.logInfo(trader.getFirstName().concat(" sold ".concat(coin).concat(" successfully")));
    }

    private void processUpdate(JsonNode data, String coin){
        double price = data.get("price").asDouble();
        ConcurrentMap<String, CoinsData> coinsDataList = Main.getCoinsDataList();
        CoinsData coinsData = coinsDataList.get(coin);
        coinsData.setPrice(price);

        coinsDataList.put(coin, coinsData);
        Main.setCoinsDataList(coinsDataList);

        getBlockHash();
        Logging.logInfo("Price of ".concat(coin).concat(" updated successfully!"));
    }

    private void processAdd(JsonNode data, String coin){
        ConcurrentMap<String, CoinsData> coinsDataList = Main.getCoinsDataList();
        Long updateQty = data.get("volume").asLong();

        CoinsData coinsData = coinsDataList.get(coin);
        Long qty = coinsData.getSupply();

        if(updateQty > Long.MAX_VALUE - qty){
            Logging.logWarn("Coin exceeded maximum volume!!");
            return;
        }
        coinsData.setSupply(qty + updateQty);

        coinsDataList.put(coin, coinsData);
        Main.setCoinsDataList(coinsDataList);

        getBlockHash();
        Logging.logInfo(coin.concat(" has been added successfully. Total added -> ".concat(Long.toString(coinsData.getSupply()))));
    }

    @Override
    public void run() {
            String type = jsonNode.get("type").asText();
            JsonNode data = jsonNode.get("data");
            String coin = data.get("coin").asText();

            Lock coinLock = coinLocks.computeIfAbsent(coin, k -> new ReentrantLock());

            coinLock.lock();
            try {
                if (type.equals("BUY")) {
                    String walletAddress = data.get("wallet_address").asText();
                    processBuy(data, coin, walletAddress);
                } else if (type.equals("SELL")) {
                    String walletAddress = data.get("wallet_address").asText();
                    processSell(data, coin, walletAddress);
                    processPendingBuyRequests(coin);
                } else if (type.equals("UPDATE_PRICE")) {
                    processUpdate(data, coin);
                } else {
                    processAdd(data, coin);
                    processPendingBuyRequests(coin);
                }
            } finally {
                coinLock.unlock();
            }

    }
}
