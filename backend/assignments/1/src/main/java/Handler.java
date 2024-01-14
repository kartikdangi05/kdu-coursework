import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class Handler {
    private Handler(){}

    public static void retrieveCoinDetails(ConcurrentMap<String, CoinsData> coinsDataHashMap, String coin){
        Logging.logInfo(coinsDataHashMap.get(coin).objToString());
    }

    public static void showTopNCoins(ConcurrentMap<String, CoinsData> coinsDataHashMap, int n){
        List<CoinsData> coinsList = new ArrayList<>(coinsDataHashMap.values());

        // Sort the coins by price in descending order
        List<CoinsData> topNCoins = coinsList.stream()
                .sorted(Comparator.comparingDouble(CoinsData::getPrice).reversed())
                .limit(n)
                .toList();

        topNCoins.forEach(coin -> Logging.logInfo("\nCoin : ".concat(coin.getName())
                .concat(" | Price : ").concat(Double.toString(coin.getPrice()))));
    }

    public static void showPortfolio(ConcurrentMap<String, TradersData> tradersDataHashMap, String walletAdd){
        Logging.logInfo(tradersDataHashMap.get(walletAdd).objToString());
    }

    public static void showProfitLoss(ConcurrentMap<String, TradersData> tradersDataHashMap, String walletAdd){
        TradersData cust = tradersDataHashMap.get(walletAdd);
        Logging.logInfo("\nProfit/loss of ".concat(cust.getFirstName()).concat(" ")
                .concat(cust.getLastName()).concat(" : ").concat(Double.toString(cust.getProfitLoss())));
    }

    public static void showTopBottomTraders(ConcurrentMap<String, TradersData> tradersDataHashMap){

        List<TradersData> tradersDataList = new ArrayList<>(tradersDataHashMap.values());

        List<TradersData> top5 = tradersDataList.stream()
                .sorted(Comparator.comparingDouble(TradersData::getProfitLoss).reversed())
                .limit(5)
                .toList();

        List<TradersData> bottom5 = tradersDataList.stream()
                .sorted(Comparator.comparingDouble(TradersData::getProfitLoss))
                .limit(5)
                .toList();

        Logging.logInfo("\nTop 5 traders : \n");
        top5.forEach(trader -> Logging.logInfo("Name : ".concat(trader.getFirstName())
                .concat(" | Profit/Loss : ").concat(Double.toString(trader.getProfitLoss()))));

        Logging.logInfo("\nBottom 5 traders : \n");
        bottom5.forEach(trader -> Logging.logInfo("Name : ".concat(trader.getFirstName())
                .concat(" | Profit/Loss : ").concat(Double.toString(trader.getProfitLoss()))));
    }
}
