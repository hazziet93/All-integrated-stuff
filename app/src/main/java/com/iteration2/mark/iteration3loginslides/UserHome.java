package com.iteration2.mark.iteration3loginslides;

        import java.util.ArrayList;
        import java.util.Locale;

        import android.content.Intent;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.os.Bundle;
        import android.support.v4.view.ViewPager;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.astuetz.PagerSlidingTabStrip;


public class UserHome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class HomeFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public final ArrayList<String> tags = new ArrayList<>();

        //Declaration of a List Handler
        public ListHandler subscriptionsController, updatesController;

        public HomeFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static HomeFragment newInstance(int sectionNumber) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_user_home, container, false);


            //This method will call the ListController instance of ListHandler in this view
            subscriptionsController = new ListHandler(rootView, (ListView) rootView.findViewById(R.id.subscriptionsListView));
            updatesController = new ListHandler(rootView, (ListView) rootView.findViewById(R.id.recentUpdatesListView));


            ArrayList<String> subscriptionsArray = new ArrayList<String>();
            ArrayList<String> updatesArray = new ArrayList<String>();

            subscriptionsArray.add("Tennis Society");
            subscriptionsArray.add("Chess Society");
            subscriptionsArray.add("Cooking Society");
            subscriptionsArray.add("FragSoc");
            subscriptionsArray.add("YUWake");

            updatesArray.add("FragSoc: Tonight's social will take place in room RM05, please pass on this message to anybody concerned");

            //This method will call the setSocieties method from the ListController instance of ListHandler
            subscriptionsController.setSocieties(subscriptionsArray);
            updatesController.setSocieties(updatesArray);

            subscriptionsController.mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                    TextView clickedView = (TextView) view;

                    Toast.makeText(view.getContext(), "Item with id [" + id + "] - position [" + position + "] - Text [" + clickedView.getText() + "]", Toast.LENGTH_SHORT).show();
                    Intent toSocHomeUser = new Intent(view.getContext(),SocietyHome.class);
                    toSocHomeUser.putExtra("socName", clickedView.getText());
                    startActivity(toSocHomeUser);
                }
            });

            updatesController.mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                    TextView clickedView = (TextView) view;

                    Toast.makeText(view.getContext(), "Item with id [" + id + "] - position [" + position + "] - Text [" + clickedView.getText() + "]", Toast.LENGTH_SHORT).show();
                    Intent toSocHomeUpdates = new Intent(view.getContext(),SocietyHome.class);
                    toSocHomeUpdates.putExtra("socName", clickedView.getText());
                    startActivity(toSocHomeUpdates);
                }
            });

            return rootView;
        }
    }

    public static class DiscoverFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public DiscoverFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static DiscoverFragment newInstance(int sectionNumber) {
            DiscoverFragment fragment = new DiscoverFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_discover_page, container, false);
            return rootView;
        }
    }

    public static class BrowseFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public final ArrayList<String> tags = new ArrayList<>();
        //3D string array declaration to hold [Society][Category][Tag]
        public String[][][] SocietyNames = new String[400][2][10];
        //Declaration of string arrays to hold categories and tags
        public ArrayList<String> categories = new ArrayList<>();
        //Declaration of a List Handler
        public ListHandler ListController;

        public BrowseFragment() {


        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static BrowseFragment newInstance(int sectionNumber) {

            BrowseFragment fragment = new BrowseFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }





        View rootView;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_browse_page, container, false);

            ListView list = (ListView) rootView.findViewById(R.id.browseListView);
            //This method will call the ListController instance of ListHandler in this view
            ListController = new ListHandler(rootView, list);

            //This method will populate the categories array with the categories found in the string.XML resource file
            categories = ListController.allCategories(categories);

            //This method will add the name and category of each society to the SocietyNames array
            SocietyNames = ListController.addSocieties();

            //Add dummy-tag "tag" to the tags array
            tags.add("tag");

            //This method will call the setSocieties method from the ListController instance of ListHandler
            ListController.setSocieties((ListController.filterByCategoryThenTag(SocietyNames, categories, tags)));

            Button openCategoriesButton = (Button) rootView.findViewById(R.id.filterButton);

            openCategoriesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListController.showOptions();
                }
            });

            Button closeCategoriesButton = (Button) rootView.findViewById(R.id.filterCompleteButton);

            closeCategoriesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListController.closeOptions();
                }
            });
            CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8;

            checkBox1 = (CheckBox) rootView.findViewById(R.id.checkBox1);
            checkBox2 = (CheckBox) rootView.findViewById(R.id.checkBox2);
            checkBox3 = (CheckBox) rootView.findViewById(R.id.checkBox3);
            checkBox4 = (CheckBox) rootView.findViewById(R.id.checkBox4);
            checkBox5 = (CheckBox) rootView.findViewById(R.id.checkBox5);
            checkBox6 = (CheckBox) rootView.findViewById(R.id.checkBox6);
            checkBox7 = (CheckBox) rootView.findViewById(R.id.checkBox7);
            checkBox8 = (CheckBox) rootView.findViewById(R.id.checkBox8);

            checkBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });
            checkBox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });
            checkBox3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });
            checkBox4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });
            checkBox5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });
            checkBox6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });
            checkBox7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });
            checkBox8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    categories = ListController.updateCategories(v, categories);

                    //set the updated society list to the listView
                    ListController.setSocieties(ListController.filterByCategoryThenTag(SocietyNames, categories, tags));

                }
            });

            ListController.mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                    TextView clickedView = (TextView) view;

                    Toast.makeText(view.getContext(), "Item with id [" + id + "] - position [" + position + "] - Text [" + clickedView.getText() + "]", Toast.LENGTH_SHORT).show();
                    Intent toSocHomeBrowse = new Intent(view.getContext(),SocietyHome.class);
                    toSocHomeBrowse.putExtra("socName", clickedView.getText());
                    startActivity(toSocHomeBrowse);
                }
            });

            return rootView;
        }
    }


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
            switch (position) {
                case 0:
                    return HomeFragment.newInstance(position);
                case 1:
                    return DiscoverFragment.newInstance(position);
                case 2:
                    return BrowseFragment.newInstance(position);
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

}
