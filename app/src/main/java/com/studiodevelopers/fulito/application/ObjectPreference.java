package com.studiodevelopers.fulito.application;

import android.app.Application;

import com.studiodevelopers.fulito.util.ComplexPreferences;

/**
 * Created by Ignacio on 10/02/2015.
 */
public class ObjectPreference extends Application {
    private static final String TAG = "ObjectPreference";
    private ComplexPreferences complexPrefenreces = null;

    @Override
    public void onCreate() {
        super.onCreate();
        complexPrefenreces = ComplexPreferences.getComplexPreferences(getBaseContext(), "data", MODE_PRIVATE);
        android.util.Log.i(TAG, "Preference Created.");
    }

    public ComplexPreferences getComplexPreference() {
        if(complexPrefenreces != null) {
            return complexPrefenreces;
        }
        return null;
    } }