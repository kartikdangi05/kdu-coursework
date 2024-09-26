package question_two;

import question_one.Patient;

public class Billing {

    private Billing(){}
    private static double addDisc(double coverage){
        if(coverage == 0.9) return 50;
        else if(coverage == 0.8)    return 40;
        else if(coverage == 0.7)    return 30;
        else    return 25;
    }
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        double insuranceCompanyAmt;
        double patientAmt;

        if(patientInsurancePlan == null){
            insuranceCompanyAmt = 0;
            patientAmt = amount - 20;
        }
        else{
            double coverage = patientInsurancePlan.getCoverage();
            insuranceCompanyAmt = coverage*amount;
            patientAmt = amount - insuranceCompanyAmt - addDisc(coverage);
        }

        payments[0] = insuranceCompanyAmt;
        payments[1] = patientAmt;

        return payments;
    }
}
