package ocp.C8;

import java.io.*;
import java.util.stream.Stream;

/**
 * Created by barry on 29/04/2017.
 */
public class MainC8 {

    //    private static final String ZOO_TXT = "src/main/java/ocp/C8/zoo.txt";
    private static final String ZOO_TXT = "zoo.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        q1In();

        InputStream is =
                new BufferedInputStream(
                        new FileInputStream(ZOO_TXT));
        InputStream wrapper = new BufferedInputStream(is);
        InputStream wrapper2 = new BufferedInputStream(wrapper);

        ObjectInputStream ois = new ObjectInputStream(is);
        Object o = ois.readObject();
        Object o2 = ois.readObject();

        System.out.println(o);
        System.out.println("o2 = " + o2);
        System.out.println();

//---------------------------

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(ZOO_TXT));

        System.out.println();
//---------------------------

        Console console = System.console();
        if (console != null) {
            char[] myPasswordChars =
                    console.readPassword("Gimme your password...");
            console.writer().append("... myPasswordChars = ");
            console.writer().write(myPasswordChars);
            console.writer().write("\n");

            String myReadInString = console.readLine("Gimme your text...");
            console.writer().append("||| myReadInString = ")
                    .append(myReadInString)
                    .append("\n")
                    .flush();

            console.printf("hello 1");
            console.format("hello 2");
        }

        System.out.println();
//---------------------------
    }

    private static void q1In() throws IOException {
        long start = System.currentTimeMillis();

        File zooFile = new File(ZOO_TXT);

        ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(zooFile))
        );

        Stream.iterate("\nHello World!", s -> s.concat("x"))
                .limit(1_000)
                .forEach(s -> {
                    try {
                        oos.writeObject(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        oos.flush();

        long end = System.currentTimeMillis();

        System.out.println("time = " + (end - start));
    }
}
