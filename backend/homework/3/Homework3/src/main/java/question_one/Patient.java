package question_one;

import question_two.HealthInsurancePlan;

public class Patient extends User{
    private long patientId;
    private boolean insured;
    HealthInsurancePlan insurancePlan;

    public long getPatientId(){
        return this.patientId;
    }
    public void setPatientId(long patientId){
        this.patientId = patientId;
    }
    public boolean getInsured(){
        return this.insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }
}