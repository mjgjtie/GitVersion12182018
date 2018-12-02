package com.example.tabtodo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Tab2todo extends ListFragment {
    ArrayAdapter<String> test;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2todo, container, false);
        dbTasks db = new dbTasks(getActivity());

        ArrayList<String> lst = db.getTaskList();
        test = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lst);
        setListAdapter(test);

        return rootView;
    }
}
