package question_three;

import org.student.Logging;

import java.util.ArrayList;
import java.util.List;

public class Factor extends Thread{
    private int num;

    public Factor(int num){
        this.num = num;
    }
    public void calculateFactors(int num) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                if (i * i != num) factors.add(i);
                factors.add(num / i);
            }
        }
        Logging.logInfo("Factors are : ");
        for(Integer fact : factors){
            Logging.logInfo(Integer.toString(fact));
        }
    }

    @Override
    public void run() {
        calculateFactors(num);
    }
}
