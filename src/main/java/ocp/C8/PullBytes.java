package ocp.C8;

import java.io.*;

/**
 * Created by barry on 01/05/2017.
 */
public class PullBytes {

    public static final String PULL_BYTES_DATA_FILE = "PullBytes.data";

    private static String pullBytes(InputStream is, int count) throws IOException {
        final StringBuilder sb = new StringBuilder();
        sb.append((char) is.read());
        is.mark(count);

        for (int i = 0; i < count; i++) {
            sb.append((char) is.read());
        }

        is.reset();

        long skip = is.skip(1);

        sb.append((char) is.read());
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (OutputStream os = new FileOutputStream(PULL_BYTES_DATA_FILE)) {
            os.write("XYZABC".getBytes());
        }


        try (FileInputStream fin = new FileInputStream(PULL_BYTES_DATA_FILE);
             BufferedInputStream is = new BufferedInputStream(fin)) {
            System.out.println("Bytes... " + pullBytes(is, 3));
        }
    }
}
