package org.ejfr.fintonic.fintonic.model;

import android.util.Log;
import org.ejfr.fintonic.fintonic.utils.HttpUtils;
import org.ejfr.fintonic.fintonic.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by EmilioJosé on 04/08/2017.
 */

public class SuperheroesWrapper {

    public static MarvelCharacter fromJson(String s, String characterName){

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray(Utils.KEY_SUPERHEROES);

            for(int i=0;i<jsonArray.length();i++){
                String name = jsonArray.getJSONObject(i).get(Utils.KEY_CHARACTER_NAME).toString();
                Log.d("SuperheroesWrapper",name);
                if(name.equalsIgnoreCase(characterName)){
                    String photo = jsonArray.getJSONObject(i).get(Utils.KEY_PHOTO).toString();
                    String realName= jsonArray.getJSONObject(i).get(Utils.KEY_REALNAME).toString();
                    String height =jsonArray.getJSONObject(i).get(Utils.KEY_HEIGHT).toString();
                    String power= jsonArray.getJSONObject(i).get(Utils.KEY_POWER).toString();
                    String abilities = jsonArray.getJSONObject(i).get(Utils.KEY_ABILITIES).toString();
                    String groups =jsonArray.getJSONObject(i).get(Utils.KEY_GROUPS).toString();

                    //image
                    byte[] photoByte = HttpUtils.getImageFromUrl(photo);
                    return new MarvelCharacter(name, photoByte,realName,height,power,abilities,groups);
                }
            }
        }catch (Exception e){
            Log.d("SuperheroesWrapper",""+e.toString());
        }
        return null;
    }

    public static ArrayList<String> fromJSon(String s){
        ArrayList<String> names = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray(Utils.KEY_SUPERHEROES);

            for(int i=0;i<jsonArray.length();i++){
                String name = jsonArray.getJSONObject(i).get(Utils.KEY_CHARACTER_NAME).toString();
                names.add(name);
            }
        }catch (Exception e){
            Log.d("SuperheroesWrapper",""+e.toString());
        }

        return names;
    }
}
