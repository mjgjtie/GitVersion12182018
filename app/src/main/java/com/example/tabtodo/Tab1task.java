package com.example.tabtodo;

/*
 *  Create by Thien Quang 27/11/2018
  * */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;


public class Tab1task extends ListFragment {
    Button btn;
    ArrayAdapter<String> test;
    ListView showTask;
    ArrayList<String> lst;
//    String[] arrayCitys = {"Proe","Loere","Lklds"};




    private MainActivity mainActivity;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        RecyclerView recyclerView = (RecyclerView)inflater.inflate(R.layout.tab1task,container,false);
//        SetUpRecyclerView(recyclerView);
//        showTask = (ListView)getView().findViewById(R.id.listTest);
        View rootView = inflater.inflate(R.layout.tab1task, container, false);
        dbTasks db = new dbTasks(getActivity());
        ArrayList<String> lst = db.getAccountList();
        test = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lst);
        setListAdapter(test);
        //        test = new ArrayAdapter<String>(getActivity(),R.layout.row,lst);
//        showTask.setAdapter(test);

        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }







}
