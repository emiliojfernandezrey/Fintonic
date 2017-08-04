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

    private FrameLayout frameLayout2;
    private FragmentCharacter fragmentCharacter2;
    private static FragmentCharacter fragmentCharacter1;
    private FragmentTransaction transaction;
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

        fragmentManager=getFragmentManager();
        transaction=fragmentManager.beginTransaction();

        if(fragmentCharacter1==null){
            fragmentCharacter1 = new FragmentCharacter();
        }

        Bundle bundle;
        if(savedInstanceState==null) {
            bundle = new Bundle();
            fragmentCharacter1.setArguments(bundle);
        }

        transaction.replace(R.id.frame_layout_character1, fragmentCharacter1);

        if(frameLayout2 !=null){//landscape
            fragmentCharacter2 = new FragmentCharacter();
            transaction.add(R.id.frame_layout_character2, fragmentCharacter2);
        }

        transaction.commit();
    }
}
