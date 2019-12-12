package com.example.finalyear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyear.adapter.Medicine_Adapter;
import com.example.finalyear.pojos.Medicine_User_pojos;
import com.example.finalyear.viewmodel.Mediicine_profile_viewmodel;

import java.util.List;
import java.util.Objects;

public class MedicinelistFragment extends Fragment {
    private Medicine_Adapter medicine_adapter;
    private RecyclerView recyclerView;
    private Mediicine_profile_viewmodel galleryViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        galleryViewModel =
                ViewModelProviders.of(this).get(Mediicine_profile_viewmodel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
      setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.additem:
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.action_nav_gallery_to_doctors_User_profile);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.doctorListRV);
        galleryViewModel.eventlistDB.observe(this, new Observer<List<Medicine_User_pojos>>() {
            @Override
            public void onChanged(List<Medicine_User_pojos> medicine_user_pojos) {
                medicine_adapter = new Medicine_Adapter(getActivity(), medicine_user_pojos);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(medicine_adapter);
            }
        });
    }
}