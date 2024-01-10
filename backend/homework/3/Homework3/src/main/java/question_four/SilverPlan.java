package question_four;

public class SilverPlan extends HealthInsurancePlan {
    public SilverPlan(){
        super.setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
