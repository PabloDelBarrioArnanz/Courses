//Thread creation with java.lang.Runnable
//Thread class capabilities
//Thread debugging
public class Lesson1ThreadCreation {

    //Thread creation

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Code that will run in a new thread
                //3
                System.out.println("We are in the new thread: " + Thread.currentThread().getName());
                System.out.println("New worker priority is: " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("NewWorker");
        thread.setPriority(Thread.MAX_PRIORITY); //1-10
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error has occurred inside the thread" + t.getName() + ", error => " + e.getMessage());
            }
        });

        Thread lambdaThread = new Thread(() -> {
            //Code that will run in a new thread
        });

        //1
        System.out.println("We are in thread: " + Thread.currentThread().getName());
        thread.start(); //JVM will create new thread and passing to OS
        //2
        System.out.println("We are in thread: " + Thread.currentThread().getName());

        Thread.sleep(10000); //make current thread sleep n millis
    }

    //That will print first 1 then 2 and latter 3 bcs main thread is already created and will be faster than new thread to make de print

    //Other way to create a Thread is to create a class and extends it from Thread
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("New Thread with name " + this.getName());
        }
    }
}