package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
     public static final String J_OBJ_NAME = "name";
     public static final String J_OBJ_MAIN_NAME = "main_name";
     public static final String J_OBJ_AlSO_KNOWN_AS = "alsoKnownAs";
     public static final String J_OBJ_DESCRIPTION = "descroption";
     public static final String J_OBJ_PLACE_OF_ORIGIN = "placeOfOrigin";
     public static final String J_OBJ_INGREDIENTS = "ingredients";
     public static final String J_OBJ_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        String mainName = "";
        ArrayList<String> list_of_alsoKnownAs = new ArrayList<>();
        ArrayList<String> list_of_ingredients = new ArrayList<>();
        String description = "";
        String placeOfOrigin = "";
        String image = "";

        if (json!= null){
            JSONObject jsonObject  = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject(J_OBJ_NAME);
            mainName = name.getString(J_OBJ_MAIN_NAME);
            JSONArray alsoKnownAs = name.getJSONArray(J_OBJ_AlSO_KNOWN_AS);
            for (int  i= 0;i<alsoKnownAs.length();i++){
                list_of_alsoKnownAs.add(alsoKnownAs.getString(i));
            }
            description = jsonObject.getString(J_OBJ_DESCRIPTION);
            placeOfOrigin = jsonObject.getString(J_OBJ_PLACE_OF_ORIGIN);
            JSONArray ingredients = jsonObject.getJSONArray(J_OBJ_INGREDIENTS);
            for (int i = 0; i <ingredients.length() ; i++) {
                list_of_ingredients.add(ingredients.getString(i));
            }
            image = jsonObject.getString(J_OBJ_IMAGE);
        }

        return new Sandwich(mainName,list_of_alsoKnownAs,placeOfOrigin,description,
                image,list_of_ingredients);
    }
}
