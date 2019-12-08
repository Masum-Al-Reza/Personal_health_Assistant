package com.example.finalyear.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyear.R;
import com.example.finalyear.pojos.Event_consume_pojos;
import com.example.finalyear.viewmodel.ConsumeViewmodel;


import java.util.List;

public class Consumehistory_ListRVAdpater extends RecyclerView.Adapter<Consumehistory_ListRVAdpater.ConsumeViewHolder>{
    private Context context;
    private List<Event_consume_pojos> event_consume_pojos;
  //  private ExpenseViewModel expenseViewModel = new ExpenseViewModel();
    private ConsumeViewmodel consumeViewmodel=new ConsumeViewmodel();
    private String expenseID;
    private String eventID;

    public Consumehistory_ListRVAdpater(Context context, List<Event_consume_pojos> event_consume_pojos) {
        this.context = context;
        this.event_consume_pojos = event_consume_pojos;
    }

    @NonNull
    @Override
    public ConsumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.history_list_rows,parent,false);


        return new ConsumeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumeViewHolder holder, final int position) {

        //expenseID = event_consume_pojos.get(position).getConsumeid();
       // eventID = event_consume_pojos.get(position).getEventId();
      //  final Event_consume_pojos expensePojo = event_consume_pojos.get(position);

        holder.ConsumeName.setText(event_consume_pojos.get(position).getConsumename());
        holder.consumecallory.setText(String.valueOf(event_consume_pojos.get(position).getCallories()));
        holder.calloryDate.setText(event_consume_pojos.get(position).getConsumeDateTime());

    /*    holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showExpenseDilog(expenseID,eventID,position);
            }
        });*/
/*  holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expenseViewModel.DeleteExpense(expensePojo);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        Toast.makeText(context, "size"+event_consume_pojos.size(), Toast.LENGTH_SHORT).show();

        return event_consume_pojos.size();

    }

    public class ConsumeViewHolder extends RecyclerView.ViewHolder
    {
        TextView ConsumeName, consumecallory, calloryDate;
        Button editbtn,deletebtn;

        public ConsumeViewHolder(@NonNull View itemView) {
            super(itemView);

            ConsumeName = itemView.findViewById(R.id.row_consumeName);
            consumecallory = itemView.findViewById(R.id.row_consume_callorytarget);
            calloryDate = itemView.findViewById(R.id.row_consume_date);
          /*  editbtn = itemView.findViewById(R.id.row_expenseEdit);
            deletebtn = itemView.findViewById(R.id.row_expenseDelete);*/


        }
    }


  /*  private void showExpenseDilog(final String expenseID,final String eventID,final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Expense");
        View view1 = LayoutInflater.from(context).inflate(R.layout.add_expense_dialog,null);

        builder.setView(view1);
        final EditText expenseNameET = view1.findViewById(R.id.expenseNameET);
        final EditText expenseAmoutET = view1.findViewById(R.id.expenseAmountET);

        expenseNameET.setText(event_consume_pojos.get(position).getExpenseName());
        expenseAmoutET.setText(String.valueOf(event_consume_pojos.get(position).getAmount()));



        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ename = expenseNameET.getText().toString();
                String amount = expenseAmoutET.getText().toString();

                EventExpensePojo expensePojo = new EventExpensePojo(expenseID,eventID,ename,Integer.parseInt(amount), EventUtils.getDateWithTime());

                expenseViewModel.updateExpense(expensePojo);

                Toast.makeText(context,"Updated", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Canel",null);

        AlertDialog dialog = builder.create();

        dialog.show();



    }*/
}
