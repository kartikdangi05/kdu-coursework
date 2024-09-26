package question_three;

public class SilverPlan extends HealthInsurancePlan{
    public SilverPlan(){
        super.setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.06 * salary;
    }
}
