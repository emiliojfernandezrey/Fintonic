package org.ejfr.fintonic.fintonic.utils;

import android.app.Activity;
import java.util.ArrayList;

/**
 * Created by EmilioJosé on 03/08/2017.
 */

public class Utils {

    //private static ArrayList<String> charactersList = new ArrayList<String>(Arrays.asList("Spiderman", "Captain Marvel", "Hulk", "Thor", "Iron Man","Captain America"));
    private static Activity myActivity;
    public static final String KEY_CHARACTER_NAME= "name";
    public static  final String KEY_PHOTO="photo";
    public static final String KEY_REALNAME="realName";
    public static final String KEY_HEIGHT="height";
    public static final String KEY_POWER="power";
    public static final String KEY_ABILITIES="abilities";
    public static final String KEY_GROUPS="groups";
    public static final String KEY_SUPERHEROES="superheroes";


    public static void setActivity(Activity activity){
        myActivity =activity;
    }

    /*
    public static ArrayList<String> getCharactersList(){
        Collections.sort(charactersList);
        return charactersList;
    }*/

    public static int getCharacterNamePosition(ArrayList<String> names, String characterName){
        for(int i=0;i<names.size();i++){
            if(names.get(i).equalsIgnoreCase(characterName)){
                return i;
            }
        }
        return 0;
    }
}
