package com.hoodbluck.authum.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.StringUtils;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
public class AuthumResponse implements Parcelable {

    public enum AuthumResponseStatus {
        SUCCESS("success"),
        USER_INVALID("user_invalid"),
        USER_ALREADY_REGISTERED("user_already_registered"),
        LOGIN_INVALID("login_invalid"),
        UNKNOWN("unknown");

        private String mText;
        AuthumResponseStatus(String text) {
            mText = text;
        }

        public static AuthumResponseStatus getAuthumResponse(String value) {
            AuthumResponseStatus status = UNKNOWN;
            for (AuthumResponseStatus responseStatus : AuthumResponseStatus.values()) {
                if (StringUtils.equalsIgnoreCase(value, responseStatus.mText)) {
                    status = responseStatus;
                    break;
                }
            }
            return status;
        }
    }

    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("value")
    private String value;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.status);
        dest.writeString(this.value);
    }

    public AuthumResponse() {
    }

    protected AuthumResponse(Parcel in) {
        this.code = in.readInt();
        this.status = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<AuthumResponse> CREATOR = new Parcelable.Creator<AuthumResponse>() {
        public AuthumResponse createFromParcel(Parcel source) {
            return new AuthumResponse(source);
        }

        public AuthumResponse[] newArray(int size) {
            return new AuthumResponse[size];
        }
    };
}
