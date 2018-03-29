package com.pregbuddy.ui.activity.registration;


import com.pregbuddy.base.BasePresenter;
import com.pregbuddy.base.BaseView;
import com.pregbuddy.utils.GlobalTypeIntDef;
public interface RegistrationContract {

    interface View extends BaseView<Presenter> {

        void onAccountTypeError(@GlobalTypeIntDef.GlobalType int type);

        void onFirstNameError(@GlobalTypeIntDef.GlobalType int type);

        void onLastNameError(@GlobalTypeIntDef.GlobalType int type);

        void onAgeGroupError(@GlobalTypeIntDef.GlobalType int type);

        void onCountryError(@GlobalTypeIntDef.GlobalType int type);

        void onStateError(@GlobalTypeIntDef.GlobalType int type);

        void onCityError(@GlobalTypeIntDef.GlobalType int type);

        void onIndustryError(@GlobalTypeIntDef.GlobalType int type);

        void onIndustryOtherError(@GlobalTypeIntDef.GlobalType int type);

        void onPhoneNumberError(@GlobalTypeIntDef.GlobalType int type);

        void onEmailError(@GlobalTypeIntDef.GlobalType int type);

        void onPasswordError(@GlobalTypeIntDef.GlobalType int type);

        void onConfirmPasswordError(@GlobalTypeIntDef.GlobalType int type);

        void onConditionError(@GlobalTypeIntDef.GlobalType int type);

        void onRequestStart();

        void onRequestStop();

        void onRequestError(String message);

        void onCriticalError();

        void onRequestSuccess(UserResponse response);


    }

    interface Presenter extends BasePresenter {
        void onRegistrationClick(String profileImage, int accountType, String firstName, String lastName, String ageGroup, String phoneNumber, int isPhonePrivate, String emailAddress, String password,
                                 String confirmPassword, String website, String city, String state, String country, int containerOtherVisibility, String other, String industry, boolean isAccepted);

        void onEditProfileClick(int userId, String profileImage, String firstName, String lastName, String ageGroup, String phoneNumber, int isPhonePrivate, String website, String city,
                                String state, String country,
                                int containerOtherVisibility, String other, String industry);


    }
}
