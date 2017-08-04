package org.ejfr.fintonic.fintonic.utils;


/**Class that manages the http connections of Marvel Superheroes
 * Created by EmilioJosé on 03/08/2017.
 */

public class CharacterHttpClient {
    /**String which contains the json url*/
    private static final String BASE_URL= "https://api.myjson.com/bins/bvyob";

    /**Method that returns the data read from the url CharacterHttpClient.BASE_URL
     *
     * @return String
     */
    public  String getCharactersData() {
        return HttpUtils.getDataFromURL(BASE_URL);
    }
}
