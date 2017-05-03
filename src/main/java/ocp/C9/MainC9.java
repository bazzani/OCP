package ocp.C9;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * Created by barry on 01/05/2017.
 */
public class MainC9 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/sockets.log");
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        BasicFileAttributeView attributesView = Files.getFileAttributeView(path, BasicFileAttributeView.class);

        PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);
        PosixFileAttributeView posixFileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class);

        if (attributes.size() >= 0 && attributes.creationTime().toMillis() > 0) {
            System.out.println("attributes = " + attributes);
            System.out.println("attributesView.name() = " + attributesView.name());
            System.out.println();
            System.out.println("pAttributes = " + posixFileAttributes);
            System.out.println("posixFileAttributeView.name() = " + posixFileAttributeView.name());
        }
        System.out.println();
//---------------------------

        Path pathSub = Paths.get("/zoo/animals/bear/koala/food.txt");
        System.out.println(pathSub.subpath(1, 3).getName(1).toAbsolutePath());
        System.out.println();
//---------------------------

        Path path1 = Paths.get("./");
//        Files.walk(path1)
//                .forEach(System.out::println);

        System.out.println();
//---------------------------

        Path turkeysPath = Paths.get("turkey");
        try {
            if (Files.isSameFile(turkeysPath, Paths.get("/Users/barry/dev/projects/java/ocp/turkey"))) {
                Files.createDirectory(path.resolve("info"));
            }
        } catch (NoSuchFileException isSameFileException) {
            System.out.println("isSameFileException = " + isSameFileException);
        }
        System.out.println();
//---------------------------

//        Files.move(Paths.get("a"),
//                Paths.get("b"),
//                StandardCopyOption.ATOMIC_MOVE,
//                LinkOption.NOFOLLOW_LINKS,
//                StandardCopyOption.REPLACE_EXISTING);

        UserPrincipalLookupService userPrincipalLookupService =
                FileSystems.getDefault().getUserPrincipalLookupService();

        UserPrincipal barry =
                userPrincipalLookupService.lookupPrincipalByName("barry");

        System.out.println();
        //--------------------------

        Path startPath = Paths.get("/users");
        Files.find(startPath, 0,
                (p, bfa) -> bfa.isDirectory())
                .map(Path::toString)
                .collect(Collectors.toList())
                .stream()
                .filter(x -> x.endsWith(".png") || x.endsWith(".jpg"))
                .forEach(System.out::println);

        System.out.println("---");
//---------------------------

        File rootFile = new File("/");

        int maxDepth = 0;
        BiPredicate<Path, BasicFileAttributes> matcher =
                (path2, basicFileAttributes) -> true;

        Files.list(startPath);
        Files.walk(startPath);
        Files.walk(startPath, maxDepth);
        Files.find(startPath, maxDepth, matcher);
        Files.lines(startPath);

//      -------
        int beginIndex = 1;
        int endIndex = 3;
        Path rootPath = Paths.get("/");
        rootPath.normalize();
        rootPath.relativize(startPath);
        if (rootPath.getNameCount() > 0)
            rootPath.subpath(beginIndex, endIndex);
        rootPath.toAbsolutePath();

        System.out.println();
//---------------------------

        final Path dotPath = Paths.get(".").normalize();
        int count = 0;
        for (int i = 0; i < dotPath.getNameCount(); i++) {
            count++;
        }
        System.out.println("count = " + count);
    }
}