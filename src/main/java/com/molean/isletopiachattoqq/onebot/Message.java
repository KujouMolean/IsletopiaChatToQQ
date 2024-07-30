package com.molean.isletopiachattoqq.onebot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.Pair;

public class Message {

    private static JsonObject msg(String type, JsonObject data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", type);
        jsonObject.add("data", data);
        return jsonObject;
    }

    @SafeVarargs
    private static JsonObject fromPair(Pair<String, String>... pairs) {
        JsonObject jsonObject = new JsonObject();
        for (Pair<String, String> pair : pairs) {
            jsonObject.addProperty(pair.left(), pair.right());
        }
        return jsonObject;
    }


    public static JsonObject face(String id) {
        return msg("face", fromPair(Pair.of("id", id)));
    }

    public static JsonObject text(String text) {
        return msg("text", fromPair(Pair.of("text", text)));
    }

    public static JsonObject image(String file) {
        return msg("image", fromPair(Pair.of("file", file)));
    }

    public static JsonObject flash(String file) {
        return msg("image", fromPair(
                Pair.of("file", file),
                Pair.of("type", "flash")
        ));
    }

    public static JsonObject record(String file) {
        return msg("record", fromPair(Pair.of("file", file)));
    }

    public static JsonObject video(String file) {
        return msg("video", fromPair(Pair.of("file", file)));
    }

    public static JsonObject at(String qq) {
        return msg("at", fromPair(Pair.of("qq", qq)));
    }

    public static JsonObject rps() {
        return msg("rps", fromPair());
    }

    public static JsonObject dice() {
        return msg("dice", fromPair());
    }

//    public static JsonObject shake() {
//        return msg("shake", fromPair());
//    }

    public static Pair<String, String> POKE = Pair.of("1", "-1");
    public static Pair<String, String> SHOW_LOVE = Pair.of("2", "-1");
    public static Pair<String, String> LIKE = Pair.of("3", "-1");
    public static Pair<String, String> HEARTBROKEN = Pair.of("4", "-1");
    public static Pair<String, String> SIX_SIX_SIX = Pair.of("5", "-1");
    public static Pair<String, String> FANG_DA_ZHAO = Pair.of("6", "-1");
    public static Pair<String, String> BAO_BEI_QIU = Pair.of("126", "2011");//SVIP
    public static Pair<String, String> ROSE = Pair.of("126", "2007");//SVIP
    public static Pair<String, String> ZHAO_HUAN_SHU = Pair.of("2", "2006");//SVIP
    public static Pair<String, String> RANG_NI_PI = Pair.of("2", "2006");//SVIP
    public static Pair<String, String> JIE_YIN = Pair.of("2", "2005");//SVIP
    public static Pair<String, String> SHOU_LEI = Pair.of("2", "2004");//SVIP
    public static Pair<String, String> GOU_YIN = Pair.of("2", "2003");
    public static Pair<String, String> ZHUA_YI_XIA = Pair.of("2", "2001");  //SVIP
    public static Pair<String, String> SUI_PING = Pair.of("2", "2002");//SVIP
    public static Pair<String, String> QIAO_MEN = Pair.of("2", "2002");//SVIP

    public static JsonObject poke(Pair<String, String> data) {
        return msg("poke", fromPair(Pair.of("type", data.left()), Pair.of("id", data.right())));
    }

//    public static JsonObject share(String url, String title) {
//        return msg("share", fromPair(Pair.of("url", url), Pair.of("title", title)));
//    }

//    public static JsonObject contact(String qq) {
//        return msg("contact", fromPair(Pair.of("type", "qq"), Pair.of("id", qq)));
//    }

//    public static JsonObject group(String id) {
//        return msg("contact", fromPair(Pair.of("type", "group"), Pair.of("id", id)));
//    }

    public static JsonObject location(String lat, String lon) {
        return msg("location", fromPair(Pair.of("lat", lat), Pair.of("lon", lon)));
    }

//    public static JsonObject music(String type, String id) {
//        return msg("music", fromPair(Pair.of("type", type), Pair.of("id", id)));
//    }
//
//    public static JsonObject musicCustom(String url, String audio, String title) {
//        return msg("music", fromPair(
//                Pair.of("type", "custom"),
//                Pair.of("url", url),
//                Pair.of("audio", audio),
//                Pair.of("title", title)
//        ));
//    }

    public static JsonObject reply(String id) {
        return msg("reply", fromPair(Pair.of("id", id)));
    }

    public static JsonObject node(String id) {
        return msg("node", fromPair(Pair.of("id", id)));
    }

    public static JsonObject node(String userId, String nickname, JsonObject ...objs) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uin", userId);
        jsonObject.addProperty("name", nickname);
        JsonArray jsonElements = new JsonArray();
        for (JsonObject obj : objs) {
            jsonElements.add(obj);
        }
        jsonObject.add("content", jsonElements);
        return msg("node", jsonObject);
    }

//    public static JsonObject xml(String data) {
//        return msg("xml", fromPair(Pair.of("data", id)));
//    }

    public static JsonObject json(String json) {
        return msg("json", fromPair(Pair.of("data", json)));
    }
}
