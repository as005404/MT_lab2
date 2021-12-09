package com.foxrider.mt_lab2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.foxrider.mt_lab2.model.NameDescription;
import com.foxrider.mt_lab2.model.RetrofitClient;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {


    private View view;
    public List<NameDescription> nameDescriptions;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        getModel(view);
        return view;
    }


    private void getModel(View view) {
        Call<List<NameDescription>> call = RetrofitClient.getInstance().getMyApi().getModel();
        call.enqueue(new Callback<List<NameDescription>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<NameDescription>> call, Response<List<NameDescription>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "404", Toast.LENGTH_SHORT).show();
                } else {

                    nameDescriptions = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nameDescriptions.stream()
                            .map(NameDescription::getName)
                            .collect(Collectors.toList()));

                    ListView messageList = view.findViewById(R.id.listView);

                    // устанавливаем для списка адаптер
                    messageList.setAdapter(adapter);
                    // добавляем для списка слушатель
                    messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            // получаем выбранный элемент
                            String selectedItemDescription = nameDescriptions.get(position).getDescription();
                            Intent intent = new Intent(getContext(), DetailsActivity.class);
                            intent.putExtra("desc", selectedItemDescription);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<NameDescription>> call, Throwable t) {
                Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
            }

        });

    }

}