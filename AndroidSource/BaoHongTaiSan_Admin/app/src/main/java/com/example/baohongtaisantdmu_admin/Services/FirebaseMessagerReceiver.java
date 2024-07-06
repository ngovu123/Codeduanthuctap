package com.example.baohongtaisantdmu_admin.Services;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.baohongtaisantdmu_admin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Objects;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class FirebaseMessagerReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Map<String, String> stringMap = message.getData();
            if (Objects.equals(stringMap.get("LoaiNoti"), "UserToAdmin")) {
                String title = stringMap.get("TenP") + ": " + stringMap.get("TenTS");
                String body = "";
                if (Integer.parseInt(Objects.requireNonNull(stringMap.get("TinhTrang"))) == 1) {
                    body = "Hư hỏng nhẹ (Minor)";
                } else if (Integer.parseInt(Objects.requireNonNull(stringMap.get("TinhTrang"))) == 2) {
                    body = "Hư hỏng trung bình (Moderate)";
                } else if (Integer.parseInt(Objects.requireNonNull(stringMap.get("TinhTrang"))) == 3) {
                    body = "Hư hỏng nghiêm trọng (Severe)";
                } else if (Integer.parseInt(Objects.requireNonNull(stringMap.get("TinhTrang"))) == 4) {
                    body = "Hư hỏng hoàn toàn (Critical)";
                }
                showNotification(stringMap.get("MaND"), title, body);
            }


        }
    }

    private void showNotification(String MaND, String title, String body) {
       /* Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), MaND)
                .setSmallIcon(R.drawable.logo_tdmu_2)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000})
                .setOnlyAlertOnce(true);
        //.setContentIntent(pendingIntent);
        builder = builder.setContent(customNotification(title, body));
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(MaND, "test", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0, builder.build());

    }


    public RemoteViews customNotification(String title, String body) {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.custom_notification);
        remoteViews.setTextViewText(R.id.txtTitle_noti, title);
        remoteViews.setTextViewText(R.id.txtBody_noti, body);
        return remoteViews;
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}
