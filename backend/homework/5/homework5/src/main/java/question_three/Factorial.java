package question_three;

import org.student.Logging;

public class Factorial extends Thread{

    private int num;

    public Factorial(int num){
        this.num = num;
    }
    public void calculateFact(){
        int res = 1;
        for(int i = 2; i <= num ; i++){
            res *= i;
        }
        Logging.logInfo("Factorial : ".concat(Integer.toString(res)));
    }

    @Override
    public void run() {
        calculateFact();
    }
}
