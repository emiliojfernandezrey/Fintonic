package org.ejfr.fintonic.fintonic.utils;

import android.os.AsyncTask;
import android.util.Log;
import org.ejfr.fintonic.fintonic.FragmentCharacter;
import org.ejfr.fintonic.fintonic.model.MarvelCharacter;
import org.ejfr.fintonic.fintonic.model.SuperheroesWrapper;

/**Class AsyncTask created to manages the http request which reads the MarvelCharacter object
 * Created by EmilioJosé on 03/08/2017.
 */

public class JsonCharacterTask extends AsyncTask<String, Void, MarvelCharacter> {

    /**Attribute required to display the character fields shown in the fragment*/
    private FragmentCharacter fragmentCharacter;

    public void setFragmentCharacter(FragmentCharacter fragmentCharacter){
        this.fragmentCharacter = fragmentCharacter;
    }

    @Override
    protected MarvelCharacter doInBackground(String... params) {
        MarvelCharacter character = null;
        CharacterHttpClient characterHttpClient=new CharacterHttpClient();
        String data = characterHttpClient.getCharactersData();
        try{
            character = SuperheroesWrapper.getMarvelCharacterfromJson(data, params[0]);
        }catch(Exception e){
            Log.d("Exception",e.toString());
        }
        return character;
    }

    @Override
    protected void onPostExecute(MarvelCharacter character) {
        super.onPostExecute(character);
        fragmentCharacter.updateValues(character);
    }
}
