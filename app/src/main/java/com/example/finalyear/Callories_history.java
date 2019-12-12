package com.example.finalyear;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.finalyear.adapter.Consumehistory_ListRVAdpater;
import com.example.finalyear.pojos.Event_consume_pojos;
import com.example.finalyear.viewmodel.ConsumeViewmodel;
import com.example.finalyear.viewmodel.Eventviewmodel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Callories_history extends Fragment {
    private RecyclerView recyclerView;
    private Consumehistory_ListRVAdpater adapter;
    private Eventviewmodel eventviewmodel;
    private ConsumeViewmodel consumeViewmodel;
    private  String eventID;


    public Callories_history() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eventviewmodel = ViewModelProviders.of(getActivity()).get(Eventviewmodel.class);
        consumeViewmodel = ViewModelProviders.of(getActivity()).get(ConsumeViewmodel.class);
        Toast.makeText(getActivity(), "welcome", Toast.LENGTH_SHORT).show();
       Bundle bundle = getArguments();
       if (bundle != null)
        {
            eventID = bundle.getString("id");
           // eventviewmodel.eventdetails(eventID);
            consumeViewmodel.addallcallories(eventID);
            Toast.makeText(getActivity(), "size"+eventID, Toast.LENGTH_SHORT).show();

        }
        return inflater.inflate(R.layout.fragment_callories_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.historyRV);
        consumeViewmodel.consumetlistDB.observe(this, new Observer<List<Event_consume_pojos>>() {
            @Override
            public void onChanged(List<Event_consume_pojos> event_consume_pojos) {
                adapter = new Consumehistory_ListRVAdpater(getActivity(),event_consume_pojos);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(adapter);

            }
        });
    }
}
