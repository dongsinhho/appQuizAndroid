package com.example.ailatrieuphuonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;


public class RankingActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("BẢNG XẾP HẠNG");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public static class PlaceholderFragment extends Fragment {

        private static final String KEY_COLOR = "key_color";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int color) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(KEY_COLOR, color);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_ranking_fragment, container, false);
            RelativeLayout relativeLayout = view.findViewById(R.id.rl_fragment);

            switch (getArguments().getInt(KEY_COLOR)) {
                case 1:
                    relativeLayout.setBackgroundColor(Color.GREEN);
                    break;
                case 2:
                    relativeLayout.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    relativeLayout.setBackgroundColor(Color.YELLOW);
                    break;
                case 4:
                    relativeLayout.setBackgroundColor(Color.DKGRAY);
                    break;
                case 5:
                    relativeLayout.setBackgroundColor(Color.BLUE);
                    break;
                default:
                    relativeLayout.setBackgroundColor(Color.CYAN);
                    break;
            }
            TextView textView = (TextView) view.findViewById(R.id.section_label);
            textView.setText("List View");
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