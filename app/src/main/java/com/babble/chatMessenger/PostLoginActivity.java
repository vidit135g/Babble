package com.babble.chatMessenger;

import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.babble.chatMessenger.fragments.ArchivedFragment;
import com.babble.chatMessenger.fragments.BabbleBombFragment;
import com.babble.chatMessenger.fragments.ChatFragment;
import com.babble.chatMessenger.fragments.FavoritesFragment;
import com.babble.chatMessenger.fragments.GroupChatFragment;
import com.babble.chatMessenger.fragments.HelpFragment;
import com.babble.chatMessenger.fragments.InviteFragment;
import com.babble.chatMessenger.fragments.SecretChatFragment;
import com.babble.chatMessenger.fragments.SettingsFragment;
import com.babble.chatMessenger.fragments.StoriesFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class PostLoginActivity extends AppCompatActivity {

    private static final int PROFILE_SETTING = 1;
    //drawer result
    //save our header or result
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.BLACK);
        getSupportActionBar().setTitle("Babble");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");
        //TODO Handle the AccountHeader setup
        AccountHeader headerResult=new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.coveraccount)
                .addProfiles(
                        new ProfileDrawerItem().withName("Vidit Gupta").withEmail("vidit135g@gmail.com").withIcon(getResources().getDrawable(R.drawable.avatar))
                ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();

        //TODO CREATE THE DRAWER
        result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withActivity(this)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Chat").withIcon(R.drawable.chaticon).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Create Group").withIcon(R.drawable.newgroupicon).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Create Babble Bomb").withIcon(R.drawable.speaker).withIdentifier(3),

                        //Secondary elements
                        new PrimaryDrawerItem().withName("Secret Chat").withIcon(R.drawable.secretchaticon).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Archived Messages").withIcon(R.drawable.archiveicon).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Favorites").withIcon(R.drawable.favoritesicon).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Invite").withIcon(R.drawable.inviteicon2).withIdentifier(7),
                        new PrimaryDrawerItem().withName("Settings").withIcon(R.drawable.settingsicon).withIdentifier(8),
                        new PrimaryDrawerItem().withName("FAQ").withIcon(R.drawable.faqicon).withIdentifier(9)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem.getIdentifier()==1) {

                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new ChatFragment()).commit();
                        }
                        else if(drawerItem.getIdentifier()==2){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new GroupChatFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==3){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new BabbleBombFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==4){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new SecretChatFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==5){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new ArchivedFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==6){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new FavoritesFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==7){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new InviteFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==8){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new SettingsFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==9){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().add(R.id.frame_container,new StoriesFragment()).commit();
                        }
                        else{
                            Toast.makeText(PostLoginActivity.this," " , Toast.LENGTH_SHORT).show();
                        }
                        /*if(drawerItem!=null){

                            Fragment fragment=null;
                            Class fragmentClass;
                            String s="";
                            if(drawerItem.getIdentifier()==1){
                                fragmentClass=ChatFragment.class;
                                s="Chat";
                            }
                            else{
                                fragmentClass=ArchivedFragment.class;
                                s="Archived Messages";
                            }
                            try{
                                fragment=(Fragment)fragmentClass.newInstance();
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                            //FragmentTransaction
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,fragment).commit();
                            result.setSelection(drawerItem.getIdentifier());

                        }*/

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();


        //set the back arrow in the toolbar



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState=result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
