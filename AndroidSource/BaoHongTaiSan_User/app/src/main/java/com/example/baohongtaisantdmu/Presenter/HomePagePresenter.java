package com.example.baohongtaisantdmu.Presenter;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.View.HomePageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePagePresenter {
    private HomePageView homePageView;

    public HomePagePresenter(HomePageView homePageView) {
        this.homePageView = homePageView;
    }



}
