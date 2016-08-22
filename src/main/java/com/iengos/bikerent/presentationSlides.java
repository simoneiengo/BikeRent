package com.iengos.bikerent;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class presentationSlides extends AppCompatActivity {

    private static LayoutInflater mInflater;        // used to point to original layout
    private static ViewGroup mRootView;             // and do toggle hide/show in password EditText

    private EditText pass;      // used for hide/show pass
    private Dialog dialog;      // used for regulament popup
    private int slideNum;       // used to set correct slide from intent to login

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        Intent intent = getIntent();
        slideNum = intent.getIntExtra("slideNum", 0);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                ((ImageView)findViewById(R.id.ic_first)).setImageResource(R.drawable.ic_passive_slide);
                ((ImageView)findViewById(R.id.ic_second)).setImageResource(R.drawable.ic_passive_slide);
                ((ImageView)findViewById(R.id.ic_third)).setImageResource(R.drawable.ic_passive_slide);
                ((ImageView)findViewById(R.id.ic_fourth)).setImageResource(R.drawable.ic_passive_slide);

                // active this view
                switch (position){
                    case 0: ((ImageView)findViewById(R.id.ic_first)).setImageResource(R.drawable.ic_active_slide);
                        break;

                    case 1: ((ImageView)findViewById(R.id.ic_second)).setImageResource(R.drawable.ic_active_slide);
                        break;

                    case 2: ((ImageView)findViewById(R.id.ic_third)).setImageResource(R.drawable.ic_active_slide);
                        break;

                    case 3: ((ImageView)findViewById(R.id.ic_fourth)).setImageResource(R.drawable.ic_active_slide);
                        break;
                }
            }
        });
        mViewPager.setCurrentItem(slideNum, false);
    }

    /*============================================================
                    BUTTON LINKS MANAGEMENT
    ============================================================*/
    public void register(View v){
        startActivity(new Intent(getApplicationContext(), home.class));
    }

    public void loginRedir(View v) { startActivity(new Intent(getApplicationContext(), login.class)); }

    /*============================================================
                    DOT LINKS MANAGEMENT
     ============================================================*/
    public void gotoX(View v) {
        // get position from description
        mViewPager.setCurrentItem(Integer.parseInt(((ImageView)v).getContentDescription().toString()),true);
    }

    /*============================================================
                          POP-UP MANAGEMENT
     ============================================================*/
    public void showPopup(View v) {
        if(((CheckBox)v).isChecked()) {
            dialog = new Dialog(presentationSlides.this);
            dialog.setContentView(R.layout.rules_popup);        // set popup layout
            dialog.setTitle("Regulament");
            dialog.show();                                      // open popup
        }
    }

    /*============================================================
                    FRAGMENT MANAGEMENT
     ============================================================*/
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            mInflater = inflater;
            mRootView = container;
            View rootView = null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.slide_description1, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.slide_description2, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.slide_description3, container, false);
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.slide_registration, container, false);
                    break;
            }

            return rootView;
        }
    }
}
