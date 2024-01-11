package question_two;

import org.student.Logging;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketReservation ts = new TicketReservation();
        Scanner sc = new Scanner(System.in);

        while(true){
            Logging.logInfo("What do you want to do?");
            Logging.logInfo("1. Book Ticket \n2. Cancel Ticket\n3. Exit");

            int input;
            input = sc.nextInt();

            if(input == 1){
                Logging.logInfo("Enter firstname, lastName, age, gender, travelclass, confirmNum");
                String firstName = sc.next();
                String lastName = sc.next();
                int age = sc.nextInt();
                String gender = sc.next();
                String travelclass = sc.next();

                boolean res = ts.bookFlight(firstName,lastName,age,gender,travelclass,"");
                if(res){
                    Logging.logInfo("Booking confirmed!");
                }
                else{
                    Logging.logWarn("Booking failed! Confirmed and waiting lists are full!");
                }
            }
            else if(input == 2){
                Logging.logInfo("Enter Confirmation/Waiting Number");
                String confNum = sc.next();

                boolean res = ts.cancel(confNum);
                if(res){
                    Logging.logInfo("Booking cancelled!");
                }
                else{
                    Logging.logErr("Invalid Confirmation Number!");
                }
            }
            else{
                break;
            }
        }


    }
}
