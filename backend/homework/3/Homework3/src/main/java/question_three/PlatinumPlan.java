package question_three;

public class PlatinumPlan extends HealthInsurancePlan {
    public PlatinumPlan(){
        super.setCoverage(0.9);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.08 * salary;
    }
}
