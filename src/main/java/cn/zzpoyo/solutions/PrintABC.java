package cn.zzpoyo.solutions;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程循环打印N次ABC
 */
public class PrintABC {
    
    static class PrintThread extends Thread {
        
        volatile static Lock lock = new ReentrantLock();
        
        //打印次数
        static volatile int times = 3;
        //当前状态
        static volatile int state = 0;
        
        int letter;
        
        PrintThread(int letter) {
            this.letter = letter;
        }
        
        @Override
        public void run() {
            int i = 0;
            while (i < times) {
                if (state % 3 == letter) {
                    try {
                        lock.lock();
                        print();
                        i++;
                        state++;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        
        void print() {
            System.out.print((char) ('A' + letter));
        }
    }
    
    
    public static void main(String[] args) {
        PrintThread.times = 10;
        new PrintThread(0).start();
        new PrintThread(1).start();
        new PrintThread(2).start();
    }
    
    
}
