import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhipeng
 * @date 2019-02-01
 */
public class TestVolatile {
    static class A {
        
        public static B b = new B();
        private static Map<String, B> map = new HashMap<>();
        
        static {
            map.put("1", b);
        }
        
        private void write() {
            b.setX(1);
            System.out.println("update end");
        }
        
        private void read() {
            while (map.get("1").getX() != 1) {
            }
            System.out.println("read end");
        }
    }
    
    static class B {
        private int x = 0;
        public static int y = 0;
        
        
        public int getX() {
            return x;
        }
        
        public void setX(int x) {
            this.x = x;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
//        Thread writeThread = new Thread(() -> B.y = 1);
//        Thread readThread = new Thread(() ->B.y = 2);
//        Thread thread3 = new Thread(() -> {
//            while (B.y == 0) {
//            }
//            System.out.println("thread 3 end");
//        
//        });
//        Thread thread4 = new Thread(() -> {
//            while (B.y == 0) {
//            }
//            System.out.println("thread 4 end");
//        
//        });
        
        Thread writeThread = new Thread(() -> a.write());
        Thread readThread = new Thread(() -> a.read());
        
        readThread.start();
        Thread.sleep(1000);
        writeThread.start();
        
    }
}
