package question_three;

public class BronzePlan extends HealthInsurancePlan{
    public BronzePlan(){
        super.setCoverage(0.6);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.05 * salary;
    }
}
