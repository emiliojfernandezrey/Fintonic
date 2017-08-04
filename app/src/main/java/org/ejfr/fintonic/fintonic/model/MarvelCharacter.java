package org.ejfr.fintonic.fintonic.model;

import android.util.Log;

/** Class that contains the attributes for a marvel character
 *
 * Created by EmilioJosé on 03/08/2017.
 */

public class MarvelCharacter {

    private String name;
    private byte[] photo;
    private String realName;
    private String height;
    private String power;
    private String abilities;
    private String groups;

    /**Constructor:
     * Empty constructor, created in case of using Gson
     *
     */
    public MarvelCharacter(){
        Log.d("MarvelCharacter"," Default Constructor");
    }

    /**Constructor for testing
     *
     * @param name String
     */
    public MarvelCharacter(String name){
        this(name,null,null,null,null,null,null);
    }

    /**Constructor that creates a Marvel character
     *
     * @param name String
     * @param photo byte[]
     * @param realName String
     * @param height String
     * @param power String
     * @param abilities String
     * @param groups String
     */
    public MarvelCharacter(String name, byte[] photo, String realName, String height, String power, String abilities, String groups) {
        this.name = name;
        this.photo = photo;
        this.realName = realName;
        this.height = height;
        this.power = power;
        this.abilities = abilities;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getRealName() {
        return realName;
    }

    public String getHeight() {
        return height;
    }

    public String getPower() {
        return power;
    }

    public String getAbilities() {
        return abilities;
    }

    public String getGroups() {
        return groups;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Setter methods has been created in case of using Gson

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
