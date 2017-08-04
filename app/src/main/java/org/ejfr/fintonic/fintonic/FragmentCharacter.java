package org.ejfr.fintonic.fintonic;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.ejfr.fintonic.fintonic.model.MarvelCharacter;
import org.ejfr.fintonic.fintonic.utils.JsonCharacterNamesTask;
import org.ejfr.fintonic.fintonic.utils.JsonCharacterTask;
import org.ejfr.fintonic.fintonic.utils.Utils;

import java.util.ArrayList;

/**Class which extends Fragment. Used to show the information of a Marvel Character.
 * Created by EmilioJosé on 03/08/2017.
 */

public class FragmentCharacter extends Fragment {

    private View myView;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private FragmentCharacter fragmentCharacter=this;
    private TextView textViewName;
    private ImageView imageViewPhoto;
    private TextView textViewRealName;
    private TextView textViewHeight;
    private TextView textViewPower;
    private TextView textViewAbilities;
    private TextView textViewGroups;
    private String characterName;
    private ArrayList<String> characterNamesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_character, container, false);

        //InitAdapter asyntask to get the chracter names shown in the spinner
        JsonCharacterNamesTask namesTask = new JsonCharacterNamesTask();
        namesTask.setFragmentCharacter(fragmentCharacter);
        namesTask.execute();

        this.updateBundle();

        return myView;
    }


    /**Method that updates the spinner selection given by the bungle characterName saved
     *
     */
    private void updateBundle(){
        Bundle bundle = getArguments();
        if(bundle!=null && bundle.getString(Utils.KEY_CHARACTER_NAME)!=null && characterNamesList !=null && characterNamesList.size()>0) {
            spinner.setSelection(Utils.getCharacterNamePosition(characterNamesList, characterName));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(characterName==null || characterName.trim().equals("")){
            return;
        }

        Bundle bundle = getArguments();
        if (getArguments() == null) {
            bundle=new Bundle();
        }
        bundle.putString(Utils.KEY_CHARACTER_NAME, characterName);
    }

    /**Method that updates the MarvelCharacter fields shown in the fragment
     *
     * @param character MarvelCharacter
     */
    public void updateValues(MarvelCharacter character){
        this.textViewName = (TextView) this.myView.findViewById(R.id.textViewName);
        this.imageViewPhoto = (ImageView) this.myView.findViewById(R.id.imageViewPhoto);
        this.textViewRealName = (TextView) this.myView.findViewById(R.id.textViewRealName);
        this.textViewHeight = (TextView) this.myView.findViewById(R.id.textViewHeight);
        this.textViewPower = (TextView) this.myView.findViewById(R.id.textViewPower);
        this.textViewAbilities = (TextView) this.myView.findViewById(R.id.textViewAbilities);
        this.textViewGroups = (TextView) this.myView.findViewById(R.id.textViewGroups);

        this.textViewName.setText(character.getName());
        Bitmap img = BitmapFactory.decodeByteArray(character.getPhoto(),0,character.getPhoto().length);
        this.imageViewPhoto.setImageBitmap(img);
        this.textViewRealName.setText(character.getRealName());
        this.textViewHeight.setText(character.getHeight());
        this.textViewPower.setText(character.getPower());
        this.textViewAbilities.setText(character.getAbilities());
        this.textViewGroups.setText(character.getGroups());
    }

    /**Method that sets the adapter with the values given as argument
     *
     * @param names ArrayList - Values for the adapter
     */
    public void setAdapter(final ArrayList<String> names){
        spinner= (Spinner) myView.findViewById(R.id.spinnerFragment);
        characterNamesList =names;

        adapter = new ArrayAdapter<String> (myView.getContext(),R.layout.item_spinner,R.id.spinnerItemCharacter, names);
        if(adapter!=null) {
            spinner.setAdapter(adapter);
        }

        this.updateBundle();

        //Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                characterName = names.get(position);
                JsonCharacterTask task = new JsonCharacterTask();
                task.setFragmentCharacter(fragmentCharacter);
                task.execute(characterName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
