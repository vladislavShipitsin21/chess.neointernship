package neointernship.web.server.connection;

import java.util.ArrayList;

public class ActiveConnectionController {
    private final UserConnection firstUserConnection;
    private final UserConnection secondUserConnection;

    private final ArrayList<UserConnection> userList;

    private UserConnection currentConnection;

    public ActiveConnectionController(final UserConnection firstUserConnection, final UserConnection secondUserConnection) {
        this.firstUserConnection = firstUserConnection;
        this.secondUserConnection = secondUserConnection;

        userList = new ArrayList<UserConnection>() {{
            add(firstUserConnection);
            add(secondUserConnection);
        }};
    }

    public void update() {
        currentConnection = (currentConnection != firstUserConnection) ? firstUserConnection : secondUserConnection;
    }

    public UserConnection getCurrentConnection() {
        return currentConnection;
    }

    public ArrayList<UserConnection> getConnections() {
        return userList;
    }
}
