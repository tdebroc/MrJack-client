import com.fasterxml.jackson.databind.JsonNode;
import grooptown.ia.PlayerConnector;

import static grooptown.ia.SSLUtil.disableSSLValidation;

/**
 * Launches a Random game between somes players.
 * Created by thibautdebroca on 09/01/2019.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // With JDK inferior to 8u101 you need to disable SSL validation.
        disableSSLValidation();
        // PlayerConnector.baseUrl = "https://domi-nation.grooptown.com";

        System.out.println("Creating new Game");
        String gameUuid = PlayerConnector.createNewGame();
        System.out.println("Game Id is " + gameUuid);
        PlayerConnector playerConnector = new PlayerConnector(gameUuid);
        playerConnector.joinGame(gameUuid, true);
        JsonNode game = PlayerConnector.getGameState(gameUuid);
        System.out.println(game);

    }


}
