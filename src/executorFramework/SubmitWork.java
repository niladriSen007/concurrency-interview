package executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SubmitWork {
    static void main() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> sendConfirmationEmail(1));
        executorService.submit(() -> sendConfirmationEmail(2));
        executorService.submit(() -> sendConfirmationEmail(3));
        executorService.submit(() -> sendConfirmationEmail(4));

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("All tasks completed on " + Thread.currentThread().getName());

    }

    static void sendConfirmationEmail(int orderId) {
        System.out.println("Sending email for order " + orderId + " on " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
