package org.ejfr.fintonic.fintonic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import org.ejfr.fintonic.fintonic.utils.Utils;

public class MainActivity extends AppCompatActivity {

    /**Attribute FrameLayout it contains the second fragment in case of landscape*/
    private FrameLayout frameLayout2;
    /**Attribute which contains the object of the landscape fragment/character*/
    private FragmentCharacter fragmentCharacter2;
    /**Attribute which contains the object of the portrait fragment/character*/
    private static FragmentCharacter fragmentCharacter1;//defined as static to fix the data in case change portrait to landscape
    /**Attribute required to manage the fragment*/
    private FragmentTransaction transaction;
    /**Attribute required to manage the fragment*/
    private FragmentManager fragmentManager;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setActivity(this);

        frameLayout2 = (FrameLayout) findViewById(R.id.frame_layout_character2);

        exitButton = (Button) findViewById(R.id.exit_button);
        if(this.exitButton!=null && !this.exitButton.hasOnClickListeners()) {

            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        //Begin transaction to manage the fragments
        fragmentManager=getFragmentManager();
        transaction=fragmentManager.beginTransaction();

        if(fragmentCharacter1==null){//Only first time
            fragmentCharacter1 = new FragmentCharacter();
        }

        Bundle bundle;
        if(savedInstanceState==null) {
            bundle = new Bundle();
            fragmentCharacter1.setArguments(bundle);
        }

        //In case of fragment1 I replace the existing one
        transaction.replace(R.id.frame_layout_character1, fragmentCharacter1);

        if(frameLayout2 !=null){//landscape, add the fragment2
            fragmentCharacter2 = new FragmentCharacter();
            transaction.add(R.id.frame_layout_character2, fragmentCharacter2);
        }

        transaction.commit();
    }
}
