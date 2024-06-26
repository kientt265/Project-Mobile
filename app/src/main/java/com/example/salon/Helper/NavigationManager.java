package com.example.salon.Helper;
import android.app.Activity;
import android.content.Intent;

import com.example.salon.Activity.CartActivity;
import com.example.salon.Activity.HistoryActivity;
import com.example.salon.Activity.HomeActivity;
import com.example.salon.Activity.NotiActivity;
import com.example.salon.Activity.ProfileActivity;
import com.example.salon.Activity.ShoppingActivity;
import com.example.salon.Booking.Booking_sel_locale;

public class NavigationManager {
    public static void navigateToNotifications(Activity currentActivity) {
        Intent intent = new Intent(currentActivity, NotiActivity.class);
        currentActivity.startActivity(intent);
    }
    public static void navigateToHome(Activity currentActivity) {
        Intent intent = new Intent(currentActivity, ShoppingActivity.class);
        currentActivity.startActivity(intent);
    }
    public static void navigateToCart(Activity currentActivity) {
        Intent intent = new Intent(currentActivity, CartActivity.class);
        currentActivity.startActivity(intent);
    }
    public static void navigateToHistory(Activity currentActivity) {
        Intent intent = new Intent(currentActivity, Booking_sel_locale.class);
        currentActivity.startActivity(intent);
    }
    public  static  void navigateToProfile (Activity currenActivity) {
        Intent intent = new Intent(currenActivity, ProfileActivity.class);
        currenActivity.startActivity(intent);
    }

}
