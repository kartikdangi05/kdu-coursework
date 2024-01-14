import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

public class MenuOption extends Thread {
    public void run() {
        ConcurrentMap<String, CoinsData> coinsDataList = Main.getCoinsDataList();
        ConcurrentMap<String, TradersData> tradersDataList = Main.getTradersDataList();
        Scanner sc = new Scanner(System.in);
        boolean go = true;
        while(go){
            Logging.logInfo("\n1.Retrieve Coin Details\n2.Display top N coin\n3.Show Portfolio".
                            concat("\n4.Show profit/loss\n5.Show top 5 and bottom 5 traders\n6.Exit"));

            int opt = sc.nextInt();
            switch (opt){
                case 1 : {
                    Logging.logInfo("Enter symbol : ");
                    String sym = sc.next();
                    Handler.retrieveCoinDetails(coinsDataList,sym);
                    break;
                }
                case 2 : {
                    Logging.logInfo("Enter N : ");
                    int n = sc.nextInt();
                    Handler.showTopNCoins(coinsDataList,n);
                    break;
                }

                case 3 : {
                    Logging.logInfo("Enter wallet address : ");
                    String address = sc.next();
                    Handler.showPortfolio(tradersDataList,address);
                    break;
                }

                case 4 : {
                    Logging.logInfo("Enter wallet address : ");
                    String address = sc.next();
                    Handler.showProfitLoss(tradersDataList,address);
                    break;
                }

                case 5 : {
                    Handler.showTopBottomTraders(tradersDataList);
                    break;
                }

                case 6 : {
                    go = false; break;
                }
                default: {
                    Logging.logWarn("Invalid option!!");
                }

            }
        }
    }
}
