import java.util.Random;

public class Lesson2AtomicVolatile {

    /*
        Atomic operation
         - Most of the operations are not atomic
         - Get and set references to objects its atomic
             Example getters and setters in a class
         - All assignment to primitive vars except long and double
         - Volatile modification make assignment volatile and disable memory optimizations for independent memory caches
           making write this var in RAM
            volatile double x = 1.0;
            volatile double y = 2.0;
            a = y; //atomic
         - Classes in the java.util.concurrency.atomic
    */
    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogic1 = new BusinessLogic(metrics);
        BusinessLogic businessLogic2 = new BusinessLogic(metrics);

        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogic1.start();
        businessLogic2.start();
        metricsPrinter.start();
    }

    public static class BusinessLogic extends Thread {
        private final Metrics metrics;
        private final Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                long end = System.currentTimeMillis();

                metrics.addSample(end - start);
            }
        }
    }

    private static class MetricsPrinter extends Thread {
        private final Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                double currentAverage = metrics.getAverage();
                System.out.println("Current average is " + currentAverage);
            }
        }
    }

    public static  class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count; //atomic for volatile
        }

        public double getAverage() {
            return average; //atomic for volatile
        }
    }
}