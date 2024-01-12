package question_one_two;

import org.student.Logging;

public class MessageSender implements Runnable{

    private MessageQueue mq;

    public MessageSender(MessageQueue mq){
        this.mq = mq;
    }
    private synchronized void messageSend(){
        String tName = Thread.currentThread().getName();
        String msg = "Message sent by ".concat(tName);
        Logging.logInfo(msg);
        mq.getDq().add(msg);
    }
    @Override
    public void run() {
        messageSend();
    }
}
