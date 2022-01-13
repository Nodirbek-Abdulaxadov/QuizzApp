package com.example.quizzapp;

import android.icu.lang.UProperty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This class for question object model
 */
public class Question {
    //question Text prop
    String questionText;

    //answers list
    List<String> answers = new ArrayList<>();

    //correct answer index
    int correctAnswerId;
}
