package sg.nus.iss.visa.ssf.utility;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class Utility {
    public boolean isUniqueEmail(String email) {
        return false;
    }

    public static void createDir(String path) {
        File dir = new File(path);
        boolean isDirCreated = dir.mkdirs();
        System.out.println("dir created: " + isDirCreated);

        if (isDirCreated) {
            String osName = System.getProperty("os.name");

            System.out.println("OS name: " + osName);
            if (!osName.contains("Windows")) {
                try {
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
                }
                catch (IOException e) { 
                    e.getStackTrace();
                }
            }
        }
    }
}
