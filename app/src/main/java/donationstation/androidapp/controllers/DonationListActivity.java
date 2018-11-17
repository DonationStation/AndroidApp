package donationstation.androidapp.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import donationstation.androidapp.R;

/**
 * Class to present list of donations
 */
public class DonationListActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private MyAdapter adapter;
    private List<String> listData;
    private FirebaseDatabase FDB;
    private DatabaseReference DBR;
    private String keyString;
    private String locationName;
    private ArrayList<String> locationSearch, categorySearch;
    private String nameSearch;
    private ArrayList<String[]> donationDetailInfo;
    private boolean isUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);

        // Retrieve the current userType
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            keyString = null;
            locationSearch = null;
            categorySearch = null;
            nameSearch = null;
        } else {
            keyString = bundle.getString("key");
            locationSearch = bundle.getStringArrayList("locationSearch");
            categorySearch = bundle.getStringArrayList("categorySearch");
            nameSearch = bundle.getString("nameSearch");
        }
        locationName = keyString;

        myRecyclerView = findViewById(R.id.donationLists);
        myRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager LM = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(LM);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayoutManager.VERTICAL));

        listData = new ArrayList<>();
        donationDetailInfo = new ArrayList<>();
        adapter = new MyAdapter(listData);
        FDB = FirebaseDatabase.getInstance();
        GetDataFirebase();
    }

    void GetDataFirebase() {
        // For users
        if (keyString == null) {
            isUser = true; // a var to differentiate whether the current user is an user or employee
            DBR = FDB.getReference("Locations");
            DBR.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Filter by passed values.
                    for (DataSnapshot location : dataSnapshot.getChildren()) { // Iterate all locations under 'Location' from Firebase.
                                                                               // location: AFD Station 4, Boys and Girls Club WW Woolfolk, ...

                        if (locationSearch.contains(location.getKey().toString())) { // location Filter
                                                                                     // locationSearch: [AFD Station4], [D&D Convenience Store], ... [Pavilion Of Hope Inc], or [A, D, ... P] which is all.

                            for (DataSnapshot item : location.child("Inventory").getChildren()) { // Iterate all items under 'Inventory' from a specific location.
                                                                                                  // item: Item1, Item2, ... (except. size)

                                if (!item.getKey().toString().equals("size")) { // if statement for not handling with "size" item.
                                    // Variables will be used for remembering.
                                    String category = item.child("category").getValue().toString(); // for category (Clothes, Other, ...)
                                    String shortDes = item.child("shortDes").getValue().toString(); // for itemName (e.g. shortDescription)
                                    String locationKey = location.getKey().toString(); // each location Name.
                                    String itemKey = item.getKey().toString(); // each item Name.

                                    if (categorySearch.contains(category)) { // category Filter
                                                                             // categorySearch: [Clothes], ... [Other], [Clothes, ... Other]

                                        // name Filter
                                        if (nameSearch.equals("")) { // if user didn't type anything on name text View
                                            // Get ready for populating every item in there.
                                            listData.add(shortDes);
                                            String[] detailInfo = {locationKey, itemKey};
                                            donationDetailInfo.add(detailInfo);
                                        } else { // if user typed something
                                            if (nameSearch.equals(shortDes)) { // Get ready for populating only the item that has the same shortDes with shortDes.
                                                listData.add(shortDes);
                                                String[] detailInfo = {locationKey, itemKey};
                                                donationDetailInfo.add(detailInfo);
                                            }
                                        }
                                        // listData: an ArrayList<String> of each item's shortDes that is satisfied with the condition above (ex. [test, final, ...])
                                        // donationDetailInfo: an ArrayList<String[]> of [locationKey, itemKey] as respective to listData order (ex. [[AFD Station 4, Item1])
                                        // We need donationDetailInfo for remembering and retrieving a specific item on DetailActivity.
                                    }
                                }
                            }
                        } // We don't need to consider else case because locationSearch contains all cases.
                          // If you want to put else statement here, just implement a code of dealing with error.
                    }
                    if (listData.isEmpty()) { // if there is no any item that is satisfied with the conditions an user has typed.
                        // Create a dummy listData and donationDetailInfo so that recycler View shows "Nothing found!"
                        listData.add("No result found. Try another search.");
                        String[] nothingInfo = {"Nothing", "Nothing"};
                        donationDetailInfo.add(nothingInfo);
                    }

                    myRecyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else { // For Employees
            DBR = FDB.getReference("Locations").child(keyString).child("Inventory");
            DBR.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String data = dataSnapshot.getKey();
                    if (!data.equals("size")) { // Exclude displaying "size" category
                        listData.add(data);
                    }
                    myRecyclerView.setAdapter(adapter);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }



    }

    /**
     * helper class to pass user's input to DonationDetailActivity
     * functions as our RecyclerView Adapter for DonationListActivity
     */
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        List<String> listArray;

        /**
         *
         * @param list thingy
         */
        public MyAdapter(List<String> list) { this.listArray = list; }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.list_donation_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            String title = listArray.get(position);
            holder.MyText.setText(title);
            final String currentKey;
            final String locationNameKey;

            // Set currentKey and locationNameKey in terms of the current user type.
            if (isUser) { // When it's an user.
                currentKey = donationDetailInfo.get(position)[1];
                locationNameKey = donationDetailInfo.get(position)[0];
            } else { // When it's an employee.
                currentKey = listData.get(position);
                locationNameKey = locationName;
            }

            // When click each item
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DonationListActivity.this,
                            DonationDetailActivity.class);
                    // Get ready for passing position to the next activity
                    intent.putExtra("key", currentKey);
                    intent.putExtra("locationName", locationNameKey);
                    startActivity(intent);
                }
            });
        }

        /**
         * helper class to pass user's input to DonationDetailActivity
         * functions as our RecyclerView Holder for DonationListActivity
         */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView MyText;
            public LinearLayout linearLayout;

            /**
             *
             * @param itemView holds text
             */
            public MyViewHolder(View itemView) {
                super(itemView);
                MyText = itemView.findViewById(R.id.location_name);
                linearLayout = itemView.findViewById(R.id.donation_linear_layout);
            }
        }

        @Override
        public int getItemCount() {
            return listArray.size();
        }
    }
}
