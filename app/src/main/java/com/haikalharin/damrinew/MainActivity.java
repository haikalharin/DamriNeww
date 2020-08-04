package com.haikalharin.damrinew;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.haikalharin.damrinew.Adapter.ListArrayAdapter;
import com.haikalharin.damrinew.Api.ApiService;
import com.haikalharin.damrinew.Api.Server;
import com.haikalharin.damrinew.Entity.DataItem;
import com.haikalharin.damrinew.Entity.GlobalConstan;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<DataItem> datadamri = new ArrayList<DataItem>();
    ListView listview;
    ListArrayAdapter adapter;

    ApiService api;
    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout_loading = (LinearLayout) findViewById(R.id.layout_loading);

        text_load = (TextView) findViewById(R.id.text_load);
        icon_load = (ImageView) findViewById(R.id.icon_load);

        listview = (ListView) findViewById(R.id.listdamri);
        listview.setOnItemClickListener(MainActivity.this);
        listview.setDividerHeight(0);

        api = Server.getApiService();
        setup();

    }

    public void setup() {

        api.getSemuaData(GlobalConstan.TOKEN).enqueue(new Callback<List<DataItem>>() {
            @Override
            public void onResponse(Call<List<DataItem>> call, Response<List<DataItem>> response) {

                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        DataItem data = new DataItem(
                                response.body().get(i).getAsal(),
                                response.body().get(i).getNmAsal(),
                                response.body().get(i).getNmKota(),
                                response.body().get(i).getLatloc(),
                                response.body().get(i).getLongloc(),
                                response.body().get(i).getAlamat());
                        datadamri.add(data);
                        Log.d("RESPON", "onResponse: " + response.body().get(i).getAsal());

                    }
                    listview.setVisibility(View.VISIBLE);
                    adapter = new ListArrayAdapter(MainActivity.this, R.layout.list, datadamri);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {
                        layout_loading.setVisibility(View.VISIBLE);
                        String error = "Daftar mahasiswa Kosong";
                        text_load.setText(error);
                        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_data_kosong);
                        icon_load.setImageBitmap(icon);
                    } else {
                        layout_loading.setVisibility(View.GONE);
                    }
                } else {
                    String error = "Error Retrive Data from Server !!!";
//                    text_load.setText(error);
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
//                    icon_load.setImageBitmap(icon);

                }

            }

            @Override
            public void onFailure(Call<List<DataItem>> call, Throwable t) {
                String error = "Error Retrive Data from Server wwaau!!!\n" + t.getMessage();
                text_load.setText(error);
                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_network);
                icon_load.setImageBitmap(icon);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            adapter.clear();
            setup();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
