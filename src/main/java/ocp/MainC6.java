package ocp;

/**
 * Created by barry on 27/04/2017.
 */
public class MainC6 {
//    static class SneezeException extends Exception {
//    }
//
//    static class SniffleException extends SneezeException {
//    }
//
//    static class WithClosable implements Closeable {
//        @Override
//        public void close() throws Exception {
//
//        }
//    }
//
//    static class WithAutoClosable implements AutoCloseable {
//        @Override
//        public void close() throws IllegalStateException {
//
//        }
//    }
//
//    public static void main(String[] args) throws IOException,
//            SneezeException, SQLException {
//
//        try {
//            throw new IOException();
//        } catch (IOException | RuntimeException e) {
//        }
//        MissingResourceException m;
//        IllegalStateException i;
//
//        System.out.println();
//        //---------------------------
//
//        main12();
//        main13();
//        main14();
//
//        WithClosable wc = new WithClosable();
//        WithAutoClosable wac = new WithAutoClosable();
//
//        try {
//            readFromDatabase();
//        } catch (SQLException | IOException e) {
//            throw e;
//        }
//    }
//
//    private static void readFromDatabase() throws SQLException {}
//
//    static void main12() throws SneezeException {
//        try {
//            throw new SneezeException();
//        } catch (SneezeException e) {
//            e = new SneezeException();
//            throw e;
//        }
//    }
//
//    static void main13() throws SneezeException {
//        try {
//            throw new SneezeException();
//        } catch (SneezeException | RuntimeException e) {
//            e = new SneezeException();
//            throw e;
//        }
//    }
//
//    static void main14() throws SneezeException {
//        try {
//            throw new SneezeException();
//        } catch (SneezeException | SniffleException e) {
//            throw e;
//        }
//    }
}
