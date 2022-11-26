import java.math.BigInteger;

public class Lesson2DaemonsAndTermination {

    //Thread coordination -> stop thread from another thread
    //Thread.interrupt()
    //Daemon threads

    //Thread consumes resources (Memory, cache, Cpu..) sometime we want to liberate thread resources
    //The app will not stop until all threads stops

    //Interrupt
    public static void main(String[] args) {
        /*Thread thread = new Thread(new BlockingTask());
        thread.start();
        thread.interrupt();*/

        Thread thread1 = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("10000000000")));
        thread1.start();
        thread1.interrupt();
    }

    public static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

    public static class LongComputationTask implements Runnable {

        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^"+ power+" = " + pow(base, power));
        }

        public BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) { //That way we can check if our thread it's call to be interrupted from other and gracefully stops
                    System.out.println("Prematurely interrupted computation"); //If we don't define a behaviour for interrupted message the thread won't do anything
                    return BigInteger.ZERO;                                    //If the method can handle interrupt exception we only need a try/catch and return in catch
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
    /*
      Conclusions
          Thread interrupt function only stops the thread if it's the same thread witch wants stop himself but always set interrupt var to true
          We can override the interrupt function to perform whatever we want
          Or we can check if interrupt var it's true and perform whatever we want
    */


    /* Daemon thread = Background thread that do not prevent the app from exiting if the main thread terminates
       By default java threads aren't daemon
       Scenarios
       1- If we got a file editor, and we got a thread that saves every 5 min, when we close the app we don't want to wait finish
       2- Code in a worker thread is not under our control, and we do not want it to block our app from terminating
            - worker using in an external library witch might not handle interrupt
            - If LongComputationTask didn't have Thread.currentThread().isInterrupted() behaviour defined, we can create the thread
              witch executes this task as daemon true to allow us to terminate the app until worker didn't finished yet
              Thread thread1 = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("10000000000")));
              thread1.start();
              thread1.setDaemon(true);
    * */
}
