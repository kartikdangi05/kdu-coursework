package question_one_two;

import org.student.Logging;

public class MessageReciever implements Runnable{

    private MessageQueue mq;

    public MessageReciever(MessageQueue mq){
        this.mq = mq;
    }

    private synchronized boolean messageRecieve(){
        String tName = Thread.currentThread().getName();
        String msgDeque = mq.getDq().poll();
        if(msgDeque != null){
            String msg = "Message Received by ".concat(tName).concat(" . Message : ").concat(msgDeque);
            Logging.logInfo(msg);
            return true;
        }
        return false;
    }
    @Override
    public void run() {
        while(true){
            if(messageRecieve()){
                return;
            }
        }
    }
}
