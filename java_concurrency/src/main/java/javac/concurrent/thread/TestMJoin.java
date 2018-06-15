package javac.concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TestMJoin {

    public void buildTasks() {
        Thread task1 = new Thread() {
            public void run() {
                try {
                    log("task1 | " + this.getName() + " started");
                    TimeUnit.SECONDS.sleep(1);
                    log("task1 | " + this.getName() + " ended");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread task2 = new Thread() {
            public void run() {
                try {
                    log("task2 | " + this.getName() + " started");
                    task1.interrupt();
                    TimeUnit.SECONDS.sleep(2);
                    log("task2 | " + this.getName() + " ended");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread task3 = new Thread() {
            public void run() {
                try {
                    log("task3 | " + this.getName() + " started");
                    TimeUnit.SECONDS.sleep(3);
                    log("task3 | " + this.getName() + " ended");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            task1.start();

            task2.start();
            // task2.join();

            // task3.start();
            // task3.join();

            // task1.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void log(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(msg);
        sb.append(" | ");
        Calendar time = new GregorianCalendar();
        time.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        sb.append(dateformat.format(time.getTime()));
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        TestMJoin join = new TestMJoin();
        join.buildTasks();
    }

}
