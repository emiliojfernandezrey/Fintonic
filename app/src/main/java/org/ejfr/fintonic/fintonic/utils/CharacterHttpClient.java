package org.ejfr.fintonic.fintonic.utils;


/**
 * Created by EmilioJosé on 03/08/2017.
 */

public class CharacterHttpClient {
    private static final String BASE_URL= "https://api.myjson.com/bins/bvyob";

    public  String getCharactersData() {
        return HttpUtils.getDataFromURL(BASE_URL);
    }
}
