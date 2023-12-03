package Thread;

public class CountDownThread2 implements  Runnable{

    @Override
    public void run() {
        for (int i = 10 ; i > 0; i--) {
            System.out.println(i);
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Time up!");
    }

    public static void main(String[] args) {
        CountDownThread2 cdt2 = new CountDownThread2();
        Thread thread = new Thread(cdt2);
        thread.start();

        CountDownThread2 cdt22 = new CountDownThread2();
        Thread thread1 = new Thread(cdt22);
        thread1.start();
    }
}
