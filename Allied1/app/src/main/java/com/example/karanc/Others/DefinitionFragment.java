package com.example.karanc.Others;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karanc.allied1.R;

/**
 * Created by karanc on 14-06-2016.
 */
public class DefinitionFragment extends Fragment {

    public DefinitionFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v=inflater.inflate(R.layout.definition_layout,container,false);
        return v;
    }
}
