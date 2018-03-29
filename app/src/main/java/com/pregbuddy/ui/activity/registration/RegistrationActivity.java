package com.pregbuddy.ui.activity.registration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.pregbuddy.R;
import com.pregbuddy.base.BaseActivity;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistrationActivity extends BaseActivity implements RegistrationContract.View,
        SheetFragment.Listener, TransferListener, DialogConfirmFragment.DialogButtonClick {
    private static final int DIALOG_ID_AGE_GROUP = 10001;
    private static final int DIALOG_ID_ACCOUNT_TYPE = 10002;
    private static final int DIALOG_ID_IMAGE_PICKER = 10003;
    private static final int DIALOG_ID_INDUSTRY = 10004;
    private static final int DIALOG_ID_COUNTRY = 10005;
    private static final int DIALOG_ID_STATE = 10006;
    private static final int DIALOG_ID_TERMS_CONDITIONS = 10007;
    @BindView(R.id.toolbarTitle)
    TextView _toolbarTitle;
    @BindView(R.id.editAgeGroup)
    TextView _editAgeGroup;
    @BindView(R.id.editAccountType)
    TextView _editAccountType;

    @BindView(R.id.imageProfilePicture)
    ImageView _imageProfilePicture;
    @BindView(R.id.containerProfileImage)
    RelativeLayout _containerProfileImage;
    @BindView(R.id.editFirstName)
    EditText _editFirstName;
    @BindView(R.id.editLastName)
    EditText _editLastName;


    @BindView(R.id.editPhoneNumber)
    EditText _editPhoneNumber;
    @BindView(R.id.checkPhonePrivacy)
    CheckBox _checkPhonePrivacy;
    @BindView(R.id.editEmailAddress)
    EditText _editEmailAddress;
    @BindView(R.id.editPassword)
    EditText _editPassword;
    @BindView(R.id.editConfirmPassword)
    EditText _editConfirmPassword;
    @BindView(R.id.geometricProgressView)
    GeometricProgressView _geometricProgressView;

    @BindView(R.id.editWebsite)
    EditText _editWebsite;
    @BindView(R.id.editCity)
    EditText _editCity;
    @BindView(R.id.editCountry)
    TextView _editCountry;
    @BindView(R.id.editState)
    TextView _editState;
    @BindView(R.id.editIndustry)
    TextView _editIndustry;
    @BindView(R.id.containerOther)
    LinearLayout _containerOther;
    @BindView(R.id.editOther)
    EditText _editOther;
    @BindView(R.id.buttonCreateAccount)
    Button _buttonCreateAccount;
    @BindView(R.id.containerAccountType)
    LinearLayout _containerAccountType;
    @BindView(R.id.containerAdditional)
    LinearLayout _containerAdditional;
    @BindView(R.id.containerState)
    LinearLayout _containerState;

    @BindView(R.id.checkTermsCondition)
    CheckBox _checkTermsCondition;
    @BindView(R.id.textTermsCondition)
    TextView _textTermsCondition;
    @BindView(R.id.containerTermsConditions)
    LinearLayout _containerTermsConditions;

    private RegistrationContract.Presenter mPresenter;
    private int _accountType = -1;
    private TransferUtility _transferUtility;
    private String _filePath = null;
    private String _fileName = UUID.randomUUID().toString();
    private boolean isEdit;
    private int _userId;
    private UserResponse _userDetails;
    private String _country;
    private String _state;
    private String _stateFilename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isEdit = bundle.getBoolean(ARGS_IS_EDIT_PROFILE, false);
        }
        setContentView(R.layout.activity_registration);
        new RegistrationPresenter(RegistrationActivity.this);
    }

    @Override
    protected void onCreateViews(DisplayUtils displayUtils) {
        super.onCreateViews(displayUtils);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        if (isEdit) {
            _containerTermsConditions.setVisibility(View.GONE);
            _userId = _preferenceData.getUserID();
            _userDetails = _preferenceData.getUserDetails();
            _toolbarTitle.setText(getString(R.string.text_title_edit_business_profile));
            _buttonCreateAccount.setText(getString(R.string.text_update_profile));
            _containerAccountType.setVisibility(View.GONE);
            _containerAdditional.setVisibility(View.GONE);
            Glide.with(this).load(_userDetails.getProfilePicture()).apply(RequestOptions.circleCropTransform()).into(_imageProfilePicture);

            _checkPhonePrivacy.setChecked(_userDetails.isPhoneNumberPrivate() == FALSE);
            _editFirstName.setText(_userDetails.getFirstName());
            _editLastName.setText(_userDetails.getLastName());
            _editAgeGroup.setText(_userDetails.getAgeGroup());
            _editWebsite.setText(_userDetails.getWebsite());
            _editCity.setText(_userDetails.getCity());
            if (!TextUtils.isEmpty(_userDetails.getState())) {
                _editState.setText(URLDecoder.decode(_userDetails.getState()));
                _state = URLDecoder.decode(_userDetails.getState());
            }
            if (!TextUtils.isEmpty(_userDetails.getCountry())) {
                _editCountry.setText(URLDecoder.decode(_userDetails.getCountry()));
                _country = URLDecoder.decode(_userDetails.getCountry());
            }
            _editIndustry.setText(_userDetails.getIndustry());
            _editPhoneNumber.setText(_userDetails.getPhone());


        } else {
            _toolbarTitle.setText(getString(R.string.text_title_create_account));
            _buttonCreateAccount.setText(getString(R.string.text_create_account));
            _containerAccountType.setVisibility(View.VISIBLE);
            _containerAdditional.setVisibility(View.VISIBLE);
        }
        _transferUtility = AmazonUtils.getTransferUtility(this);
    }

    @OnClick(R.id.toolbarBack)
    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    @OnClick(R.id.editAgeGroup)
    public void onAgeGroupClicked(View view) {
        SheetFragment bottomSheetFragment = SheetFragment.newInstance(DIALOG_ID_AGE_GROUP, StaticData.getAgeGroup(this),
                getString(R.string.text_select_age_group));
        bottomSheetFragment.show(getSupportFragmentManager(), getString(R.string.text_age_group));
    }

    /**
     * Dialog item click listener
     *
     * @param object
     * @param sheetId
     */
    @Override
    public void itemStateSelected(Object object, int sheetId) {
        switch (sheetId) {
            case DIALOG_ID_AGE_GROUP:
                _editAgeGroup.setText(((SheetListModel) object).getName());
                _editAgeGroup.setError(null);
                break;
            case DIALOG_ID_ACCOUNT_TYPE:
                _accountType = ((SheetListModel) object).getId();
                _editAccountType.setText(((SheetListModel) object).getName());
                _editAccountType.setError(null);
                break;
            case DIALOG_ID_INDUSTRY:
                if (((SheetListModel) object).getId() == Integer.MAX_VALUE) {
                    _containerOther.setVisibility(View.VISIBLE);
                } else {
                    _containerOther.setVisibility(View.GONE);
                }
                _editIndustry.setText(((SheetListModel) object).getName());
                _editIndustry.setError(null);
                break;
            case DIALOG_ID_IMAGE_PICKER:
                if (((SheetListModel) object).getId() == 1) {
                    checkPermission(REQUEST_GALLERY_CODE);
                } else if (((SheetListModel) object).getId() == 2) {
                    checkPermission(REQUEST_CAMERA_CODE);
                } else {
                    _imageProfilePicture.setImageResource(0);
                }
                break;
            case DIALOG_ID_COUNTRY:
                _country = ((LocationModel) object).getName();
                _stateFilename = ((LocationModel) object).getFilename();
                if (TextUtils.isEmpty(((LocationModel) object).getFilename())) {
                    _containerState.setVisibility(View.GONE);
                } else {
                    _containerState.setVisibility(View.VISIBLE);
                }
                _editCountry.setText(((LocationModel) object).getName());
                _editCountry.setError(null);
                _state = null;
                _editState.setText(null);
                break;
            case DIALOG_ID_STATE:
                _state = ((LocationModel) object).getName();
                _editState.setText(((LocationModel) object).getName());
                _editState.setError(null);
                break;
        }
    }

    @OnClick(R.id.editAccountType)
    public void onAccountTypeClicked(View view) {
        SheetFragment bottomSheetFragment = SheetFragment.newInstance(DIALOG_ID_ACCOUNT_TYPE, StaticData.getProfileType(this),
                getString(R.string.text_select_account_type));
        bottomSheetFragment.show(getSupportFragmentManager(), getString(R.string.text_account_type));
    }


    @OnClick(R.id.containerProfileImage)
    public void onProfileImageClicked(View view) {
        SheetFragment bottomSheetFragment = SheetFragment.newInstance(DIALOG_ID_IMAGE_PICKER, StaticData.getImageChooser(this, false),
                getString(R.string.text_select_image));
        bottomSheetFragment.show(getSupportFragmentManager(), getString(R.string.text_image_picker));
    }

    private void checkPermission(int requestID) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestID);
        } else {
            if (requestID == REQUEST_GALLERY_CODE) {
                pickImageFromGallery(this);

            } else if (requestID == REQUEST_CAMERA_CODE) {
                pickImageFromCamera(this);
            } else {
                //Do Nothing
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_GALLERY_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery(this);
                } else {
                    Device.showToastMessage(this, getString(R.string.text_permission_denied), Toast.LENGTH_SHORT);
                }
                break;
            case REQUEST_CAMERA_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromCamera(this);
                } else {
                    Device.showToastMessage(this, getString(R.string.text_permission_denied), Toast.LENGTH_SHORT);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                CropImage.activity(selectedImageUri)
                        .setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1, 1)
                        .start(this);
            } else {
                Device.showToastMessage(this, getResources().getString(R.string.text_try_again_later), Toast.LENGTH_LONG);
            }
        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get(getString(R.string.text_data));
            Uri selectedImageUri = Utils.bitmapToUri(this, imageBitmap);
            if (selectedImageUri != null) {
                CropImage.activity(selectedImageUri)
                        .setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1, 1)
                        .start(this);
            } else {
                Device.showToastMessage(this, getResources().getString(R.string.text_try_again_later), Toast.LENGTH_LONG);
            }

        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                _filePath = String.valueOf(Utils.getPath(this, resultUri));
                if (_filePath != null) {
                    File file = new File(_filePath);
                    _geometricProgressView.setVisibility(View.VISIBLE);
                    TransferObserver observer = _transferUtility.upload(Constants.BUCKET_NAME,
                            String.format(getString(R.string.text_amazon_s3_profile_upload_url), _fileName),
                            file, CannedAccessControlList.PublicRead);
                    observer.setTransferListener(this);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Device.showToastMessage(this, error.getMessage(), Toast.LENGTH_LONG);
            }
        }
    }


    @Override
    public void onAccountTypeError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                showSnackBarError(this, getString(R.string.text_select_account_type), Snackbar.LENGTH_LONG);
                break;
        }
    }

    @Override
    public void onFirstNameError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editFirstName.setError(getString(R.string.text_first_name_empty_error));
                break;
        }
        _editFirstName.requestFocus();
    }

    @Override
    public void onLastNameError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editLastName.setError(getString(R.string.text_last_name_empty_error));
                break;
        }
        _editLastName.requestFocus();
    }


    @Override
    public void onAgeGroupError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                showSnackBarError(this, getString(R.string.text_select_age_group), Snackbar.LENGTH_LONG);
                break;
        }
    }


    @Override
    public void onCountryError(int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                showSnackBarError(this, getString(R.string.text_select_country), Snackbar.LENGTH_LONG);
                break;
        }
    }

    @Override
    public void onStateError(int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                showSnackBarError(this, getString(R.string.text_select_state), Snackbar.LENGTH_LONG);
                break;
        }
    }

    @Override
    public void onCityError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editCity.setError(getString(R.string.text_city_empty_error));
                break;
        }
        _editCity.requestFocus();
    }

    @Override
    public void onIndustryError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                showSnackBarError(this, getString(R.string.text_select_industry), Snackbar.LENGTH_LONG);
                break;
        }
    }

    @Override
    public void onIndustryOtherError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editOther.setError(getString(R.string.text_industry_empty_error));
                break;
        }
        _editOther.requestFocus();
    }

    @Override
    public void onPhoneNumberError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editPhoneNumber.setError(getString(R.string.text_empty_phone_number));
                break;
            case GlobalTypeIntDef.VALIDATION_ERROR:
                _editPhoneNumber.setError(getString(R.string.text_invalid_phone_number));
                break;
        }
        _editPhoneNumber.requestFocus();
    }

    @Override
    public void onEmailError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editEmailAddress.setError(getString(R.string.text_email_empty_error));
                break;
            case GlobalTypeIntDef.VALIDATION_ERROR:
                _editEmailAddress.setError(getString(R.string.text_email_validity_error));
                break;
        }
        _editEmailAddress.requestFocus();
    }

    @Override
    public void onPasswordError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editPassword.setError(getString(R.string.text_password_empty_error));
                break;
            case GlobalTypeIntDef.VALIDATION_ERROR:
                _editPassword.setError(getString(R.string.text_password_validity_error));
                break;
        }
        _editPassword.requestFocus();
    }

    @Override
    public void onConfirmPasswordError(@GlobalTypeIntDef.GlobalType int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                _editConfirmPassword.setError(getString(R.string.text_confirm_password_empty_error));
                break;
            case GlobalTypeIntDef.VALIDATION_ERROR:
                _editConfirmPassword.setError(getString(R.string.text_confirm_password_validity_error));
                break;
            case GlobalTypeIntDef.PASSWORD_MISSMATCH:
                _editConfirmPassword.setError(getString(R.string.text_confirm_password_mismatch_error));
                break;
        }
        _editConfirmPassword.requestFocus();
    }

    @Override
    public void onConditionError(int type) {
        switch (type) {
            case GlobalTypeIntDef.EMPTY_ERROR:
                showSnackBarError(this, getString(R.string.text_error_accept_terms_and_conditions), Snackbar.LENGTH_LONG);
                break;

        }
    }

    @Override
    public void onRequestStart() {
        _progressUtils.showProgress(getString(R.string.text_please_wait));
    }

    @Override
    public void onRequestStop() {
        _progressUtils.dismissProgress();
    }

    @Override
    public void onRequestError(String message) {
        showSnackBarError(this, message, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onCriticalError() {
        showSnackBarError(this, getString(R.string.text_something_went_wrong), Snackbar.LENGTH_LONG);
    }

    @Override
    public void onRequestSuccess(UserResponse response) {
        if (isEdit) {
            LocalBroadcastManager.getInstance(this).
                    sendBroadcast(ReceiverHelper.getIntentForReceiver(getString(R.string.action_profile_updated), null));
            finish();
            _preferenceData.setUserDetails(response);
            showToastMessage(this, getString(R.string.text_profile_update_successful), Toast.LENGTH_LONG);
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(ARGS_USER_ID, response.getUserId());
            ActivityUtils.startActivity(this, VerificationActivity.class, bundle, true);
        }
    }

    @Override
    public void setPresenter(RegistrationContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick(R.id.buttonCreateAccount)
    public void onCreateAccountClicked(View view) {
        if (isEdit) {
            if (_geometricProgressView.getVisibility() != View.VISIBLE) {
                int isPhonePrivate = _checkPhonePrivacy.isChecked() ? FALSE : TRUE;

                String profileImage;
                if (_filePath != null) {
                    profileImage = String.format(getString(R.string.text_amazon_s3_profile_url), _fileName);
                } else {
                    profileImage = _userDetails.getProfilePicture();
                }
                mPresenter.onEditProfileClick(_userId, profileImage, _editFirstName.getText().toString().trim(),
                        _editLastName.getText().toString().trim(),
                        _editAgeGroup.getText().toString().trim(),
                        _editPhoneNumber.getText().toString().trim(), isPhonePrivate,
                        _editWebsite.getText().toString().trim(),
                        _editCity.getText().toString().trim(), _state, _country,
                        _containerOther.getVisibility(), _editOther.getText().toString().trim(), _editIndustry.getText().toString().trim());
            } else {
                showSnackBarError(this, getString(R.string.text_uploading_in_progress), Snackbar.LENGTH_LONG);
            }
        } else {

            if (_geometricProgressView.getVisibility() != View.VISIBLE) {
                int isPhonePrivate = _checkPhonePrivacy.isChecked() ? FALSE : TRUE;

                String profileImage = null;
                if (_filePath != null) {
                    profileImage = String.format(getString(R.string.text_amazon_s3_profile_url), _fileName);
                }
                mPresenter.onRegistrationClick(profileImage, _accountType, _editFirstName.getText().toString().trim(),
                        _editLastName.getText().toString().trim(),
                        _editAgeGroup.getText().toString().trim(),
                        _editPhoneNumber.getText().toString().trim(), isPhonePrivate,
                        _editEmailAddress.getText().toString().trim(),
                        _editPassword.getText().toString().trim(),
                        _editConfirmPassword.getText().toString().trim(),
                        _editWebsite.getText().toString().trim(),
                        _editCity.getText().toString().trim(), _state, _country,
                        _containerOther.getVisibility(), _editOther.getText().toString().trim(), _editIndustry.getText().toString().trim(), _checkTermsCondition.isChecked());
            } else {
                showSnackBarError(this, getString(R.string.text_uploading_in_progress), Snackbar.LENGTH_LONG);
            }
        }


    }

    @Override
    public void onStateChanged(int id, TransferState state) {
        if (state == TransferState.COMPLETED) {
            Glide.with(RegistrationActivity.this).load(_filePath).apply(RequestOptions.circleCropTransform()).into(_imageProfilePicture);
            _geometricProgressView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
        LogUtils.LOGI(this.getClass().getSimpleName(), bytesCurrent + " / " + bytesTotal);
    }

    @Override
    public void onError(int id, Exception ex) {
        _geometricProgressView.setVisibility(View.GONE);
    }

    public void onIndustryClicked(View view) {
        SheetFragment bottomSheetFragment = SheetFragment.newInstance(DIALOG_ID_INDUSTRY, StaticData.getIndustry(this),
                getString(R.string.text_select_industry));
        bottomSheetFragment.show(getSupportFragmentManager(), getString(R.string.text_industry));
    }

    public void onCountryClicked(View view) {
        String counties = readFileFromAssets(this, COUNTRY_PATH);
        Type type = new TypeToken<ArrayList<LocationModel>>() {
        }.getType();
        ArrayList<LocationModel> list = new Gson().fromJson(counties, type);
        SheetFragment bottomSheetFragment = SheetFragment.newInstance(DIALOG_ID_COUNTRY, list,
                getString(R.string.text_select_country));
        bottomSheetFragment.show(getSupportFragmentManager(), getString(R.string.text_select_country));
    }

    public void onStateClicked(View view) {
        if (_stateFilename != null) {
            String state = readFileFromAssets(this, String.format(getString(R.string.text_state_path), _stateFilename));
            Type type = new TypeToken<ArrayList<LocationModel>>() {
            }.getType();
            ArrayList<LocationModel> list = new Gson().fromJson(state, type);
            SheetFragment bottomSheetFragment = SheetFragment.newInstance(DIALOG_ID_STATE, list,
                    getString(R.string.text_select_state));
            bottomSheetFragment.show(getSupportFragmentManager(), getString(R.string.text_select_state));
        }
    }

    public void onAcceptTermsConditions(View view) {
        DialogContentModel dialogContentModel = new DialogContentModel();
        dialogContentModel.setDialogID(DIALOG_ID_TERMS_CONDITIONS);
        dialogContentModel.setDialogObject(null);
        dialogContentModel.setDialogVideo(false);
        dialogContentModel.setDialogTitle(getString(R.string.text_terms_conditions));
        dialogContentModel.setDialogButtonOneText(getString(R.string.text_accept));
        dialogContentModel.setDialogButtonTwoText(getString(R.string.text_cancel));
        dialogContentModel.setDialogHasTwoButton(true);
        dialogContentModel.setDialogMessage(getString(R.string.text_terms_conditions_description));
        dialogContentModel.setDialogCancelable(false);
        DialogFragment dialog = DialogConfirmFragment.newInstance(dialogContentModel);
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onButtonOneClick(@Nullable int dialogID, Object dialogObject) {
        if (dialogID == DIALOG_ID_TERMS_CONDITIONS && _checkTermsCondition != null) {
            _checkTermsCondition.setChecked(true);
        }
    }

    @Override
    public void onButtonTwoClick(@Nullable int dialogID, Object dialogObject) {

    }
}
