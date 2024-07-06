package com.example.baohongtaisantdmu.Model;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class Logged {
    private NguoiDung nguoiDung;

    private static Logged instance;

    private GoogleSignInOptions googleSignInOptions;


    private Logged() {
        this.googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("390576091039-ubdr4sg6lo3uh9i42ofl4npue6m2gctf.apps.googleusercontent.com")
                .requestEmail()
                .requestProfile()
                .build();
    }

    public static Logged getInstance() {
        if (instance == null) {
            instance = new Logged();
        }
        return instance;
    }






    public static void setInstance(Logged instance) {
        Logged.instance = instance;
    }

    public void setLoggedInUser(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }


    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }


    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }



    public GoogleSignInOptions getGoogleSignInOptions() {
        return googleSignInOptions;
    }





    
}
