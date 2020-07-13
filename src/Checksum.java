import javax.sound.sampled.AudioFormat;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Checksum {
    static byte shuffled_array[];

//https://en.wikipedia.org/wiki/Pearson_hashing


    static boolean has_even_parity(int x){
        int count = 0, b = 1;

        for(int i = 0; i < 32; i++){


            if((x & (b << i)) !=0 ){count++;}
        }

        if( count % 2 ==1  ){return false;}
        return true;
    }
    static void shuffle(byte[] array){
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            byte temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;

        }

    }
    public static byte hash(String input)
    {
        byte toRet = 0;
        byte[] bytes = input.getBytes();
        for (byte b: bytes)
        {
            toRet = (byte)(toRet ^ b);
        }

        return toRet;
    }
    public static byte pearson(String input) throws UnsupportedEncodingException {
        shuffled_array = new byte[128];
        for (int i=0;i<128;i++){
            shuffled_array[i] = (byte) Byte.toUnsignedInt((byte)i);
        }
        shuffle(shuffled_array);
        byte hash = (byte) (input.length() % 128);

        byte[] bytes = input.getBytes("ASCII");

        for (byte b: bytes)
        {
            hash = (byte)(shuffled_array[hash^b]);
        }

        return hash;
    }

}
