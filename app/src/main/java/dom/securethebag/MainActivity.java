package dom.securethebag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.github.abdularis.civ.AvatarImageView;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.topbar);
        this.setSupportActionBar(toolbar);
        defaultFragment();

        //Onchange listner
        final BottomNavigationView bottom = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom.setSelectedItemId(R.id.action_me);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                if (item.getItemId() == R.id.action_me){
                    fragmentChange(new DashboardFragment());
                }else if (item.getItemId() == R.id.action_groups){
                    fragmentChange(new GroupsFragment());
                }else if(item.getItemId() == R.id.action_settings){
                    fragmentChange(new SettingsFragment());
                }
                return false;
            }
        });
    }

    protected void onAccountClick(View view){
        AvatarImageView a = (AvatarImageView) view;
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    private void defaultFragment(){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DashboardFragment dash = new DashboardFragment();
        fragmentTransaction.replace(R.id.placeholder, dash);
        currentFragment = dash;

        fragmentTransaction.commit();
    }

    private void fragmentChange(Fragment dest){
        if ((dest instanceof DashboardFragment) && (currentFragment instanceof DashboardFragment)){}
        else if((dest instanceof GroupsFragment) && (currentFragment instanceof GroupsFragment)){}
        else if((dest instanceof SettingsFragment) && (currentFragment instanceof SettingsFragment)){}
        else{
            transitionFragment(dest);
        }
    }
    private void transitionFragment(Fragment dest){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Replace the layout holder with the required Fragment object.
        Fade exitFade = new Fade();
        exitFade.setDuration(500);
        currentFragment.setExitTransition(exitFade);
        // 3. Enter Transition for New Fragment
        Slide enterSlide = new Slide();
        enterSlide.setSlideEdge(Gravity.START);
        enterSlide.setStartDelay(250);
        enterSlide.setDuration(250);
        dest.setEnterTransition(enterSlide);

        fragmentTransaction.replace(R.id.placeholder, dest);
        currentFragment = dest;

        fragmentTransaction.commit();
    }
}