package my.samples;

public class EvenOddThreads {

	private final Object lock = new Object();
	private volatile boolean oddTurn = false;
	private volatile boolean evenTurn = true;

	public static void main(String[] args) {
		EvenOddThreads eot = new EvenOddThreads();
		eot.test();
	}

	public void test() {
		new Thread(new Odd()).start();
		new Thread(new Even()).start();		
	}

	class Even implements Runnable {
		public void run() {
			for (int i = 0; i < 10; i = i + 2) {
				synchronized (lock) {
					if (evenTurn) {
						oddTurn = true;
						evenTurn = false;
						System.out.println(i);
						try {
							lock.notify();
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						try {
							lock.notify();
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}
	}

	class Odd implements Runnable {
		public void run() {
			for (int i = 1; i < 10; i = i + 2) {
				if (i == 1 && evenTurn) {
					i = -1;
					continue;
				}
				synchronized (lock) {
					if (oddTurn) {
						evenTurn = true;
						oddTurn = false;
						System.out.println(i);
						
						try {
							lock.notify();
							if(i != 9) //Don't wait after the last iteration
								lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						try {
							lock.notify();
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}
	}

}
