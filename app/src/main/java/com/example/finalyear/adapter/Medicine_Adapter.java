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
import com.example.finalyear.helper.Help;
import com.example.finalyear.pojos.Medicine_User_pojos;
import com.example.finalyear.viewmodel.Mediicine_profile_viewmodel;

import java.util.List;

public class Medicine_Adapter extends RecyclerView.Adapter<Medicine_Adapter.EventViewHolder> {
    private Context context;
    private List<Medicine_User_pojos> eventList;
    private Mediicine_profile_viewmodel mediicine_profile_viewmodel = new Mediicine_profile_viewmodel();


    public Medicine_Adapter(Context context, List<Medicine_User_pojos> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemview = inflater.inflate(R.layout.doctor_list_rows, parent, false);
        return new EventViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull final EventViewHolder holder, final int position) {
        holder.medicinenameTV.setText(eventList.get(position).getMedicine_profilename());
        holder.medicine_typeTV.setText(eventList.get(position).getMedicine_type());
        holder.medicinilimitTV.setText(eventList.get(position).getMedicine_date_limit());
        holder.medicinedatemorningTV.setText(eventList.get(position).getMedicine_time().get(0)
                +"+"+eventList.get(position).getMedicine_time().get(1) +"+"+eventList.get(position).getMedicine_time().get(2));




        String goingDate = (eventList.get(position).getMedicine_date_limit());

        long diffrentDate = Help.getDefferentBetweenTwoDate(Help.getvurrentdate(), goingDate);
        if (diffrentDate == 0) {
            holder.dateLeft.setText("date is going to over");

        } else if (diffrentDate < 0) {
            holder.dateLeft.setText("date over!");
        } else {
            holder.dateLeft.setText(String.valueOf(Help.getDefferentBetweenTwoDate(Help.getvurrentdate(), goingDate)) + " Days Left");

        }

        holder.rowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.adddetailcalories, popupMenu.getMenu());
                popupMenu.show();
                String eventid = eventList.get(position).getMedicine_profileID();
                final Medicine_User_pojos medicine = eventList.get(position);

                final Bundle bundle = new Bundle();
                bundle.putString("id", eventid);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_delete:
                                mediicine_profile_viewmodel.delete(medicine);

                                break;
                            case R.id.item_details:
                                //      Navigation.findNavController(holder.itemView).navigate(R.id.action_nav_gallery_to_doctor_details,bundle);
                                break;
                            case R.id.item_edit:
                                Navigation.findNavController(holder.itemView).navigate(R.id.action_nav_gallery_to_doctors_User_profile, bundle);
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
        public TextView medicinenameTV, medicine_typeTV, medicinilimitTV, medicinedatemorningTV,medicinedatenoonTV,medicinedatenightTV, rowTV, dateLeft;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            medicinenameTV = itemView.findViewById(R.id.row_medicineName);
            medicine_typeTV = itemView.findViewById(R.id.row_medicine_type);
            medicinilimitTV = itemView.findViewById(R.id.row_medicine_limitdate);
            medicinedatemorningTV = itemView.findViewById(R.id.row_medicine_eat_time_morning);

            dateLeft = itemView.findViewById(R.id.dateleft);
            rowTV = itemView.findViewById(R.id.row_menu);


        }
    }
}
