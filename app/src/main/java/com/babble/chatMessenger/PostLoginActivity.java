package com.babble.chatMessenger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.babble.chatMessenger.fragments.ArchivedFragment;
import com.babble.chatMessenger.fragments.BabbleBombFragment;
import com.babble.chatMessenger.fragments.ChatFragment;
import com.babble.chatMessenger.fragments.FavoritesFragment;
import com.babble.chatMessenger.fragments.HelpFragment;
import com.babble.chatMessenger.fragments.SecretChatFragment;
import com.babble.chatMessenger.fragments.SettingsFragment;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.wafflecopter.multicontactpicker.ContactResult;
import com.wafflecopter.multicontactpicker.MultiContactPicker;

import java.util.ArrayList;
import java.util.List;

public class PostLoginActivity extends AppCompatActivity {

    private static final int PROFILE_SETTING = 1;
    //drawer result
    //save our header or result
    private Drawer result = null;
    private MaterialSearchView searchView = null;
    private static final int CONTACT_PICKER_REQUEST = 991;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);
        //TODO Create Default Fragment
        FragmentManager fragmentManager=getSupportFragmentManager();
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        fragmentManager.beginTransaction().replace(R.id.frame_container,new ChatFragment()).commit();


        //todo Reinitialize the search view
        searchView.setVisibility(View.VISIBLE);
        //todo declare the events
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        searchView.setVoiceSearch(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

        //end of initialization

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/fprimary.ttf");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Babble");



        //TODO Handle the AccountHeader setup
        AccountHeader headerResult=new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.heads)
                .addProfiles(
                        new ProfileDrawerItem().withName("Vidit Gupta").withEmail("vidit135g@gmail.com").withIcon(getResources().getDrawable(R.drawable.av6))
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
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withActivity(this)

                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Chat").withIcon(R.drawable.bar1).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Create Group").withIcon(R.drawable.bar2).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Create Babble Bomb").withIcon(R.drawable.bar3).withIdentifier(3),

                        //Secondary elements
                        new PrimaryDrawerItem().withName("Secret Chat").withIcon(R.drawable.bar4).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Archived Messages").withIcon(R.drawable.bar5).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Favorites").withIcon(R.drawable.bar6).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Invite").withIcon(R.drawable.bar7).withIdentifier(7),
                        new PrimaryDrawerItem().withName("Settings").withIcon(R.drawable.bar8).withIdentifier(8),
                        new PrimaryDrawerItem().withName("FAQ").withIcon(R.drawable.bar9).withIdentifier(9)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem.getIdentifier()==1) {
                            searchView.setVisibility(View.VISIBLE);
                            //todo declare the events
                            searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String query) {
                                    //Do some magic
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    //Do some magic
                                    return false;
                                }
                            });

                            searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
                                @Override
                                public void onSearchViewShown() {
                                    //Do some magic
                                }

                                @Override
                                public void onSearchViewClosed() {
                                    //Do some magic
                                }
                            });
                            searchView.setVoiceSearch(true);
                            searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new ChatFragment()).commit();
                        }
                        else if(drawerItem.getIdentifier()==2){
                            searchView.setVisibility(View.GONE);
                            /*Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new GroupChatFragment()).commit();*/
                            if (ContextCompat.checkSelfPermission(PostLoginActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                                new MultiContactPicker.Builder(PostLoginActivity.this) //Activity/fragment context
                                        .hideScrollbar(false) //Optional - default: false
                                        .showTrack(true) //Optional - default: true
                                        .searchIconColor(Color.WHITE) //Optional - default: White
                                        .setChoiceMode(MultiContactPicker.CHOICE_MODE_MULTIPLE) //Optional - default: CHOICE_MODE_MULTIPLE
                                        .handleColor(ContextCompat.getColor(PostLoginActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                                        .bubbleColor(ContextCompat.getColor(PostLoginActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                                        .bubbleTextColor(Color.WHITE) //Optional - default: White
                                        .showPickerForResult(CONTACT_PICKER_REQUEST);
                            }else{
                                Toast.makeText(PostLoginActivity.this, "Remember to go into settings and enable the contacts permission.", Toast.LENGTH_LONG).show();
                            }

                        }

                        else if(drawerItem.getIdentifier()==3){
                            /*
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new BabbleBombFragment()).commit();*/
                            if (ContextCompat.checkSelfPermission(PostLoginActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                                new MultiContactPicker.Builder(PostLoginActivity.this) //Activity/fragment context
                                        .hideScrollbar(false) //Optional - default: false
                                        .showTrack(true) //Optional - default: true
                                        .searchIconColor(Color.WHITE) //Optional - default: White
                                        .setChoiceMode(MultiContactPicker.CHOICE_MODE_MULTIPLE) //Optional - default: CHOICE_MODE_MULTIPLE
                                        .handleColor(ContextCompat.getColor(PostLoginActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                                        .bubbleColor(ContextCompat.getColor(PostLoginActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                                        .bubbleTextColor(Color.WHITE) //Optional - default: White
                                        .showPickerForResult(CONTACT_PICKER_REQUEST);
                            }else{
                                Toast.makeText(PostLoginActivity.this, "Remember to go into settings and enable the contacts permission.", Toast.LENGTH_LONG).show();
                            }

                        }


                        else if(drawerItem.getIdentifier()==4){

                            Intent intent=new Intent(PostLoginActivity.this,PrivateChat.class);
                            startActivity(intent);
                        }

                        else if(drawerItem.getIdentifier()==5){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new ArchivedFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==6){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new FavoritesFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==7){
                            if (ContextCompat.checkSelfPermission(PostLoginActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                                new MultiContactPicker.Builder(PostLoginActivity.this) //Activity/fragment context
                                        .hideScrollbar(false) //Optional - default: false
                                        .showTrack(true) //Optional - default: true
                                        .searchIconColor(Color.WHITE) //Optional - default: White
                                        .setChoiceMode(MultiContactPicker.CHOICE_MODE_MULTIPLE) //Optional - default: CHOICE_MODE_MULTIPLE
                                        .handleColor(ContextCompat.getColor(PostLoginActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                                        .bubbleColor(ContextCompat.getColor(PostLoginActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                                        .bubbleTextColor(Color.WHITE) //Optional - default: White
                                        .showPickerForResult(CONTACT_PICKER_REQUEST);
                            }else{
                                Toast.makeText(PostLoginActivity.this, "Remember to go into settings and enable the contacts permission.", Toast.LENGTH_LONG).show();
                            }

                            /*Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new InviteFragment()).commit();*/
                        }

                        else if(drawerItem.getIdentifier()==8){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new SettingsFragment()).commit();
                        }

                        else if(drawerItem.getIdentifier()==9){
                            Toast.makeText(PostLoginActivity.this, ((Nameable) drawerItem).getName().getText(PostLoginActivity.this), Toast.LENGTH_SHORT).show();
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frame_container,new HelpFragment()).commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CONTACT_PICKER_REQUEST){
            if(resultCode == RESULT_OK) {
                List<ContactResult> results = MultiContactPicker.obtainResult(data);
                Toast.makeText(PostLoginActivity.this,results.get(0).getDisplayName().toString(),Toast.LENGTH_LONG).show();
            } else if(resultCode == RESULT_CANCELED){
                System.out.println("User closed the picker without selecting items.");
            }
        }
        else  if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
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
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
