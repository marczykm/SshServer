package pl.marczyk.model;

/**
 * Created by MMARCZYK on 2016-01-26.
 */
public class Redirect {
    private final int remotePort;
    private final String host;
    private final int localPort;

    public Redirect(int remotePort, String host, int localPort) {
        this.remotePort = remotePort;
        this.host = host;
        this.localPort = localPort;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public String getHost() {
        return host;
    }

    public int getLocalPort() {
        return localPort;
    }

    @Override
    public String toString() {
        return "Redirect{" +
                "remotePort=" + remotePort +
                ", host='" + host + '\'' +
                ", localPort=" + localPort +
                '}';
    }
}
