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
import com.example.finalyear.pojos.Callories_pojos;
import com.example.finalyear.viewmodel.Eventviewmodel;


import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private Context context;
    private List<Callories_pojos> eventList;
    private Eventviewmodel eventviewmodel=new Eventviewmodel();


    public EventAdapter(Context context, List<Callories_pojos> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.list_rows,parent,false);
        return new EventViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull final EventViewHolder holder, final int position) {
        holder.nameTV.setText(eventList.get(position).getDietname());
        holder.CAllory_Date.setText(""+eventList.get(position).getCallories_date());
        holder.Callory_typeTV.setText(""+eventList.get(position).getDiet_type());
        holder.CalloryBudgetTV.setText(String.valueOf(eventList.get(position).getBudget()));
        String goingDate =(eventList.get(position).getCallories_date());


        long diffrentDate = Help.getDefferentBetweenTwoDate(Help.getvurrentdate(),goingDate);
        if (diffrentDate==0)
        {
            holder.dateLeft.setText("Date going to over");

        }
        else if (diffrentDate<0)
        {
            holder.dateLeft.setText("Date over");
        }

        else
        {
            holder.dateLeft.setText(String.valueOf(Help.getDefferentBetweenTwoDate(Help.getvurrentdate(),goingDate))+" Days Left");

        }
        holder.rowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.adddetailcalories, popupMenu.getMenu());
                popupMenu.show();
                String eventid=eventList.get(position).getEventID();
                final Callories_pojos callories_pojos=eventList.get(position);
                final Bundle bundle=new Bundle();
                bundle.putString("id",eventid);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.item_delete:
                                eventviewmodel.delete(callories_pojos);

                                break;
                            case R.id.item_details:
                               Navigation.findNavController(holder.itemView).navigate(R.id.action_diet_panel_to_diet_details2,bundle);
                                break;
                            case  R.id.item_edit:
                                Navigation.findNavController(holder.itemView).navigate(R.id.diet_Plan,bundle);
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
        public TextView nameTV, CAllory_Date,Callory_typeTV,dateLeft,CalloryBudgetTV,rowTV;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.row_eventName);
            CAllory_Date =itemView.findViewById(R.id.row_departureDate);
            Callory_typeTV=itemView.findViewById(R.id.row_callorytype);
            dateLeft=itemView.findViewById(R.id.dateleft);
            CalloryBudgetTV=itemView.findViewById(R.id.row_callory_budget);
            rowTV=itemView.findViewById(R.id.row_menu);


       }
    }
}
