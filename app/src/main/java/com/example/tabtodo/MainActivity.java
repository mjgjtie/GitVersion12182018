package com.example.tabtodo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Slide;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    dbTasks lstTask ;
    ArrayAdapter<String> mAdapter ;
    ArrayAdapter<String> test ;
    ListView show;
    ListView showFrag1;




    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_content);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null){
            setUpDrawerContent(navigationView);
        }

        lstTask = new dbTasks(this);

//
//        show list task in Tab1task
//        lstTask.insertTask("Design Task ");
        lstTask.insertTask("KDK");
        lstTask.insertTask("KDK1");
        lstTask.insertTask("KDK2");
//        show = (ListView) findViewById(R.id.test);
//        showFrag1 = (ListView) findViewById(R.id.testTask) ;
//        loadTaskList();
//        show.setAdapter(test);







        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    private void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        //Change menu icon color
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white),PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add new Task")
                        .setMessage("What do you want to do next ?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                lstTask.insertTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position){
                case 0:
                    Tab1task tab1 = new Tab1task();
//                    loadTaskList();
                    return tab1;
                case 1:
                    Tab2todo tab2 = new Tab2todo();
                    return tab2;
                case 2:
                    Tab3done tab3 = new Tab3done();
                    return tab3;
                default:
                    return  null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Tasks";
                case 1:
                    return "Todo Tasks";
                case 2:
                    return "Done Tasks";
            }
            return null;
        }
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView =(TextView)findViewById(R.id.tasktitle);
        String task = String.valueOf(taskTextView.getText());
        lstTask.deleteTask(task);
        loadTaskList();
    }

    public ArrayList<String> loadTaskList(){
        ArrayList<String> taskList = lstTask.getTaskList();
        if (test == null) {
            test = new ArrayAdapter<String>(this, R.layout.row, R.id.tasktitle, taskList);
//            show.setAdapter(mAdapter);

        }else {
            test.clear();
            test.addAll(taskList);
            test.notifyDataSetChanged();
        }
        return taskList;
    }
}
