import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringJoiner;

public class Lesson3WaitAndNotify {

    /*

        1. wait(), notify() and notifyAll() for inter-thread communication
           Object java class has this method then any class inherits it
           Then we can use every java class as a condition variable and a lock (using synchronized keyword)
           Important if we want to call any of this method we need to acquire the monitor (use synchronized on that object)

        2. BackPressure practical example with Producer Consumer
    */

    private static final String INPUT_FILE = "Part7InterThreadCommunication/src/matrices";
    private static final String OUPUT_FILE = "Part7InterThreadCommunication/src/matrices_result";
    private static final int N = 10;

    public static void main(String[] args) throws Exception {
        ThreadSafeQueue queue = new ThreadSafeQueue();
        File inputFile = new File(INPUT_FILE);
        File outputFile = new File(OUPUT_FILE);

        MatricesReaderProducer matricesReaderProducer = new MatricesReaderProducer(new FileReader(inputFile), queue);
        MatricesMultiplierConsumer matricesMultiplierConsumer = new MatricesMultiplierConsumer(new FileWriter(outputFile), queue);

        matricesReaderProducer.start();
        matricesMultiplierConsumer.start();
        /*
            With no backpressure the size of the queue grows till the producer ends and then the app queue starts to shrink
            That means the producer is really faster than consumer, multiply and save in file is slower
            In that case there is no problem, bcs we have limited matrices, but in other problems maybe the memory can be completed and produce a crash

            Then we need to implement a backpressure mechanism
        */

    }

    private static class MatricesMultiplierConsumer extends Thread {

        private ThreadSafeQueue queue;
        private FileWriter fileWriter;

        public MatricesMultiplierConsumer(FileWriter fileWriter, ThreadSafeQueue queue) {
            this.fileWriter = fileWriter;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                MatricesPair matricesPair = queue.remove();
                if (matricesPair == null) {
                    System.out.println("No more matrices to read from queue, consumer is terminating...");
                    break;
                }

                float[][] result = multiplyMatrices(matricesPair.matrix1, matricesPair.matrix2);

                try {
                    saveMatrixToFile(fileWriter, result);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public float[][] multiplyMatrices(float[][] m1, float[][] m2) {
            float[][] result = new float[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 0; k < N; k++) {
                        result[r][c] += m1[r][k] * m2[k][c];
                    }
                }
            }
            return result;
        }

        private void saveMatrixToFile(FileWriter fileWriter, float[][] result) throws IOException {
            for (int r = 0; r < N; r++) {
                StringJoiner stringJoiner = new StringJoiner(",");
                for (int c = 0; c < N; c++) {
                    stringJoiner.add(String.format("%.2f", result[c][c]));
                }
                fileWriter.write(stringJoiner.toString());
                fileWriter.write("\n");
            }
            fileWriter.write("\n");
        }
    }

    private static class MatricesReaderProducer extends Thread {
        private Scanner scanner;
        private ThreadSafeQueue queue;

        public MatricesReaderProducer(FileReader reader, ThreadSafeQueue queue) {
            this.scanner = new Scanner(reader);
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                float[][] matrix1 = readMatrix();
                float[][] matrix2 = readMatrix();
                if (matrix1 == null || matrix2 == null) {
                    queue.terminate();
                    System.out.println("No more matrices to read from file. Producer thread terminating...");
                    return;
                }

                MatricesPair matricesPair = new MatricesPair();
                matricesPair.matrix1 = matrix1;
                matricesPair.matrix2 = matrix2;
                queue.add(matricesPair);
            }
        }

        private float[][] readMatrix() {
            float[][] matrix = new float[N][N];

            for (int r = 0; r < N; r++) {
                if (!scanner.hasNext()) {
                    return null;
                }
                String[] line = scanner.nextLine().split(",");
                for (int c = 0; c < N; c++)
                    matrix[r][c] = Float.parseFloat(line[c]);
            }
            scanner.nextLine();
            return matrix;
        }
    }

    private static class ThreadSafeQueue {
        private Queue<MatricesPair> queue = new LinkedList<>();
        private boolean isEmpty = true;
        private boolean isTerminate = false;
        private static final int CAPACITY = 5; //limiting the size of the storage allows use to implement backpressure and avoid possible crashing

        public synchronized void add(MatricesPair matricesPair) {
            while (queue.size() == 5) { //Adding this to implement backpressure, sleeping producers while consumers are ending this part of work
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.add(matricesPair);
            isEmpty = false;
            notify();
        }

        public synchronized MatricesPair remove() {
            MatricesPair matricesPair;
            while (isEmpty && !isTerminate) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (queue.size() == 1) {
                isEmpty = true;
            }
            if (queue.size() == 0 && isTerminate) {
                return null;
            }
            System.out.println("Queue size " + queue.size());

            matricesPair = queue.remove();
            if (queue.size() == CAPACITY - 1) { //Adding this to wake up producer when partial work is being completed
                notifyAll();
            }
            return matricesPair;
        }

        public synchronized void terminate() {
            isTerminate = true;
            notifyAll();
        }
    }

    private static class MatricesPair {
        public float[][] matrix1;
        public float[][] matrix2;
    }
}
