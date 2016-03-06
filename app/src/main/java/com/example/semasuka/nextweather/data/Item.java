package com.example.semasuka.nextweather.data;

import org.json.JSONObject;

/**
 * Created by semasuka on 06/03/2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
         condition = new Condition();
         condition.populate(data.optJSONObject("condition"));

    }

}