package nl.sanderknopper.diningphilosophersjava;

import java.util.Arrays;

public class Application {
    private static final int AMOUNT_OF_PHILOSOPHERS_AND_CHOPSTICKS = 5;

    public static void main(String[] args) {
        Waiter waiter = new Waiter(AMOUNT_OF_PHILOSOPHERS_AND_CHOPSTICKS);

        Thread[] philosophers = new Thread[AMOUNT_OF_PHILOSOPHERS_AND_CHOPSTICKS];
        for (int i = 0; i < AMOUNT_OF_PHILOSOPHERS_AND_CHOPSTICKS; i++) {
            philosophers[i] = new Thread(new Philosopher(i, waiter), "Philosopher " + i);
            philosophers[i].start();
        }

        for (int i = 0; i < AMOUNT_OF_PHILOSOPHERS_AND_CHOPSTICKS; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}