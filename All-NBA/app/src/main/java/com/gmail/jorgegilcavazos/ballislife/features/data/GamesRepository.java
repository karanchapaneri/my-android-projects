package com.gmail.jorgegilcavazos.ballislife.features.data;

import java.util.Date;
import java.util.List;

/**
 * Defines an interface to access game data.
 */
public interface GamesRepository {

    interface LoadGamesCallback {
        void onGamesLoaded(List<NbaGame> games);

        void onFailure(String error);
    }

    void getGames(Date date, LoadGamesCallback callback);
}
