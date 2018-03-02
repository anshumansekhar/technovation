package com.technovation.technovation20;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class EventSchedule extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_schedule);

        toolbar = (Toolbar) findViewById(R.id.eventScheduleToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Event Schedule");

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pager=(ViewPager)findViewById(R.id.pager);

        setupViewPager(pager);

        tabLayout.setupWithViewPager(pager);

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new Day1Fragment(), "Day1");
        adapter.addFragment(new Day2(), "Day2");
        adapter.addFragment(new Day3Fragment(), "Day3");
        viewPager.setAdapter(adapter);
    }
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
