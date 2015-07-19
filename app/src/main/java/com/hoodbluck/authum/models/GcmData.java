package com.hoodbluck.authum.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 7/19/15.
 *
 * @author Skye Schneider
 */
public class GcmData implements Parcelable {
    @SerializedName("message")
    private String message;
    @SerializedName("clientId")
    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.clientId);
    }

    public GcmData() {
    }

    protected GcmData(Parcel in) {
        this.message = in.readString();
        this.clientId = in.readString();
    }

    public static final Parcelable.Creator<GcmData> CREATOR = new Parcelable.Creator<GcmData>() {
        public GcmData createFromParcel(Parcel source) {
            return new GcmData(source);
        }

        public GcmData[] newArray(int size) {
            return new GcmData[size];
        }
    };
}
