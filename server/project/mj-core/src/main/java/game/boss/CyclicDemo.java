package game.boss;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zuoge85@gmail.com on 2017/1/3.
 */
class CyclicDemo {


    public static void main(String[] args) {
        final int N = 10;

        //屏障点 辅助类
        final CyclicBarrier barrier = new CyclicBarrier(N,
                new Runnable() {
                    public void run() {
                        System.out.println("全部到达屏障点");
                    }
                });

        Thread[] threads  = new Thread[N];
        for (int i = 0; i < N; ++i) {
            int finalI = i;
            threads[i]= new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.printf("等待全部到达 屏障点 i:%s, NumberWaiting:%s\n", finalI, barrier.getNumberWaiting());
                        barrier.await();//屏障点
                        System.out.printf("屏障点到达后 i:%s, NumberWaiting:%s\n", finalI, barrier.getNumberWaiting());
                    } catch (InterruptedException ex) {
                    } catch (BrokenBarrierException ex) {
                    }
                }
            });
            threads[i].start();
        }

        //等等全部线程执行完毕
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("结束");
    }
}
