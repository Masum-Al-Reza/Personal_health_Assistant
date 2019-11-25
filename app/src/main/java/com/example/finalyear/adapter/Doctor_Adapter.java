package com.example.finalyear.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyear.R;
import com.example.finalyear.pojos.Callories_pojos;
import com.example.finalyear.pojos.Docotor_User_pojos;

import java.util.List;

public class Doctor_Adapter extends RecyclerView.Adapter<Doctor_Adapter.EventViewHolder> {
    private Context context;
    private List<Docotor_User_pojos> eventList;


    public Doctor_Adapter(Context context, List<Docotor_User_pojos> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.doctor_list_rows,parent,false);
        return new EventViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull final EventViewHolder holder, final int position) {
         holder.nameTV.setText(eventList.get(position).getDoctor_profilename());
        holder.Callory_typeTV.setText(eventList.get(position).getDoctor_type());
        //holder.CalloryBudgetTV.setText(eventList.get(position).getBudget());
        holder.rowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.adddetailcalories, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String eventid=eventList.get(position).getDoctor_profileID();
                        Bundle bundle=new Bundle();
                        bundle.putString("id",eventid);
                        switch (item.getItemId()){
                            case R.id.item_delete:
                                break;
                            case R.id.item_details:
                                Navigation.findNavController(holder.itemView).navigate(R.id.action_nav_gallery_to_doctor_details,bundle);
                                break;
                            case  R.id.item_edit:
                                break;

                        }
                        return false;
                    }
                });
            }
        });



    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV, CAllory_Date,Callory_typeTV,CalloryBudgetTV,rowTV;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.row_doctorName);
            Callory_typeTV=itemView.findViewById(R.id.row_doctortype);
            rowTV=itemView.findViewById(R.id.row_menu);




        }
    }
}
