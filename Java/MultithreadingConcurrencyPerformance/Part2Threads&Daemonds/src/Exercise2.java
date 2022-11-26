import java.math.BigInteger;

public class Exercise2 {

    public static class ComplexCalculation {

        public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
            PowerCalculatingThread powerCalculatingThread1 = new PowerCalculatingThread(base1, power1);
            PowerCalculatingThread powerCalculatingThread2 = new PowerCalculatingThread(base2, power2);

            powerCalculatingThread1.start();
            powerCalculatingThread2.start();

            powerCalculatingThread1.join(5000);
            powerCalculatingThread2.join(5000);

            return powerCalculatingThread1.getResult().add(powerCalculatingThread2.getResult());
        }

        private static class PowerCalculatingThread extends Thread {
            private BigInteger result = BigInteger.ONE;
            private BigInteger base;
            private BigInteger power;

            public PowerCalculatingThread(BigInteger base, BigInteger power) {
                this.base = base;
                this.power = power;
            }

            @Override
            public void run() {
                BigInteger result = BigInteger.ONE;
                for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Prematurely interrupted computation");
                        return;
                    }
                    result = result.multiply(base);
                }
            }

            public BigInteger getResult() {
                return result;
            }
        }
    }
}