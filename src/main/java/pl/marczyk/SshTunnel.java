package pl.marczyk;

import com.github.sheigutn.pushbullet.Pushbullet;
import com.github.sheigutn.pushbullet.items.device.Device;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.apache.log4j.Logger;

import javax.swing.*;

/**
 * Created by MMARCZYK on 2016-01-22.
 */
public class SshTunnel {
    private final Logger LOGGER = Logger.getLogger(SshServer.class);

    private final String remoteHost;
    private final int remotePort;
    private final String user;
    private final String password;
    private final String localHost;
    private final int localPort;

    public SshTunnel(String remoteHost, int remotePort, String user, String password, String localHost, int localPort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.user = user;
        this.password = password;
        this.localHost = localHost;
        this.localPort = localPort;
    }

    public void start() {
        LOGGER.info("Initializing SSH tunnel");
        try{
            JSch jsch=new JSch();

            Session session=jsch.getSession(user, remoteHost, 22);
            session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            LOGGER.info("Connecting to remote host " + remoteHost);
            session.connect();

            LOGGER.info("Forwarding port");
            session.setPortForwardingR(remotePort, localHost, localPort);

            LOGGER.info("Successfully forwarded: "+remoteHost+":"+remotePort+" -> "+localHost+":"+localPort);

            LOGGER.info("Sending push");
            Pushbullet pushbullet = new Pushbullet("o.s3C24IGgEg8ceGdLGOfvgqkFxIW5rCJ4");
            Device nexus = pushbullet.getDevice("LGE Nexus 5");
            nexus.pushNote("BD", "New server connected to port " + remotePort);
        } catch(Exception e){
            LOGGER.error("Couldn't forward port: " + e.getMessage());
        }
    }
}
