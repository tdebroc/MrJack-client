package grooptown.ia.model;

public class PlayerRegistration {
    private String gameId;
    private String pseudo;
    private boolean isMrJack;

    public PlayerRegistration(String gameId, String pseudo, boolean isMrJack) {
        this.gameId = gameId;
        this.pseudo = pseudo;
        this.isMrJack = isMrJack;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public boolean isisMrJack() {
        return isMrJack;
    }

    public void setMrJack(boolean mrJack) {
        isMrJack = mrJack;
    }
}
