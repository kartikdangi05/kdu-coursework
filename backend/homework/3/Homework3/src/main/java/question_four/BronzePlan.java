package question_four;

public class BronzePlan extends HealthInsurancePlan {
    public BronzePlan(){
        super.setCoverage(0.6);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
