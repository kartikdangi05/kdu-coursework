package question_two;

import org.student.Logging;

import java.util.*;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 2;
    private static final int WAITINGLIST_LIMIT = 1;

    private static int confirmationNum = 1;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    private static void updateConfirmNum(int num){
        confirmationNum = num + 1;
    }
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {
        if (confirmedList.size() <= CONFIRMEDLIST_LIMIT || waitingList.size() <= WAITINGLIST_LIMIT) {
            confirmationNumber = confirmationNumber.concat("CN" + Integer.toString(confirmationNum));
            updateConfirmNum(confirmationNum);
        }

        Passenger ps = new Passenger(firstName,lastName,age,gender,travelClass,confirmationNumber);

        if(confirmedList.size() <= CONFIRMEDLIST_LIMIT - 1) {
            confirmedList.add(ps);
            Logging.logInfo("C".concat(Integer.toString(confirmedList.size())));
            Logging.logInfo("Confirmation number : CN".concat(Integer.toString(confirmationNum)));
            return true;
        }
        if(waitingList.size() <= WAITINGLIST_LIMIT - 1) {
            waitingList.add(ps);
            Logging.logInfo(Integer.toString(waitingList.size()));
            Logging.logInfo("Waiting number : CN".concat(Integer.toString(confirmationNum)));
            return true;
        }
        return false;
    }

    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> confirmedIter = confirmedList.iterator();
        Iterator<Passenger> waitingIter = waitingList.iterator();

        boolean removed;

        removed = removePassenger(confirmedIter,confirmationNumber);
        if(removed) {
            Passenger ps = waitingList.poll();
            confirmedList.add(ps);
            return true;
        }
        else {
            removed = removePassenger(waitingIter,confirmationNumber);
            return removed;
        }
    }

    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()) {
            Passenger ps = iterator.next();
            if(ps.getConfirmationNumber().equals(confirmationNumber)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
