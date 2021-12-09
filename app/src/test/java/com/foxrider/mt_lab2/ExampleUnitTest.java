package com.foxrider.mt_lab2;

import static org.junit.Assert.fail;

import com.foxrider.mt_lab2.model.NameDescription;
import com.foxrider.mt_lab2.model.RetrofitClient;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    List<NameDescription> nameDescriptions;

    private void getModel() {
        Call<List<NameDescription>> call = RetrofitClient.getInstance().getMyApi().getModel();
        call.enqueue(new Callback<List<NameDescription>>() {
            @Override
            public void onResponse(Call<List<NameDescription>> call, Response<List<NameDescription>> response) {
                System.out.println(response + " success");
                nameDescriptions = response.body();
            }

            @Override
            public void onFailure(Call<List<NameDescription>> call, Throwable t) {
                System.out.println(t.getCause());
                fail();
            }

        });


    }

    @Test
    public void addition_isCorrect() {
        getModel();
        System.out.println(nameDescriptions);
    }
}