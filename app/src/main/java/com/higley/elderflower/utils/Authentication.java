package com.higley.elderflower.utils;

import android.app.Activity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class Authentication {
    private static FirebaseAuth auth = FirebaseAuth.getInstance();

    public static String getCurrentUid() {
        return auth.getCurrentUser().getUid();
    }

    public static void ensureAuthenticated(Activity source) {
        //TODO enable other providers
        if (auth.getCurrentUser() == null) {
            source.startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder()
                    .setProviders(Arrays.asList(
                            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                    .build(),
                    RC_SIGN_IN);
        }
    }
}
