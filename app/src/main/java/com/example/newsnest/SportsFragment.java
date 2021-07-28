package com.example.newsnest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsnest.adapter.ItemAdapter;
import com.example.newsnest.model.FetchNews;
import com.example.newsnest.model.ModelClass;
import com.example.newsnest.utils.ApiUtilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {

    static final String country = "IN";
    static final String category = "sports";
    String key = "079dc017d2eb4dce8eeb06aa8f51d8cc";
    ArrayList<ModelClass> modelClassArrayList;
    ItemAdapter itemAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sportsfragment, null);

        recyclerView = v.findViewById(R.id.sportsfragment_rv);
        modelClassArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemAdapter = new ItemAdapter(getContext(), modelClassArrayList);
        recyclerView.setAdapter(itemAdapter);

        fetchNews();

        return v;
    }

    private void fetchNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, key).enqueue(new Callback<FetchNews>() {
            @Override
            public void onResponse(Call<FetchNews> call, Response<FetchNews> response) {
                if (response.isSuccessful()) {
                    modelClassArrayList.addAll(response.body().getArticles());
                    itemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FetchNews> call, Throwable t) {

            }
        });
    }
}
