package com.example.finalyear;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalyear.adapter.EventAdapter;
import com.example.finalyear.firebase_repository.Eventviewmodel;
import com.example.finalyear.pojos.Callories_pojos;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Diet_panel extends Fragment {
    private Eventviewmodel eventviewmodel;
    private EventAdapter eventAdapter;
    private RecyclerView recyclerView;



    public Diet_panel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eventviewmodel= ViewModelProviders.of(this).get(Eventviewmodel.class);

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_diet_panel, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_menu,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.madditem:
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.action_diet_panel_to_diet_Plan);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.DietRV);
        eventviewmodel.eventlistDB.observe(this, new Observer<List<Callories_pojos>>() {
            @Override
            public void onChanged(List<Callories_pojos> tourmateEvents) {
                eventAdapter = new EventAdapter(getActivity(), tourmateEvents);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(eventAdapter);

            }
        });
    }
}
