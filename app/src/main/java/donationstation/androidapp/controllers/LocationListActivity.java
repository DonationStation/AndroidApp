package donationstation.androidapp.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import donationstation.androidapp.R;
import donationstation.androidapp.model.SimpleModel;

public class LocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);


        SimpleModel locations = SimpleModel.INSTANCE;
        RecyclerView locationLists = (RecyclerView) findViewById(R.id.locationLists);
        locationLists.setLayoutManager(new LinearLayoutManager(this));
        String[] languages = {locations.getItems().get(0).getName(), locations.getItems().get(1).getName(),
                locations.getItems().get(2).getName(), locations.getItems().get(3).getName(),
                locations.getItems().get(4).getName(), locations.getItems().get(5).getName()};
        locationLists.setAdapter(new locationListAdapter(languages));


    }


    // RecyclerView Adapter
    public class locationListAdapter extends RecyclerView.Adapter<locationListAdapter.locationListViewHolder> {

        private Activity context;

        private String[] data;
        public locationListAdapter(String[] data) {
            this.data = data;
        }

        @NonNull
        @Override
        public locationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_location_layout, parent, false);
            return new locationListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull locationListViewHolder holder, int position) {
            String title = data[position];
            holder.locationName.setText(title);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LocationDetailActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        public class locationListViewHolder extends RecyclerView.ViewHolder {
            public TextView locationName;
            public LinearLayout linearLayout;

            public locationListViewHolder(View itemView) {
                super(itemView);
                locationName = (TextView) itemView.findViewById(R.id.locationName);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            }
        }
    }
}