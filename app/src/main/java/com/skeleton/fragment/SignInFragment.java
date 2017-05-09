package com.skeleton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.CommonResponse;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;

import java.util.HashMap;

/**
 * Registered User -  Sign In
 */

public class SignInFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = SignUpFragment.class.getName();
    private MaterialEditText editTextEmail, editTextPassword;
    private Button buttonSignIn, buttonSignInWithFB;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        init(view);
        return view;
    }

    /**
     * Initializes the views and variables
     *
     * @param view : rootView of the fragment
     */
    private void init(final View view) {
        editTextEmail = (MaterialEditText) view.findViewById(R.id.frag_signin_et_email);
        editTextPassword = (MaterialEditText) view.findViewById(R.id.frag_signin_et_pass);
        buttonSignIn = (Button) view.findViewById(R.id.frag_signin_bt_sign_in);
        buttonSignIn.setOnClickListener(this);

    }

    /**
     * returns new instance of {@link SignInFragment}
     *
     * @return : instance of {@link SignInFragment}
     */
    public static SignInFragment getInstance() {
        return new SignInFragment();

    }

    /**
     * Implements methods of OnClickListener
     *
     * @param v : view clicked
     */
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.frag_signin_bt_sign_in:
                if (validateData()) {
                    signInUser();
                }
                break;
            default:
        }

    }

    /**
     * SignIn user
     */
    private void signInUser() {

        HashMap<String, String> hashMap = new CommonParams.Builder()
                .add(KEY_FRAGMENT_EMAIL, editTextEmail.getText().toString().trim())
                .add(KEY_FRAGMENT_PASSWORD, editTextPassword.getText().toString().trim())
                .add(KEY_FRAGMENT_DEVICE_TYPE, VALUE_FRAGMENT_DEVICE_TYPE)
                .add(KEY_FRAGMENT_LANGUAGE, VALUE_FRAGMENT_LANGUAGE)
                .add(KEY_FRAGMENT_DEVICE_TOKEN, VALUE_RAGMENT_DEVICE_TOKEN)
                .add(KEY_FRAGMENT_FLUSH_TOKENS, true)
                .add(KEY_FRAGMENT_APP_VERSION, VALUE_FRAGMENT_APP_VERSION).build().getMap();

        ApiInterface apiInterface = RestClient.getApiInterface();
        apiInterface.userLogin(null, hashMap).enqueue(new ResponseResolver<CommonResponse>(getContext(), true, true) {
            @Override
            public void success(final CommonResponse commonResponse) {
                Toast.makeText(getContext(), commonResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if ("200".equals(commonResponse.getStatusCode())) {
                    clearEditText(editTextEmail, editTextPassword);
                }
            }

            @Override
            public void failure(final APIError error) {
                Log.d(TAG, "failure: " + error.getMessage());
                Log.d(TAG, "failure: " + error.getStatusCode());
            }
        });
    }


    /**
     * Validate fields for sign up
     *
     * @return true: vaidated,else false
     */
    private boolean validateData() {
        if (!ValidateEditText.checkEmail(editTextEmail)) {
            return false;
        }
        if (!ValidateEditText.checkPassword(editTextPassword, false)) {
            return false;
        }

        return true;
    }

    /**
     * Clear the string in the editext
     *
     * @param editText : multiple edittexts to be cleared
     */
    public static void clearEditText(final EditText... editText) {
        for (EditText editText1 : editText) {
            editText1.setText("");

        }
    }
}