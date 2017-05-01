package ocp.C8;

import java.io.*;

/**
 * Created by barry on 01/05/2017.
 */
public class Bird implements Serializable{
    private static final String BIRDS_FILE_PATH = "Birds.txt";

    public static int COUNT = 0;

    {
        COUNT++;
    }

    transient String name = "Bridget";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Bird() {
        this.name = "Matt";
    }

    Bird(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                '}';
    }

    static class Eagle extends Bird implements Serializable {
        public static final long serialVersionUID = 1L;

        {
            this.name = "Janette";
        }

        Eagle() {
            super("Barry");
        }

        Eagle(String s) {
            super(s);
            this.name = "Daniel";
        }
    }

    public static void main(String[] args) throws IOException {
        File birdFile = new File(BIRDS_FILE_PATH);

        try (FileOutputStream fos = new FileOutputStream(BIRDS_FILE_PATH, true);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            Eagle eagleOut = new Eagle();
            oos.writeObject(eagleOut);
        }

        try (FileInputStream fin = new FileInputStream(birdFile);
             BufferedInputStream bis = new BufferedInputStream(fin);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            while (true) {
                Eagle eagleIn = (Eagle) ois.readObject();
                System.out.println("eagleIn = " + eagleIn);
                System.out.println("Eagle.COUNT = " + Eagle.COUNT);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

