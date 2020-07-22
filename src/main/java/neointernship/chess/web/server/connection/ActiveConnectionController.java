package neointernship.chess.web.server.connection;

public class ActiveConnectionController {
    private final UserConnection firstUserConnection;
    private final UserConnection secondUserConnection;

    private UserConnection currentConnection;

    public ActiveConnectionController(final UserConnection firstUserConnection, final UserConnection secondUserConnection) {
        this.firstUserConnection = firstUserConnection;
        this.secondUserConnection = secondUserConnection;
    }

    public void update() {
        currentConnection = (currentConnection != firstUserConnection) ? firstUserConnection : secondUserConnection;
    }

    public UserConnection getCurrentConnection() {
        return currentConnection;
    }
}
