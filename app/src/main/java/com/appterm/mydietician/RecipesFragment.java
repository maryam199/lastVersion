package com.appterm.mydietician;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecipesFragment extends Fragment {


    ArrayList<Recipe> recipesAfterLoad = new ArrayList<>();
    AdapterRecipe adapterRecipe;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recipes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        adapterRecipe = new AdapterRecipe(getActivity(),recipesAfterLoad);
        recyclerView.setAdapter(adapterRecipe);
        adapterRecipe.setOnItemClickListener(new AdapterRecipe.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe recipe) {
                Intent intent = new Intent(getActivity(),DisplayRecipeActivity.class);
                intent.putExtra("recipe",recipe);
                startActivity(intent);
            }
        });



        AsyncTask<Void,Void,ArrayList<Recipe>> task = new AsyncTask<Void, Void, ArrayList<Recipe>>() {
            @Override
            protected ArrayList<Recipe> doInBackground(Void... voids) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
                databaseAccess.open();
                ArrayList<Recipe> recipes = databaseAccess.getRecipes();
                databaseAccess.close();
                return recipes;
            }

            @Override
            protected void onPostExecute(ArrayList<Recipe> recipes) {
                super.onPostExecute(recipes);
                recipesAfterLoad.addAll(recipes);
                adapterRecipe.notifyDataSetChanged();

            }
        };

        task.execute();

        return view;
    }
}