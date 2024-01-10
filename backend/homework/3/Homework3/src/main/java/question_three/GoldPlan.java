package question_three;

public class GoldPlan extends HealthInsurancePlan{
    public GoldPlan(){
        super.setCoverage(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.07 * salary;
    }
}
