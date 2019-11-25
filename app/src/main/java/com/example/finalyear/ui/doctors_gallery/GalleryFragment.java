package com.example.finalyear.ui.doctors_gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyear.R;
import com.example.finalyear.adapter.Doctor_Adapter;
import com.example.finalyear.pojos.Docotor_User_pojos;

import java.util.List;

public class GalleryFragment extends Fragment {
    private Doctor_Adapter doctor_adapter;
    private RecyclerView recyclerView;
    private Doctore_User_profile_viewmodel galleryViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(Doctore_User_profile_viewmodel.class);
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
        galleryViewModel.eventlistDB.observe(this, new Observer<List<Docotor_User_pojos>>() {
            @Override
            public void onChanged(List<Docotor_User_pojos> docotor_user_pojos) {
                doctor_adapter = new Doctor_Adapter(getActivity(), docotor_user_pojos);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(doctor_adapter);
            }
        });
    }
}