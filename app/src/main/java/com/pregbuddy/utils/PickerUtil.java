package com.pregbuddy.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;

public class PickerUtil {

    public static final int REQUEST_GALLERY_CODE = 8001;
    public static final int REQUEST_CAMERA_CODE = 8002;
    public static final int REQUEST_TAKE_GALLERY_VIDEO = 20001;
    public static final int REQUEST_VIDEO_CAPTURE = 20002;
    public static final int REQUEST_PICK_FILE = 20003;

    public static void pickImageFromGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, REQUEST_GALLERY_CODE);
    }

    public static void pickVideoFromGallery(Activity activity) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO);
    }

    public static void pickVideoFromCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 120);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Environment.getExternalStorageDirectory().getPath() + "/capture.mp4");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
        }
    }


    public static void pickFileFromSystem(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        activity.startActivityForResult(intent, REQUEST_PICK_FILE);
    }


    public static void pickImageFromCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            //intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 0);
            intent.putExtra("aspectY", 0);
            activity.startActivityForResult(intent, REQUEST_CAMERA_CODE);
        }
    }
}