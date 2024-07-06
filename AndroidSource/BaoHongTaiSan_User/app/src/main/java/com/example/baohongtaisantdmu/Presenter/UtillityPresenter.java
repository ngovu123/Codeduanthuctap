package com.example.baohongtaisantdmu.Presenter;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.View.UtillityView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UtillityPresenter {
    private UtillityView utillityView;

    public UtillityPresenter(UtillityView utillityView) {
        this.utillityView = utillityView;
    }

    public void _Update_Status(Boolean Status){
        Logged.getInstance().getNguoiDung().setStatus(Status);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(Logged.getInstance().getNguoiDung().getMaND()+"").setValue(Logged.getInstance().getNguoiDung()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                utillityView._Update_Status_Success();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                utillityView._Show_Error("_Update_Status", e.getMessage());
            }
        });
    }


}
