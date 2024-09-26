package question_four;

import org.student.Logging;

public class Main {
    public static void main(String[] args) {
        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        double premium = insurancePlan.computeMonthlyPremium(5000, 56, true);
        Logging.logInfo("Monthly Premium: $" + premium);
    }
}
