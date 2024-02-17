package com.example.a2340a_team10.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340a_team10.R;
import com.example.a2340a_team10.model.Attempt;

import java.util.ArrayList;

public class AttemptListContainer extends RecyclerView.Adapter<AttemptListContainer.MyViewHolder> {
    private Context context;
    private ArrayList<Attempt> attemptHistory;
    private int count;

    private boolean addRank;

    public AttemptListContainer(Context context, ArrayList<Attempt> attemptHistory) {
        this(context, attemptHistory, attemptHistory.size(), true);
    }

    public AttemptListContainer(Context context, ArrayList<Attempt> attemptHistory, int count,
                                boolean addRank) {
        this.context = context;
        this.attemptHistory = attemptHistory;
        this.count = count;
        this.addRank = addRank;
    }
    @NonNull
    @Override
    public AttemptListContainer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                int viewType) {
        //inflating the layout (giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_veiw_row, parent, false);
        return new AttemptListContainer.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttemptListContainer.MyViewHolder holder, int position) {
        //assigning values to the views we created in the layout, based on the position
        holder.name.setText(attemptHistory.get(position).getName());
        holder.score.setText(String.valueOf(attemptHistory.get(position).getScore()));
        holder.time.setText(String.valueOf(attemptHistory.get(position).getTime()));
        holder.avatar.setImageResource(attemptHistory.get(position).getAvatar());
        if (addRank) {
            holder.rank.setText(String.valueOf(position + 1));
        } else {
            holder.rank.setText("Last:");
        }

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //grabbing the views from the row layout file
        private ImageView avatar;
        private TextView name;
        private TextView score;
        private TextView time;
        private TextView rank;
        public MyViewHolder(final View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.Charaname);
            score = itemView.findViewById(R.id.score);
            time = itemView.findViewById(R.id.attemptTime);
            rank = itemView.findViewById(R.id.rank);
        }
    }
}
