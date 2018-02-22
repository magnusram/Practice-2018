package my.samples;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadCommunicationQ {
	Queue<Integer> bq = new LinkedBlockingQueue<>();

	public static void main(String[] args) {
		ThreadCommunicationQ q = new ThreadCommunicationQ();
		q.test();
	}

	public void test() {
		new Thread(new Even()).start();
		new Thread(new Odd()).start();
	}

	class Even implements Runnable {
		public void run() {
			for (int i = 0; i < 11; i = i + 2) {
				bq.offer(i);
			}
		}
	}

	class Odd implements Runnable {
		public void run() {
			while (true) {
				int element = bq.poll();
				System.out.println(element);
				System.out.println(element + 1);
				if (element == 10) {
					break;
				}
			}
		}
	}

}
