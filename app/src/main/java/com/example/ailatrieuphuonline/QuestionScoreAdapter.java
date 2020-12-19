package com.example.ailatrieuphuonline;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuestionScoreAdapter extends ArrayAdapter<QuestionScore> {
    private Activity context;
    private ArrayList<QuestionScore> employeeArrayList;

    public QuestionScoreAdapter(Activity context, int layoutID, List<QuestionScore> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_question_score, null, false);
        }
        QuestionScore questionScore = getItem(position);
        TextView textViewName = convertView.findViewById(R.id.item_name);
        TextView textViewScore = convertView.findViewById(R.id.item_score);
        LinearLayout linearLayout = convertView.findViewById(R.id.linearLayout);
        if (questionScore.getUser() != null) {
            textViewName.setText(questionScore.getUser());
        }
        else textViewName.setText("");

        textViewScore.setText(questionScore.getScore());
        return convertView;
    }
}
