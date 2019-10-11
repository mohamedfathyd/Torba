package com.khalej.Boutique.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.khalej.Boutique.Adapter.RecyclerAdapter;
import com.khalej.Boutique.Adapter.RecyclerAdapter_offers;
import com.khalej.Boutique.R;
import com.khalej.Boutique.model.Apiclient_home;
import com.khalej.Boutique.model.apiinterface_home;
import com.khalej.Boutique.model.contact_offers;
import com.khalej.Boutique.model.contact_offers_realm;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class offers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_offers recyclerAdapter;
    private List<contact_offers> contactList;
    private apiinterface_home apiinterface;
    ProgressBar progressBar;
    ImageView car;
    Realm realm;
    TextView textView;
    private SharedPreferences sharedpref;
    Typeface myTypeface;
    Intent intent;
    int secondry_id;
    String name;
    TextView textCartItemCount;
    int mCartItemCount = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Nasser.otf", true);
        sharedpref = getSharedPreferences("Boutique", Context.MODE_PRIVATE);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        this.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        if (!isNetworkAvailable(offers.this)) {
            showdata();
            progressBar.setVisibility(View.GONE);
        } else {
            fetchInfo();
        }


    }

    public void fetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_offers>> call = apiinterface.getcontacts_offers();
        call.enqueue(new Callback<List<contact_offers>>() {
            @Override
            public void onResponse(Call<List<contact_offers>> call, Response<List<contact_offers>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                WriteTodatabase(contactList);

            }

            @Override
            public void onFailure(Call<List<contact_offers>> call, Throwable t) {

            }
        });
    }
    public void WriteTodatabase(List<contact_offers> contactList){
//             realm.delete(subject_content_realm.class);
        // Create a new object
        deletedata();
        if(!(contactList==null)){
            for(int i=0;i<contactList.size();i++){
                realm.beginTransaction();
                contact_offers_realm images = realm.createObject(contact_offers_realm.class);
                images.setId(contactList.get(i).getId());
                images.setName(contactList.get(i).getName());
                images.setImg(contactList.get(i).getImg());
                images.setOldprice(contactList.get(i).getOldprice());
                images.setPrice(contactList.get(i).getPrice());
                images.setDescription(contactList.get(i).getDescription());
                images.setNameca(name);
                realm.commitTransaction();
            }
            showdata();}
        else{}
    }
    public void showdata(){

        RealmResults<contact_offers_realm> content_realms = realm.where(contact_offers_realm.class).equalTo("nameca", name).findAll();
        if (content_realms.isEmpty() || content_realms.equals(null)) {

        } else {    // realm.beginTransaction();
            List<contact_offers_realm> result = content_realms;


            recyclerAdapter=new RecyclerAdapter_offers(offers.this,result);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    public void deletedata(){
        realm.beginTransaction();
        RealmResults<contact_offers_realm> content_realms=realm.where(contact_offers_realm.class).equalTo("nameca", name).findAll();
        content_realms.deleteAllFromRealm();
        realm.commitTransaction();
    }
    public boolean isNetworkAvailable(Context ctx) {
        if (ctx == null)
            return false;

        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_cart: {
                // Do something
                startActivity(new Intent(offers.this,Order_Details.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {
        mCartItemCount= sharedpref.getInt("number",0);
        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }


}
