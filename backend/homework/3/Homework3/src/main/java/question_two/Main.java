package question_two;

import question_one.Patient;
import org.student.Logging;

public class Main {
    public static void main(String[] args) {
        HealthInsurancePlan insurancePlan = new GoldPlan();
        Patient patient = new Patient();
        patient.setInsurancePlan(insurancePlan);
        double[] payments = Billing.computePaymentAmount(patient, 1000.0);

        Logging.logInfo(String.format("%nInsurance Company Amount : %.3f %nPatient Amount : %.3f",payments[0],payments[1]));
    }
}
