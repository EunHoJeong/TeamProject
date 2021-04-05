package com.example.teamproject;

import java.util.Comparator;

public class DescendingSort implements Comparator<Reservation> {


    @Override
    public int compare(Reservation a, Reservation b) {
        return b.getDate().compareTo(a.getDate());
    }
}
