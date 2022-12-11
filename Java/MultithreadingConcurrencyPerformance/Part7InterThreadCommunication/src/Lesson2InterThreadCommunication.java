public class Lesson2InterThreadCommunication {

    /*

        1. Review inter-thread communication
           - Thread.interrupt to send a signal to a thread and stops it
           - Thread.join to wait for a thread a being notified when finish
           - Semaphore release if a thread makes this and other thread was waiting for semaphore will be notified

        2. Semaphore as a particular case of condition variable
           Calling acquire on a semaphore it's like checking "is number of permits > 0" this is a condition variable

        3. Condition variables java.util.concurrent.locks.Condition
           Are conditions witch have to be ok before to continue, any thread will sleep till another thread set condition ok then every thread sleeping will be notified

    */
}
