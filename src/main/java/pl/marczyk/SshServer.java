package pl.marczyk;

import org.apache.log4j.Logger;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by MMARCZYK on 2016-01-22.
 */
public class SshServer {
    private final Logger LOGGER = Logger.getLogger(SshServer.class);

    private final String host;
    private final int port;

    public SshServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        LOGGER.info("Initializing SSH server");
        org.apache.sshd.server.SshServer sshd = org.apache.sshd.server.SshServer.setUpDefaultServer();
        sshd.setPort(port);
        sshd.setHost(host);
        sshd.setShellFactory(new ProcessShellFactory(new String[]{"cmd"}));
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(new File("C:\\hostkey.ser")));
        sshd.setPasswordAuthenticator(new PasswordAuthenticator() {
            public boolean authenticate(String username, String password, ServerSession serverSession) {
                if ("admin".equals(username) && "qwerty".equals(password))
                    return true;
                return false;
            }
        });

        LOGGER.info("Starting SSH server...");
        sshd.start();

        if (sshd.isOpen()) {
            LOGGER.info("SSH server successfully started on ip address " + sshd.getHost() + ":" + sshd.getPort());
        } else {
            LOGGER.error("Couldn't start SSH server on ip address " + sshd.getHost() + ":" + sshd.getPort());
        }
    }
}
