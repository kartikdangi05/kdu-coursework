package question_one_two;

import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageQueue {

    private Deque<String> dq;

    public Deque<String> getDq(){
        return this.dq;
    }

    MessageQueue(){
        dq = new LinkedBlockingDeque<>();
    }

    public static void main(String[] args)  {

        MessageQueue mq = new MessageQueue();

//         question 1
        for(int i = 1; i <= 3; i++){
            MessageSender msgSend = new MessageSender(mq);
            MessageReciever msgRcvr = new MessageReciever(mq);
            Thread t1 = new Thread(msgSend);
            t1.setName("Sender Thread ".concat(Integer.toString(i)));
            Thread t2 = new Thread(msgRcvr);
            t2.setName("Receiver Thread ".concat(Integer.toString(i)));

            t1.start();
            t2.start();

        }



//         question 2
        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            senderThreadPool.submit(new MessageSender(mq));
            receiverThreadPool.submit(new MessageReciever(mq));
        }

        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }
}
