package question_three;

import org.student.Logging;

public class Main {
    public static void main(String[] args) {
        GoldPlan gp = new GoldPlan();
        double res = gp.computeMonthlyPremium(1000);
        Logging.logInfo(String.format("Monthly premium amount : %.3f", res));
    }
}