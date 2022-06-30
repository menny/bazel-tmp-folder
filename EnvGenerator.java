package testing;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EnvGenerator {
    public static void main(String[] args) throws Exception {

        List<String> env = new ArrayList<>();
        System.getenv().forEach((key, value) -> {
            System.out.printf(Locale.ROOT, "%s -> %s%n", key, value);
        });

        Path tempFile = Files.createTempFile("testing", "tmp");
        System.out.printf(Locale.ROOT, "createTempFile %s%n", tempFile.toAbsolutePath());
    }
}