package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {




    public static Sandwich parseSandwichJson(String json) throws JSONException {

         String mainName = new String();
         ArrayList<String> list_of_alsoKnownAs = new ArrayList<>();
         ArrayList<String> list_of_ingredients = new ArrayList<>();
         String description = new String();
         String placeOfOrigin = new String();
         String image = new String();

        if (json!= null){
            JSONObject jsonObject  = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");
            mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            for (int  i= 0;i<alsoKnownAs.length();i++){

                list_of_alsoKnownAs.add(alsoKnownAs.getString(i));
            }
            description = jsonObject.getString("description");
            placeOfOrigin = jsonObject.getString("placeOfOrigin");

            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            for (int i = 0; i <ingredients.length() ; i++) {
                list_of_ingredients.add(ingredients.getString(i));

            }
            image = jsonObject.getString("image");
        }

        return new Sandwich(mainName,list_of_alsoKnownAs,placeOfOrigin,description,
                image,list_of_ingredients);


    }
}
