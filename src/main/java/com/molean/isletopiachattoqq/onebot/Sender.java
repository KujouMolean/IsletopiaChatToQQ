package com.molean.isletopiachattoqq.onebot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Sender {
    @SerializedName("user_id")
    private String userId;
    private String nickname;
    private String card;
    private String sex;
    private int age;
    private String area;
    private String level;
    private String role;
    private String title;
}
