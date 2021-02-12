package grooptown.ia;

import com.fasterxml.jackson.databind.JsonNode;
import grooptown.ia.model.MoveRequest;
import grooptown.ia.model.PayerSecret;
import grooptown.ia.model.PlayerRegistration;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

@Data
public class PlayerConnector {

    private String gameId;

    public PayerSecret player;

    public static String baseUrl = "http://mrjack.grooptown.com";
    // public static String baseUrl = "http://127.0.0.1:9000";

    private static RestTemplate restTemplate = getRestTemplate();

    public PlayerConnector(String gameId) {
        this.gameId = gameId;
    }

    /**
     * Constructor for RestTemplate.
     * @return An instance of a RestTemplate.
     */
    private static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter
                .setSupportedMediaTypes(Arrays.asList(
                        MediaType.TEXT_PLAIN,
                        MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_OCTET_STREAM,
                        MediaType.ALL
                        ));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

    /**
     * Creates a Game on the server.
     * @return The uuid of the game created.
     */
    public static String createNewGame() {
        return restTemplate.postForObject(
                baseUrl + "/game",
                null, String.class
        ).replaceAll("\"", "");
    }

    /**
     * Make one player joining the game. PlayerConnector will store the secret uuid of the player.
     * @param gameId Id of the game.
     * @param isMrJack Whether we play as MrJack or not.
     */
    public void joinGame(String gameId,
                         boolean isMrJack) {
        PlayerRegistration playerRegistration = new PlayerRegistration(
                gameId,
                UUID.randomUUID().toString(),
                isMrJack
        );

        player = restTemplate.postForObject(
                baseUrl + "/register",
                playerRegistration, PayerSecret.class
        );
    }

    /**
     * Gets game state as a String.
     * @param gameId Id Of the game.
     * @return Game represented as a String.
     */
    public static String getGameStateAsString(String gameId) {
        return restTemplate.getForEntity(
                baseUrl + "/game/" + gameId,
                String.class
        ).getBody();
    }


    public static JsonNode getGameSecret(String gameId, String secretId) {
        return restTemplate.getForEntity(
                baseUrl + "/game/" + gameId + "/secret?secretId=" + secretId,
                JsonNode.class
        ).getBody();
    }


    /**
     * Gets Game State as a Java Object GameState.
     * @param gameId ID Of the game we want to get.
     * @return State of the game.
     */
    public static JsonNode getGameState(String gameId) {
        return restTemplate.getForEntity(
                baseUrl + "/game/" + gameId,
                JsonNode.class
        ).getBody();
    }


    /**
     * The players will play move number "moveNumber".
     * @param move Number of the Move.
     * @return State of the Game.
     */
    public JsonNode playMove(String move, String gameId) {
        System.out.println("Playing Move " + move + " for player " + player.getSecretId());
        MoveRequest moveRequest = new MoveRequest(gameId, move);
        return restTemplate.postForEntity(
                baseUrl + "/playAction?secretId=" + player.getSecretId(),
                moveRequest,
                JsonNode.class
        ).getBody();
    }


}
