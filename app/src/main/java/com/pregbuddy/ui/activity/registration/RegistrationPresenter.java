package com.pregbuddy.ui.activity.registration;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;


import com.pregbuddy.network.ErrorHandlingAdapter;
import com.pregbuddy.network.RequestCall;
import com.pregbuddy.network.RequestHelper;
import com.pregbuddy.network.utils.RequestParams;
import com.pregbuddy.utils.GlobalTypeIntDef;
import com.pregbuddy.utils.Utils;

import java.io.IOException;
import java.net.URLEncoder;

import retrofit2.Response;

import static android.support.v4.util.Preconditions.checkNotNull;

public class RegistrationPresenter implements RegistrationContract.Presenter, ErrorHandlingAdapter.RetroCallback<UserResponseModel> {


    private final RegistrationContract.View mView;

    public RegistrationPresenter(RegistrationContract.View view) {
        mView = checkNotNull(view, view.getClass().getSimpleName());
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void onRegistrationClick(String profileImage, int accountType, String firstName, String lastName, String ageGroup,
                                    String phoneNumber, int isPhonePrivate, String emailAddress, String password,
                                    String confirmPassword, String website, String city,
                                    String state, String country, int containerOtherVisibility, String other, String industry,boolean isAccepted) {
        if (accountType == -1) {
            mView.onAccountTypeError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(firstName)) {
            mView.onFirstNameError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(lastName)) {
            mView.onLastNameError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(ageGroup)) {
            mView.onAgeGroupError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(country)) {
            mView.onCountryError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(state)) {
            mView.onStateError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(city)) {
            mView.onCityError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (!(containerOtherVisibility == View.VISIBLE) && TextUtils.isEmpty(industry)) {
            mView.onIndustryError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if ((containerOtherVisibility == View.VISIBLE) && TextUtils.isEmpty(other)) {
            mView.onIndustryOtherError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(phoneNumber)) {
            mView.onPhoneNumberError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (!Utils.isValidMobile(phoneNumber)) {
            mView.onPhoneNumberError(GlobalTypeIntDef.VALIDATION_ERROR);
        } else if (TextUtils.isEmpty(emailAddress)) {
            mView.onEmailError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (!Utils.isValidEmail(emailAddress)) {
            mView.onEmailError(GlobalTypeIntDef.VALIDATION_ERROR);
        } else if (TextUtils.isEmpty(password)) {
            mView.onPasswordError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(confirmPassword)) {
            mView.onConfirmPasswordError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (!password.equals(confirmPassword)) {
            mView.onConfirmPasswordError(GlobalTypeIntDef.PASSWORD_MISSMATCH);
        }else if (!isAccepted) {
            mView.onConditionError(GlobalTypeIntDef.EMPTY_ERROR);
        } else {
            RequestParams params = getRequestParamsRegistration(profileImage, accountType, firstName, lastName,
                    ageGroup, phoneNumber, isPhonePrivate, emailAddress, password, website, city, state, country, (containerOtherVisibility == View.VISIBLE) ? other : industry);
            RequestCall.IRegister requestCall = RequestHelper.createRequest(RequestCall.IRegister.class);
            ErrorHandlingAdapter.RetroCall<UserResponseModel> call = requestCall.register(params.getUrlParams());
            call.enqueue(this);
        }


    }

    @Override
    public void onEditProfileClick(int userId, String profileImage, String firstName, String lastName, String ageGroup, String phoneNumber, int isPhonePrivate, String website, String city,
                                   String state, String country, int containerOtherVisibility, String other, String industry) {
        if (TextUtils.isEmpty(firstName)) {
            mView.onFirstNameError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(lastName)) {
            mView.onLastNameError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(ageGroup)) {
            mView.onAgeGroupError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(country)) {
            mView.onCountryError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(state)) {
            mView.onStateError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(city)) {
            mView.onCityError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (!(containerOtherVisibility == View.VISIBLE) && TextUtils.isEmpty(industry)) {
            mView.onIndustryError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if ((containerOtherVisibility == View.VISIBLE) && TextUtils.isEmpty(other)) {
            mView.onIndustryOtherError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (TextUtils.isEmpty(phoneNumber)) {
            mView.onPhoneNumberError(GlobalTypeIntDef.EMPTY_ERROR);
        } else if (!Utils.isValidMobile(phoneNumber)) {
            mView.onPhoneNumberError(GlobalTypeIntDef.VALIDATION_ERROR);
        } else {
            RequestParams params = getRequestParamsEditProfile(userId, profileImage, firstName, lastName,
                    ageGroup, phoneNumber, isPhonePrivate, website, city, state, country, (containerOtherVisibility == View.VISIBLE) ? other : industry);
            RequestCall.IEditProfile requestCall = RequestHelper.createRequest(RequestCall.IEditProfile.class);
            ErrorHandlingAdapter.RetroCall<UserResponseModel> call = requestCall.editProfile(params.getUrlParams());
            call.enqueue(this);
        }
    }

    @Override
    public void onRequestStart() {
        mView.onRequestStart();
    }

    @Override
    public void onRequestStop() {
        mView.onRequestStop();
    }


    @Override
    public void onSuccess(Response<UserResponseModel> response) {
        UserResponseModel model = response.body();
        if (model != null) {
            if (model.isHasError()) {
                mView.onRequestError(model.getMessage());
            } else {
                mView.onRequestSuccess(model.getResponse());
            }
        } else {
            mView.onCriticalError();
        }
    }

    @Override
    public void onUnauthenticated(Response<?> response) {
        mView.onRequestError(response.message());
    }

    @Override
    public void onClientError(Response<?> response) {
        mView.onRequestError(response.message());
    }

    @Override
    public void onServerError(Response<?> response) {
        mView.onRequestError(response.message());
    }

    @Override
    public void onNetworkError(IOException e) {
        mView.onRequestError(e.getMessage());
    }

    @Override
    public void onUnexpectedError(Throwable t) {
        mView.onRequestError(t.getMessage());
    }

    @NonNull
    private RequestParams getRequestParamsRegistration(String profileImage, int accountType, String firstName,
                                                       String lastName, String ageGroup, String phoneNumber, int isPhonePrivate,
                                                       String emailAddress, String password, String website, String city,
                                                       String state, String country, String industry) {

        RequestParams params = new RequestParams();
        params.put(PARAMS_PROFILE_PICTURE, profileImage);
        params.put(PARAMS_USER_TYPE, accountType);
        params.put(PARAMS_FIRST_NAME, firstName);
        params.put(PARAMS_LAST_NAME, lastName);
        params.put(PARAMS_AGE_GROUP, ageGroup);
        params.put(PARAMS_PHONE, phoneNumber);
        params.put(PARAMS_PHONE_NUMBER_HIDDEN, isPhonePrivate);
        params.put(PARAMS_EMAIL, emailAddress);
        params.put(PARAMS_PASSWORD, password);
        params.put(PARAMS_WEBSITE, website);
        params.put(PARAMS_CITY, city);
        if (!TextUtils.isEmpty(state)) {
            params.put(PARAMS_STATE, URLEncoder.encode(state));
        }
        if (!TextUtils.isEmpty(country)) {
            params.put(PARAMS_COUNTRY, URLEncoder.encode(country));
        }
        params.put(PARAMS_INDUSTRY, industry);
        return params;
    }

    @NonNull
    private RequestParams getRequestParamsEditProfile(int userId, String profileImage, String firstName, String lastName, String ageGroup, String phoneNumber, int isPhonePrivate, String website, String city,
                                                      String state, String country, String industry) {
        RequestParams params = new RequestParams();
        params.put(PARAMS_USER_ID, userId);
        params.put(PARAMS_PROFILE_PICTURE, profileImage);
        params.put(PARAMS_FIRST_NAME, firstName);
        params.put(PARAMS_LAST_NAME, lastName);
        params.put(PARAMS_AGE_GROUP, ageGroup);
        params.put(PARAMS_PHONE, phoneNumber);
        params.put(PARAMS_PHONE_NUMBER_HIDDEN, isPhonePrivate);
        params.put(PARAMS_WEBSITE, website);
        params.put(PARAMS_CITY, city);
        params.put(PARAMS_STATE, URLEncoder.encode(state));
        params.put(PARAMS_COUNTRY, URLEncoder.encode(country));
        params.put(PARAMS_INDUSTRY, industry);
        return params;
    }

}
