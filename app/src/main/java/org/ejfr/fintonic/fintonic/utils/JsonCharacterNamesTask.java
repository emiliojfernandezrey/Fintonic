package org.ejfr.fintonic.fintonic.utils;

import android.os.AsyncTask;
import android.util.Log;
import org.ejfr.fintonic.fintonic.FragmentCharacter;
import org.ejfr.fintonic.fintonic.model.SuperheroesWrapper;
import java.util.ArrayList;

/**Class AsynTask created to manage the http request of characterNames.
 * Created by EmilioJosé on 04/08/2017.
 */

public class JsonCharacterNamesTask extends AsyncTask<Void, Void, ArrayList<String>> {

    /**Attribute required to update the names of the adapter/spinner defined in the fragment*/
    private FragmentCharacter fragmentCharacter;

    public void setFragmentCharacter(FragmentCharacter fragmentCharacter) {
        this.fragmentCharacter = fragmentCharacter;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        ArrayList<String> characterNames = null;
        CharacterHttpClient characterHttpClient=new CharacterHttpClient();
        String data = characterHttpClient.getCharactersData();
        try{
            characterNames = SuperheroesWrapper.getNamesfromJSon(data);
        }catch(Exception e){
            Log.d("Exception",e.toString());
        }
        return characterNames;
    }

    @Override
    protected void onPostExecute(ArrayList<String> names) {
        super.onPostExecute(names);
        fragmentCharacter.setAdapter(names);
    }
}
