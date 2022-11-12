import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public class Lesson3Join {

    //Thread coordination with join, with this instruction we can guarantee that the thread witch we depend on completes his work
    //Different threads run independently and order of execution is out of our control, with join we can aggregate work
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = List.of(0L, 3435L, 34435L, 2324L, 4556L, 23L, 2435L, 556787L);

        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true); //To avoid big numbers continue executing after join with time
            thread.start();
        }

        for (Thread thread : threads) {
            //thread.join();
            /* If we add join here for each thread we main thread executes the next for all results will be available bcs we are waiting for each
               But if we wait for each number/thread and a number it's veeery big we wouldn't get the result till all threads ends
               We can define a maxium wait for each number with
            */
            thread.join(2000); //If thread doesn't finish in time will continue after join
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    public static class FactorialThread extends Thread {

        private final long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger((Long.toString(i))));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
