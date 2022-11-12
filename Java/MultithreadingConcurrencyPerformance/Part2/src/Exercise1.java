import java.util.Random;
import java.util.stream.Stream;

public class Exercise1 {

    public static final int MAX_GUESS = 9999;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_GUESS));

        Stream.of(new AscendingHackerThread(vault),
                        new DescendingHackerThread(vault),
                        new PoliceThread())
                .forEach(Thread::start);
    }

    private static class Vault {
        private final int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrect(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(MAX_PRIORITY);
        }
    }

    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_GUESS; guess++) {
                if (vault.isCorrect(guess)) {
                    System.out.println(this.getName() + " guessed the pass " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_GUESS; guess >= 0; guess--) {
                if (vault.isCorrect(guess)) {
                    System.out.println(this.getName() + " guessed the pass " + guess);
                    System.exit(0);
                }
            }
        }
    }

    public static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("Police coming in " + i);
            }
            System.out.println("Game over for you hacker!");
            System.exit(0);
        }
    }
}
