package questions.oddEven;

import java.util.concurrent.atomic.AtomicInteger;

class OddEvenNaive {
	private final int num;
	private AtomicInteger state = new AtomicInteger(1);

	public OddEvenNaive(int num) {
		this.num = num;
	}

	public void odd() {
		for (int i = 1; i <= num; i += 2) {
			while (state.get() != 1) {
			}

			System.out.println(i + " by "+Thread.currentThread().getName());

			state.set(2);
		}
	}

	public void even() {
		for (int i = 2; i <= num; i += 2) {
			while (state.get() != 2) {
			}

			System.out.println(i + " by "+Thread.currentThread().getName());

			state.set(1);
		}
	}
}

public class OddEvenPrintWithTwoThreads {
	static void main() {

		OddEvenNaive oddEvenNaive = new OddEvenNaive(10);

		Thread thread1 = new Thread(oddEvenNaive::odd);

		Thread thread2 = new Thread(oddEvenNaive::even);

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
