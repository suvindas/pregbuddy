/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pregbuddy.network;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * A sample showing a custom {@link CallAdapter} which adapts the built-in {@link Call} to a custom
 * version whose callback has more granular methods.
 */
public final class ErrorHandlingAdapter {
    /**
     * A callback which offers granular callbacks for various conditions.
     */
    public interface RetroCallback<T> {
        /**
         * On Request Start.
         */
        void onRequestStart();

        /**
         * On Request Start.
         */
        void onRequestStop();

        /**
         * Called for [200, 300) responses.
         */
        void onSuccess(Response<T> response);

        /**
         * Called for 401 responses.
         */
        void onUnauthenticated(Response<?> response);

        /**
         * Called for [400, 500) responses, except 401.
         */
        void onClientError(Response<?> response);

        /**
         * Called for [500, 600) response.
         */
        void onServerError(Response<?> response);

        /**
         * Called for network errors while making the call.
         */
        void onNetworkError(IOException e);

        /**
         * Called for unexpected errors while making the call.
         */
        void onUnexpectedError(Throwable t);
    }

    public interface RetroCall<T> {
        void cancel();

        void enqueue(RetroCallback<T> callback);

        RetroCall<T> clone();


        // Left as an exercise for the reader...
        // TODO MyResponse<T> execute() throws MyHttpException;
    }

    public static class ErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
        @Override
        public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations,
                                     Retrofit retrofit) {
            if (getRawType(returnType) != RetroCall.class) {
                return null;
            }
            if (!(returnType instanceof ParameterizedType)) {
                throw new IllegalStateException(
                        "RetroCall must have generic type (e.g., RetroCall<ResponseBody>)");
            }
            Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
            Executor callbackExecutor = retrofit.callbackExecutor();
            return new ErrorHandlingCallAdapter<>(responseType, callbackExecutor);
        }

        private static final class ErrorHandlingCallAdapter<R> implements CallAdapter<R, RetroCall<R>> {
            private final Type responseType;
            private final Executor callbackExecutor;

            ErrorHandlingCallAdapter(Type responseType, Executor callbackExecutor) {
                this.responseType = responseType;
                this.callbackExecutor = callbackExecutor;
            }

            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public RetroCall<R> adapt(Call<R> call) {
                return new RetroCallAdapter<>(call, callbackExecutor);
            }
        }
    }

    /**
     * Adapts a {@link Call} to {@link RetroCall}.
     */
    static class RetroCallAdapter<T> implements RetroCall<T> {
        private final Call<T> call;
        private final Executor callbackExecutor;
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        RetroCallAdapter(Call<T> call, Executor callbackExecutor) {
            this.call = call;
            this.callbackExecutor = callbackExecutor;
        }

        @Override
        public void cancel() {
            call.cancel();
        }

        @Override
        public void enqueue(final RetroCallback<T> callback) {
            checkNotNull(callback, "RetroCallback cannot be null!");
            callback.onRequestStart();
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, final Response<T> response) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            int code = response.code();
                            if (code >= 200 && code < 300) {
                                callback.onSuccess(response);
                            } else if (code == 401) {
                                callback.onUnauthenticated(response);
                            } else if (code >= 400 && code < 500) {
                                callback.onClientError(response);
                            } else if (code >= 500 && code < 600) {
                                callback.onServerError(response);
                            } else {
                                callback.onUnexpectedError(new RuntimeException("Unexpected response " + response));
                            }
                            callback.onRequestStop();
                        }
                    });

                }

                @Override
                public void onFailure(Call<T> call, final Throwable throwable) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (throwable instanceof IOException) {
                                callback.onNetworkError(new OfflineException("No Internet Connection"));
                            } else {
                                callback.onUnexpectedError(throwable);
                            }
                            callback.onRequestStop();
                        }
                    });
                }
            });
        }

        @Override
        public RetroCall<T> clone() {
            return new RetroCallAdapter<>(call.clone(), callbackExecutor);
        }
    }


}