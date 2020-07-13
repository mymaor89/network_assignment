import java.util.Random;

public class Station implements Runnable
{
    static final long MAX_WAIT = 100;
    Thread t;
    boolean flag;
    long start;
    int station_number;
    int n, packets_sent;
    public Station(long start,int station_number) {
        t = new Thread(this, "my runnable thread");
        System.out.println("Station created: "+ t);
        flag = true;
        this.start = start;
        this.station_number = station_number;
        t.start();
    }

    @Override
    public void run() {

        while(flag){
            try {
                if (!Main.isLineBusy){
                    Main.isLineBusy = true;
                    System.out.println(this.station_number +" Sending at: "+ ((double)(System.currentTimeMillis() - start)/1000));
                    packets_sent++;
                    Main.isLineBusy = false;
                    Thread.sleep(10);
                }else{
                    exponential_backoff();
                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

    }

    private void exponential_backoff() throws InterruptedException {
        int random_milli = new Random().nextInt(100);
        Thread.sleep(Math.min((long) (Math.pow(2,n)+ random_milli),MAX_WAIT));
        n+=1;
    }
}
