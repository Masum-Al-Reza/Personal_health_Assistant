package com.example.finalyear;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyear.R;

import com.example.finalyear.helper.Help;
import com.example.finalyear.pojos.Callories_pojos;
import com.example.finalyear.pojos.Event_consume_pojos;
import com.example.finalyear.viewmodel.ConsumeViewmodel;
import com.example.finalyear.viewmodel.Eventviewmodel;

import java.util.List;

public class Diet_details_fragment extends Fragment {
    private Eventviewmodel eventviewmodel;
    private ConsumeViewmodel consumeViewmodel;
    private CardView addcalloryBTNsCV, viewhistoryCV;

    private TextView details_calloryTV, total_calloriesTV, initial_calloriesTV, remaining_calloriesTV;

    private String eventid = null;
    private int Totalcals = 0;

    public Diet_details_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eventviewmodel = ViewModelProviders.of(this).get(Eventviewmodel.class);
        consumeViewmodel = ViewModelProviders.of(this).get(ConsumeViewmodel.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            eventid = bundle.getString("id");
            eventviewmodel.eventdetails(eventid);
            consumeViewmodel.addallcallories(eventid);
            Toast.makeText(getActivity(), "size" + eventid, Toast.LENGTH_SHORT).show();
        }
        return inflater.inflate(R.layout.fragment_diet_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        details_calloryTV = view.findViewById(R.id.details_calloryTV);
        total_calloriesTV = view.findViewById(R.id.total_calloriesTV);
        initial_calloriesTV = view.findViewById(R.id.initial_calloriesTV);
        remaining_calloriesTV = view.findViewById(R.id.remaining_calloriesTV);
        addcalloryBTNsCV = view.findViewById(R.id.addcalloryBTNs);
        viewhistoryCV = view.findViewById(R.id.View_history2);


        eventviewmodel.eventdetailsLD.observe(this, new Observer<Callories_pojos>() {
            @Override
            public void onChanged(Callories_pojos callories_pojos) {
                details_calloryTV.setText(callories_pojos.getDietname());
                total_calloriesTV.setText("total callory: " + callories_pojos.getBudget());
                Totalcals = callories_pojos.getBudget();

            }
        });
        consumeViewmodel.consumetlistDB.observe(this, new Observer<List<Event_consume_pojos>>() {
            @Override
            public void onChanged(List<Event_consume_pojos> event_consume_pojos) {
                int totalcallory = 0;
                for (Event_consume_pojos c : event_consume_pojos) {
                    totalcallory += c.getCallories();


                }
                int remaining = Totalcals - totalcallory;
                remaining_calloriesTV.setText("remaining: " + remaining);
                initial_calloriesTV.setText("initial: " + totalcallory);
                final Bundle bundle=new Bundle();
                bundle.putString("id",eventid);
                viewhistoryCV.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.action_diet_details_to_medicin_history,bundle);
                    }
                });
            }
        });

        addcalloryBTNsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddExpenseDialog();
            }
        });
    }

    private void showAddExpenseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add  Callory");
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.add_callory_dialog_layout, null);
        builder.setView(view);
        final EditText amountET = view.findViewById(R.id.dialogAddExpenseAmountInput);
        final EditText nameET = view.findViewById(R.id.dialogAddExpenseNameInput);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String amount = amountET.getText().toString();
                String name = nameET.getText().toString();
                if (amount.isEmpty() && name.isEmpty()) {
                    //toast
                    return;
                }

                Event_consume_pojos consume =
                        new Event_consume_pojos(null, eventid, name,
                                Integer.parseInt(amount), Help.getvurrentdate());
                consumeViewmodel.savecallory(consume);

            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
