package question_three;

public abstract class HealthInsurancePlan {
    double coverage;

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public abstract double computeMonthlyPremium(double salary);

    // Don't worry about the below code and also the InsuranceBrand class
}
