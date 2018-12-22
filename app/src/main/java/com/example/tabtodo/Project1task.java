package com.example.tabtodo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Project1task extends AppCompatActivity {
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private Intent intent;

    Button btn;
    ArrayAdapter<String> test;
    ListView showTask;
    ArrayList<String> lst;
    dbTasks db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project1task);

        db = new dbTasks(this);
        ArrayList<String> lst = db.getProjectList();
        showTask = (ListView) findViewById(R.id.lstproject);
        test = new ArrayAdapter<String>(Project1task.this,R.layout.row, R.id.tasktitle,lst);
        showTask.setAdapter(test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_content);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null){
            setUpDrawerContent(navigationView);
        }
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
//              menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Log.d("Click nav home","OKe");
                        intent = new Intent(Project1task.this,MainActivity.class);
                        startActivity(intent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_schedule:
                        Log.d("Click nav schedule","OKe");
                        intent = new Intent(Project1task.this,Calendar1task.class);
                        startActivity(intent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                }
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
                final EditText projectEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Project")
                        .setMessage("What the of your project ?")
                        .setView(projectEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String project = String.valueOf(projectEditText.getText());
                                db.insertProject(project);
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<String> loadProject(){
        ArrayList<String> taskList = db.getProjectList();
        if (test == null) {
            test = new ArrayAdapter<String>(Project1task.this, R.layout.row, R.id.tasktitle, taskList);
            showTask.setAdapter(test);

        }else {
            test.clear();
            test.addAll(taskList);
            test.notifyDataSetChanged();
        }
        return taskList;
    }

}
