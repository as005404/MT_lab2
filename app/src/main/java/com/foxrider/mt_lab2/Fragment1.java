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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxrider.mt_lab2.model.Meme;
import com.foxrider.mt_lab2.model.MemeResponse;
import com.foxrider.mt_lab2.model.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {


    private View view;
    public MemeResponse memeResponse;
    public List<Meme> memes;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        getModel(view);
        return view;
    }


    private void getModel(View view) {
        Call<MemeResponse> call = RetrofitClient.getInstance().getMyApi().getModel();
        call.enqueue(new Callback<MemeResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<MemeResponse> call, Response<MemeResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "404", Toast.LENGTH_SHORT).show();
                } else {

                    memes = response.body().getData().getMemes();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, memes.stream()
                            .map(Meme::getName)
                            .collect(Collectors.toList()));

                    ListView messageList = view.findViewById(R.id.listView);

                    // устанавливаем для списка адаптер
                    messageList.setAdapter(adapter);
                    // добавляем для списка слушатель
                    messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            // получаем выбранный элемент
                            String prettyString = null;
                            try {
                                String selectedItemDescription = new ObjectMapper().writeValueAsString(memes.get(position));
                                JSONObject json = new JSONObject(selectedItemDescription);
                                prettyString = json.toString(4);

                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            System.out.println(); // Print it with specified indentation

                            Intent intent = new Intent(getContext(), DetailsActivity.class);
                            intent.putExtra("desc", prettyString);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MemeResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
            }

        });

    }

}