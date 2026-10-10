package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    static void main() throws ExecutionException, InterruptedException {
        CompletableFuture<String> productDetails = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                System.out.println(Thread.currentThread().getName());
            }
            return "Notebook | $4.99 | In stock";
        });

        System.out.println("Submitted, doing other work...");
        // Imagine other work here

        CompletableFuture<Void> voidCompletableFuture =
                productDetails.thenApply(productName -> "Product: " + productName).thenAccept(System.out::println);

        voidCompletableFuture.get(); // Wait for the CompletableFuture to complete
    }
}
