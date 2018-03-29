package com.pregbuddy.network;


import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public class RequestCall {

   /* *//**
     *
     *//*
    public interface IChangePassword {
        @FormUrlEncoded
        @POST(RequestURL.API_CHANGE_PASSWORD)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> changePassword(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IBlockedUsers {
        @FormUrlEncoded
        @POST(RequestURL.API_BLOCKED_USER)
        ErrorHandlingAdapter.RetroCall<CommonResponseModel> blockedUsers(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IBlockUnblockUser {
        @FormUrlEncoded
        @POST(RequestURL.API_BLOCK_USER)
        ErrorHandlingAdapter.RetroCall<SingleCommonResponseModel> blockUnBlockUser(@FieldMap Map<String, String> fields);
    }


    *//**
     * Save Follow API Call
     *//*
    public interface IFeedRespondReport {
        @FormUrlEncoded
        @POST(RequestURL.API_FEED_RESPOND_REPORT)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> feedRespondReport(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IFeedReport {
        @FormUrlEncoded
        @POST(RequestURL.API_FEED_REPORT)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> feedReport(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IFeedDetails {
        @FormUrlEncoded
        @POST(RequestURL.API_FEED_DETAILS)
        ErrorHandlingAdapter.RetroCall<FeedDetailsResponseModel> feedDetails(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IViewFeed {
        @FormUrlEncoded
        @POST(RequestURL.API_FEED_VIEW)
        ErrorHandlingAdapter.RetroCall<SingleCommonResponseModel> viewFeed(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IEditFeed {
        @FormUrlEncoded
        @POST(RequestURL.API_EDIT_FEED)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> editFeed(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IBusinessData {
        @FormUrlEncoded
        @POST(RequestURL.API_BUSINESS_DATA)
        ErrorHandlingAdapter.RetroCall<SingleCommonResponseModel> getBusinessData(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IUserData {
        @FormUrlEncoded
        @POST(RequestURL.API_USER_DATA)
        ErrorHandlingAdapter.RetroCall<SingleCommonResponseModel> getUserData(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface ISaveReply {
        @FormUrlEncoded
        @POST(RequestURL.API_REPLY_COMMENT)
        ErrorHandlingAdapter.RetroCall<SingleCommentsResponseModel> saveReply(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IRemoveComment {
        @FormUrlEncoded
        @POST(RequestURL.API_REMOVE_COMMENT)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> removeComment(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface ISaveRespond {
        @FormUrlEncoded
        @POST(RequestURL.API_SAVE_RESPOND)
        ErrorHandlingAdapter.RetroCall<SingleRespondResponseModel> saveRespond(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface ISearch {
        @FormUrlEncoded
        @POST(RequestURL.API_SEARCH)
        ErrorHandlingAdapter.RetroCall<FeedListResponseModel> search(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface ISearchUsers {
        @FormUrlEncoded
        @POST(RequestURL.API_SEARCH_USERS)
        ErrorHandlingAdapter.RetroCall<CommonResponseModel> search(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IAcceptRejectConnection {
        @FormUrlEncoded
        @POST(RequestURL.API_ACCEPT_REJECT_CONNECTION)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> acceptRejectConnection(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IGetNotifications {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_NOTIFICATION)
        ErrorHandlingAdapter.RetroCall<NotificationResponseModel> getNotifications(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface IDeleteFeed {
        @FormUrlEncoded
        @POST(RequestURL.API_DELETE_FEED)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> deleteFeed(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface ISaveConnection {
        @FormUrlEncoded
        @POST(RequestURL.API_SAVE_CONNECTION)
        ErrorHandlingAdapter.RetroCall<SingleCommonResponseModel> saveConnection(@FieldMap Map<String, String> fields);
    }

    *//**
     * Save Follow API Call
     *//*
    public interface ISaveFollow {
        @FormUrlEncoded
        @POST(RequestURL.API_SAVE_FOLLOW)
        ErrorHandlingAdapter.RetroCall<SingleCommonResponseModel> saveFollow(@FieldMap Map<String, String> fields);
    }

    *//**
     * Get Business Details API Call
     *//*
    public interface IGetBusinessDetails {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_BUSINESS_DETAILS)
        ErrorHandlingAdapter.RetroCall<SingleBusinessResponseModel> getBusinessDetails(@FieldMap Map<String, String> fields);
    }

    *//**
     * Get Following API Call
     *//*
    public interface IGetFollowing {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_FOLLOWING)
        ErrorHandlingAdapter.RetroCall<CommonResponseModel> getFollowing(@FieldMap Map<String, String> fields);
    }

    *//**
     * Get Followers API Call
     *//*
    public interface IGetFollowers {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_FOLLOWERS)
        ErrorHandlingAdapter.RetroCall<CommonResponseModel> getFollowers(@FieldMap Map<String, String> fields);
    }

    *//**
     * Get Connections API Call
     *//*
    public interface IGetConnections {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_CONNECTIONS)
        ErrorHandlingAdapter.RetroCall<CommonResponseModel> getConnections(@FieldMap Map<String, String> fields);
    }

    *//**
     * Get Liked Users API Call
     *//*
    public interface IGetLikedUsers {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_LIKED_USERS)
        ErrorHandlingAdapter.RetroCall<CommonResponseModel> getLikedUsers(@FieldMap Map<String, String> fields);
    }

    *//**
     * Get User API Call
     *//*
    public interface IGetUser {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_PROFILE)
        ErrorHandlingAdapter.RetroCall<UserResponseModel> getProfile(@FieldMap Map<String, String> fields);
    }

    *//**
     * Update Business API Call
     *//*
    public interface IUpdateBusiness {
        @FormUrlEncoded
        @POST(RequestURL.API_UPDATE_BUSINESS)
        ErrorHandlingAdapter.RetroCall<BusinessResponseModel> updateBusiness(@FieldMap Map<String, String> fields);
    }

    *//**
     * Edit Profile API Call
     *//*
    public interface IEditProfile {
        @FormUrlEncoded
        @POST(RequestURL.API_EDIT_PROFILE)
        ErrorHandlingAdapter.RetroCall<UserResponseModel> editProfile(@FieldMap Map<String, String> fields);
    }

    *//**
     * Web Content API Call FAQ, About Us, Privacy
     *//*
    public interface IWebContent {
        @FormUrlEncoded
        @POST(RequestURL.API_CONTENT)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> getContent(@FieldMap Map<String, String> fields);
    }

    *//**
     * Like Feed API Call
     *//*
    public interface ILikeFeed {
        @FormUrlEncoded
        @POST(RequestURL.API_LIKE_FEED)
        ErrorHandlingAdapter.RetroCall<FeedLikeResponseModel> likeFeed(@FieldMap Map<String, String> fields);
    }

    *//**
     * Business List API Call
     *//*
    public interface IBusinessList {
        @FormUrlEncoded
        @POST(RequestURL.API_BUSINESS_LIST)
        ErrorHandlingAdapter.RetroCall<BusinessResponseModel> businessList(@FieldMap Map<String, String> fields);
    }

    *//**
     * Add Feed API Call
     *//*
    public interface IAddFeed {
        @FormUrlEncoded
        @POST(RequestURL.API_ADD_FEED)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> addFeed(@FieldMap Map<String, String> fields);
    }

    *//**
     * Add Business API Call
     *//*
    public interface IAddBusiness {
        @FormUrlEncoded
        @POST(RequestURL.API_ADD_BUSINESS)
        ErrorHandlingAdapter.RetroCall<BusinessResponseModel> addBusiness(@FieldMap Map<String, String> fields);
    }


    *//**
     * Feeds API Call
     *//*
    public interface IFeeds {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_FEEDS)
        ErrorHandlingAdapter.RetroCall<FeedListResponseModel> feeds(@FieldMap Map<String, String> fields);
    }

    *//**
     * Forgot Password API Call
     *//*
    public interface IForgotPassword {
        @FormUrlEncoded
        @POST(RequestURL.API_FORGOT_PASSWORD)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> forgotPassword(@FieldMap Map<String, String> fields);
    }

    *//**
     * Resend Code API Call
     *//*
    public interface IResendCode {
        @FormUrlEncoded
        @POST(RequestURL.API_RESEND_OTP)
        ErrorHandlingAdapter.RetroCall<StringResponseModel> resend(@FieldMap Map<String, String> fields);
    }

    *//**
     * Verify Code API Call
     *//*
    public interface IVerifyCode {
        @FormUrlEncoded
        @POST(RequestURL.API_VERIFY_OTP)
        ErrorHandlingAdapter.RetroCall<UserResponseModel> verify(@FieldMap Map<String, String> fields);
    }

    *//**
     * Register User API Call
     *//*
    public interface IRegister {
        @FormUrlEncoded
        @POST(RequestURL.API_REGISTRATION_URL)
        ErrorHandlingAdapter.RetroCall<UserResponseModel> register(@FieldMap Map<String, String> fields);
    }

    *//**
     * Login User API Call
     *//*
    public interface ILogin {
        @FormUrlEncoded
        @POST(RequestURL.API_LOGIN_URL)
        ErrorHandlingAdapter.RetroCall<UserResponseModel> login(@FieldMap Map<String, String> fields);
    }

    *//**
     * App Data API Call
     *//*
    public interface IAppData {
        @FormUrlEncoded
        @POST(RequestURL.API_APP_DATA)
        ErrorHandlingAdapter.RetroCall<AppDataResponseModel> appData(@FieldMap Map<String, String> fields);
    }

    *//**
     * Feed Comments API Call
     *//*
    public interface IGetFeedComments {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_COMMENTS)
        ErrorHandlingAdapter.RetroCall<FeedCommentsResponseModel> getComments(@FieldMap Map<String, String> fields);
    }

    *//**
     * Add Comments API Call
     *//*
    public interface IAddFeedComments {
        @FormUrlEncoded
        @POST(RequestURL.API_ADD_COMMENTS)
        ErrorHandlingAdapter.RetroCall<AddCommentsResponseModel> AddComments(@FieldMap Map<String, String> fields);
    }


    *//**
     * Respond API Call
     *//*
    public interface IGetRespond {
        @FormUrlEncoded
        @POST(RequestURL.API_GET_RESPOND)
        ErrorHandlingAdapter.RetroCall<RespondResponseModel> getRespond(@FieldMap Map<String, String> fields);
    }

    *//**
     * Add Respond API Call
     *//*
    public interface IAddRespond {
        @FormUrlEncoded
        @POST(RequestURL.API_ADD_RESPOND)
        ErrorHandlingAdapter.RetroCall<AddCommentsResponseModel> addRespond(@FieldMap Map<String, String> fields);
    }*/
}
