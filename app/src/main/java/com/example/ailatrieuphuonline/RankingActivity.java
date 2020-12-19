package com.example.ailatrieuphuonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class RankingActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    FirebaseDatabase database;
    DatabaseReference Question_Scorce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("BẢNG XẾP HẠNG");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //mViewPager.setOffscreenPageLimit(5);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        database = FirebaseDatabase.getInstance();
        Question_Scorce = database.getReference("Question_Scorce");

        Question_Scorce.orderByChild("question_Score")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            QuestionScore questionScore = postSnapshot.getValue(QuestionScore.class);
                            //int check = Integer.parseInt(ques.levelId);
                            Common.questionScore.add(questionScore);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    public static class PlaceholderFragment extends Fragment {

        private static final String keyFragment = "key_Fragment";
        ListView listView;
        ArrayList<QuestionScore> arrayList;
        QuestionScoreAdapter arrayAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        public static PlaceholderFragment newInstance(int value) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(keyFragment, value);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_ranking_fragment, container, false);
            RelativeLayout relativeLayout = view.findViewById(R.id.rl_fragment);


            listView = view.findViewById(R.id.lvRanking);
            arrayList= new ArrayList<QuestionScore>();
            arrayAdapter = new QuestionScoreAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(arrayAdapter);


        for (int i = 0; i < Common.questionScore.size(); i++) {
            //Log.d(Common.questionScore.get(i).getQuestion_Score(), "Sinh" + Common.questionScore.size());
            if (Common.questionScore.get(i).getQuestion_Score() == "null")
                Common.questionScore.get(i).setScore("0");
            if (Integer.parseInt(Common.questionScore.get(i).getQuestion_Score()) == getArguments().getInt(keyFragment)) {
                arrayList.add(Common.questionScore.get(i));
            }
        }
        Collections.sort(arrayList, new Comparator<QuestionScore>() {
            @Override
            public int compare(QuestionScore o1, QuestionScore o2) {
                if (Integer.parseInt(o1.getScore()) < Integer.parseInt(o2.getScore())) {
                    return 1;
                } else {
                    if (Integer.parseInt(o1.getScore()) == Integer.parseInt(o2.getScore())) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        arrayAdapter.notifyDataSetChanged();

        return view;

        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // position + 1 vì position bắt đầu từ số 0.
            return PlaceholderFragment.newInstance(position + 1);

        }


        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Đố vui - Dân gian";
                case 1:
                    return "Thể thao";
                case 2:
                    return "Khoa học tự nhiên";
                case 3:
                    return "Khoa học xã hội";
                case 4:
                    return "Kiến thức chung";
            }
            return null;
        }
    }

}