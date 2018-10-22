//package donationstation.androidapp.controllers;
//
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import donationstation.androidapp.R;
//
//
//public class DonationListActivity extends AppCompatActivity {
//
//    RecyclerView myRecyclerView;
//    MyAdapter adapter;
//    List<String> listData;
//    FirebaseDatabase FDB;
//    DatabaseReference DBR;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_donation_list);
//
//        myRecyclerView = (RecyclerView) findViewById(R.id.donationLists);
//        myRecyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager LM = new LinearLayoutManager(getApplicationContext());
//        myRecyclerView.setLayoutManager(LM);
//        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        myRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
//
//        listData = new ArrayList<>();
//        adapter = new MyAdapter(listData);
//        FDB = FirebaseDatabase.getInstance();
//        GetDataFirebase();
//    }
//
//    void GetDataFirebase() {
//        DBR = FDB.getReference("Locations");
//
//        DBR.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String data = dataSnapshot.getKey().toString();
//                listData.add(data);
//                myRecyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//
//        List<String> listArray;
//
//        public MyAdapter(List<String> list) { this.listArray = list; }
//
//        @NonNull
//        @Override
//        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_donation_layout, parent, false);
//            return new MyViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//            String title = listArray.get(position);
//            holder.MyText.setText(title);
//            final String currentKey = listData.get(position);
//
//            // When click each item
//            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(DonationListActivity.this, DonationDetailActivity.class);
//                    // Get ready for passing position to the next activity
//                    intent.putExtra("key", currentKey);
//                    startActivity(intent);
//                }
//            });
//        }
//
//        public class MyViewHolder extends RecyclerView.ViewHolder {
//            public TextView MyText;
//            public LinearLayout linearLayout;
//
//            public MyViewHolder(View itemView) {
//                super(itemView);
//                MyText = (TextView) itemView.findViewById(R.id.location_name);
//                linearLayout = (LinearLayout) itemView.findViewById(R.id.donation_linear_layout);
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return listArray.size();
//        }
//    }
//}
