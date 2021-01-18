package nl.sanderknopper.diningphilosophersjava;

import java.util.Arrays;

public class Waiter {
    private final boolean[] chopsticksInUse;
    private final int[] amountOfTimesEatenPerPhilosopher;

    public Waiter(int amountOfChopsticksAndPhilosophers) {
        chopsticksInUse = new boolean[amountOfChopsticksAndPhilosophers];
        amountOfTimesEatenPerPhilosopher = new int[amountOfChopsticksAndPhilosophers];
    }

    public void waitForAndGiveChopsticks(int philosopherId) throws InterruptedException {
        System.out.println("Philosopher " + philosopherId + " is waiting for chopsticks.");
        boolean obtainedChopsticks = false;
        while (!obtainedChopsticks) {
            synchronized (chopsticksInUse) {
                // First check if chopsticks are available
                if (!chopsticksInUse[philosopherId] && !chopsticksInUse[(philosopherId + 1) % chopsticksInUse.length]) {
                    // Now check if we haven't eaten more than others
                    if (fairToEat(philosopherId)) {
                        chopsticksInUse[philosopherId] = true;
                        chopsticksInUse[(philosopherId + 1) % chopsticksInUse.length] = true;

                        obtainedChopsticks = true;
                        amountOfTimesEatenPerPhilosopher[philosopherId]++;
                    } else {
                        System.out.println("Philosopher " + philosopherId + " should be polite and let others eat too.");
                    }
                }
            }

            Thread.sleep(125);
        }
    }

    private boolean fairToEat(int philosopherId) {
        int timesEaten = amountOfTimesEatenPerPhilosopher[philosopherId];
        return Arrays.stream(amountOfTimesEatenPerPhilosopher).filter(amount -> amount < timesEaten).findFirst().isEmpty();
    }

    public void returnChopsticks(int philosopherId) {
        synchronized (chopsticksInUse) {
            chopsticksInUse[philosopherId] = false;
            chopsticksInUse[(philosopherId + 1) % chopsticksInUse.length] = false;
        }
    }
}
