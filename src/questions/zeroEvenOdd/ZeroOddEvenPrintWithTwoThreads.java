package questions.zeroEvenOdd;

import java.util.concurrent.atomic.AtomicInteger;

class ZeroEvenOddNaive {
	//this is the end number
	private final int num;

	// state to consider whose turn
	/**
	 * 0 -> ZERO_BEFORE_ODD
	 * 1 -> ODD
	 * 2 -> ZERO_BEFORE_EVEN
	 * 3 -> EVEN
	 */
	private AtomicInteger state = new AtomicInteger(0);

	public ZeroEvenOddNaive(int num) {
		this.num = num;
	}

	public void zero(Runnable printZero) {
		for (int i = 0; i < num; i++) {
//			 Until the state value is 0 or 2, this will loop constantly
			while (state.get() % 2 != 0) {
			}
			printZero.run();
//			Move to the ODD or EVEN state
			state.incrementAndGet();
		}
	}

	public void odd(Runnable printOdd) {
		for (int i = 1; i <= num; i += 2) {
			while (state.get() != 1) {
			}

			System.out.println(i);

			state.set(2);
		}
	}

	public void even(Runnable printEven) {
		for (int i = 2; i <= num; i += 2) {
			while (state.get() != 3) {
			}

			System.out.println(i);

			state.set(0);
		}
	}
}

public class ZeroOddEvenPrintWithTwoThreads {
	static void main() {

		ZeroEvenOddNaive zoo = new ZeroEvenOddNaive(10);

		Thread thread1 = new Thread(() -> {
			zoo.zero(() -> System.out.println(0));
		});

		Thread thread2 = new Thread(() -> {
			zoo.odd(() -> {
			});
		});

		Thread thread3 = new Thread(() -> {
			zoo.even(() -> {
			});
		});

		thread1.start();
		thread2.start();
		thread3.start();

		try {
			thread1.join();
			thread2.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
