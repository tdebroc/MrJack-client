package grooptown.ia.model;

public class MoveRequest {
    private String gameId;
    private String action;

    public MoveRequest(String gameId, String action) {
        this.gameId = gameId;
        this.action = action;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
