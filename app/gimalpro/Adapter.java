package org.app.gimalpro;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Todoitem> todoitems;
    private Context context;
    private DBHelp_health_list dbHelpHealthlist;

    public Adapter(ArrayList<Todoitem> todoitems, Context context) {
        this.todoitems = todoitems;
        this.context = context;
        dbHelpHealthlist = new DBHelp_health_list(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(todoitems.get(position).getTitle());
        holder.tv_content.setText(todoitems.get(position).getContent());
        holder.tv_date.setText(todoitems.get(position).getWritedate());

    }

    @Override
    public int getItemCount() {
        return todoitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_content=itemView.findViewById(R.id. tv_content);
            tv_date=itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curpos = getAdapterPosition(); //????????? ??????
                    Todoitem todoitem = todoitems.get(curpos);

                    String[] strChoiceitems = {"????????????","????????????"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("????????? ????????? ???????????????");
                    builder.setItems(strChoiceitems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position) {
                            if (position ==0){
                                //??????
                                Dialog dialog = new Dialog(context, android.R.style.Theme_Material_Light_Dialog);
                                dialog.setContentView(R.layout.dialog);
                                EditText et_title = dialog.findViewById(R.id.et_title);
                                EditText et_content = dialog.findViewById(R.id.et_content);
                                Button btn_ok = dialog.findViewById(R.id.bt_ok);

                                et_title.setText(todoitem.getTitle());
                                et_content.setText(todoitem.getContent());
                                //?????? ??????
                                et_title.setSelection(et_title.getText().length());

                                btn_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //db update
                                        String tittle = et_title.getText().toString();
                                        String content = et_content.getText().toString();
                                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//???????????? ????????????
                                        String beforedate = todoitem.getWritedate();
                                        dbHelpHealthlist.updateTodo(LoginActivity.UserID,tittle,content,currentTime,beforedate);

                                        //ui update
                                        todoitem.setTitle(tittle);
                                        todoitem.setContent(content);
                                        todoitem.setWritedate(currentTime);
                                        notifyItemChanged(curpos,todoitem);
                                        dialog.dismiss();
                                        Toast.makeText(context.getApplicationContext(), "???????????? ??????", Toast.LENGTH_SHORT).show();


                                    }
                                });
                                dialog.show();
                            }
                            else if (position ==1){
                                //????????? ??????
                                String beforedate = todoitem.getWritedate();
                                dbHelpHealthlist.deleteTodo(beforedate,LoginActivity.UserID);

                                //delete ui
                                todoitems.remove(curpos);
                                notifyItemRemoved(curpos);
                                Toast.makeText(context, "????????? ?????????????????????", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    builder.show();
                }
            });

        }

        }
    //????????? ????????? ?????? ??????
    public void additem(Todoitem _item){
        todoitems.add(0,_item); //?????? ???????????? ???????????? ??????
        notifyItemInserted(0);


    }





}
