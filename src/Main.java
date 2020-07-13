import java.io.UnsupportedEncodingException;

public class Main {
    public static boolean isLineBusy = false;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("********\tCSMA with exponential backoff\t********");
        System.out.println("Starting simulation for 10 seconds...");
        Thread.sleep(1000);
        Station s1 = new Station(System.currentTimeMillis(),1);
        Station s2 = new Station(System.currentTimeMillis(),2);
        Station s3 = new Station(System.currentTimeMillis(),3);
        for(int i=0; i<10 ; i++){
            Thread.sleep(1000);
        }
        System.out.println("Stopping stations...");
        s1.flag = false;
        s2.flag = false;
        s3.flag = false;
        Thread.sleep(1000);
        System.out.println("Station 1 sent: "+ s1.packets_sent + " packets");
        System.out.println("Station 2 sent: "+ s2.packets_sent + " packets");
        System.out.println("Station 3 sent: "+ s3.packets_sent + " packets");
        for(int i=0; i<5;i++){
            System.out.println();
        }
        System.out.println("********\tPearson hash\t********");
        byte b=0,c = 0,d=0;
        try {
            b = Checksum.pearson("hello");
            c = Checksum.pearson("olleh");
            d = Checksum.pearson("eello");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(" hello hash: "+ b);
        System.out.println("olleh hash: "+ c);
        System.out.println("eello hash: "+ d);
        for(int i=0; i<5;i++){
            System.out.println();
        }
        System.out.println("********\tParity check\t********");
        for(int i=0; i<10; i++){
            System.out.println(Integer.toBinaryString(i)+" has even 1's? "+ Checksum.has_even_parity(i));
        }


    }
}
