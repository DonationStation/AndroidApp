package donationstation.androidapp.controllers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import donationstation.androidapp.R;

public class LocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        RecyclerView locationLists = (RecyclerView) findViewById(R.id.locationLists);
        locationLists.setLayoutManager(new LinearLayoutManager(this));
        String[] languages = {"Java", "JavaScript", "C#", "php", "C", "C++", "Python", "Java", "JavaScript", "C#", "php", "C", "C++", "Python"};
        locationLists.setAdapter(new locationListAdapter(languages));
    }


    // RecyclerView Adapter
    public class locationListAdapter extends RecyclerView.Adapter<locationListAdapter.locationListViewHolder> {

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
            holder.txtTitle.setText(title);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        public class locationListViewHolder extends RecyclerView.ViewHolder {
            ImageView imgIcon;
            TextView txtTitle;
            public locationListViewHolder(View itemView) {
                super(itemView);
                imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
                txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            }
        }
    }
}
