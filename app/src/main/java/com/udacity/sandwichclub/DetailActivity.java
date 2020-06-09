package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public static final String NO_DATA_AVAILABLE = "No Data Available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }


        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimg)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
    private void populateUI(Sandwich sandwich) {
        TextView origin_tv = findViewById(R.id.origin_tv);
        TextView also_known_tv = findViewById(R.id.also_known_tv);
        TextView ingredients_tv = findViewById(R.id.ingredients_tv);
        TextView description_tv = findViewById(R.id.description_tv);
        ArrayList<String> list = new ArrayList<>();
        list.add(NO_DATA_AVAILABLE);

        if (sandwich.getAlsoKnownAs().isEmpty()){
            sandwich.setAlsoKnownAs(list);
        }

        if (sandwich.getPlaceOfOrigin().isEmpty()){
            sandwich.setPlaceOfOrigin(NO_DATA_AVAILABLE);
        }

        also_known_tv.setText(TextUtils.join(", ",sandwich.getAlsoKnownAs()));
        origin_tv.setText(sandwich.getPlaceOfOrigin());
        description_tv.setText(sandwich.getDescription());
        ingredients_tv.setText(TextUtils.join("," ,sandwich.getIngredients()));
    }
}
