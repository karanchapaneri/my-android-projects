package com.gmail.jorgegilcavazos.ballislife.features.data;

import com.gmail.jorgegilcavazos.ballislife.network.GetRequestListener;
import com.gmail.jorgegilcavazos.ballislife.network.NetworkManager;
import com.gmail.jorgegilcavazos.ballislife.util.DateFormatUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the {@link GameServiceApi} that fetches data from a private API.
 */
public class GameServiceApiImpl implements GameServiceApi {
    private final static String BASE_URL = "http://phpstack-4722-10615-67130.cloudwaysapps.com/api/v1/";
    private final static String REQUEST_TAG = "FETCH_GAMES";

    @Override
    public void getAllGames(Date date, final GameServiceCallback<List<NbaGame>> callback) {
        String url = BASE_URL + "scoreboard.php" + "?date="
                + DateFormatUtil.getDashedDateString(date);

        NetworkManager.getInstance().makeGetRequest(url, REQUEST_TAG, new GetRequestListener() {
            @Override
            public void onResult(String result) {
                List<NbaGame> games = new ArrayList<>();
                games.addAll(getGamesListFromJson(result));
                callback.onLoaded(games);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

    private List<NbaGame> getGamesListFromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<List<NbaGame>>(){}.getType());
    }
}
