package org.ejfr.fintonic.fintonic.utils;

import android.os.AsyncTask;
import android.util.Log;
import org.ejfr.fintonic.fintonic.FragmentCharacter;
import org.ejfr.fintonic.fintonic.model.MarvelCharacter;
import org.ejfr.fintonic.fintonic.model.SuperheroesWrapper;

/**
 * Created by EmilioJosé on 03/08/2017.
 */

public class JsonCharacterTask extends AsyncTask<String, Void, MarvelCharacter> {

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
            character = SuperheroesWrapper.fromJson(data, params[0]);
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
