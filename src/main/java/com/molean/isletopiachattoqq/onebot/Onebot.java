package com.molean.isletopiachattoqq.onebot;

import com.google.gson.*;
import it.unimi.dsi.fastutil.Pair;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Onebot extends TimerTask implements WebSocket.Listener {


    private Gson gson = new Gson();

    private final HttpClient httpClient = HttpClient.newBuilder()
            .build();

    public WebSocket webSocket;

    public void reconnect() {
        try {
            CompletableFuture<WebSocket> webSocketCompletableFuture = httpClient.newWebSocketBuilder().buildAsync(new URI("ws://127.0.0.1:3001"), this);
            webSocketCompletableFuture.whenComplete((webSocket, throwable) -> this.webSocket = webSocket);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        reconnect();
        return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        reconnect();
        WebSocket.Listener.super.onError(webSocket, error);
    }

    public void push(String message) {
        push("824852529", message);
    }


    @SafeVarargs
    public final void perform(String type, Pair<String, JsonElement>... pairs) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", type);
        JsonObject params = new JsonObject();
        for (Pair<String, JsonElement> pair : pairs) {
            params.add(pair.left(), pair.right());
        }
        jsonObject.add("params", params);
        webSocket.sendText(gson.toJson(jsonObject), true);
    }

    public void push(String groupId, JsonObject... objs) {
        JsonArray jsonElements = new JsonArray();
        for (JsonObject obj : objs) {
            jsonElements.add(obj);
        }
        perform("send_group_msg",
                Pair.of("group_id", new JsonPrimitive(groupId)),
                Pair.of("message", jsonElements));
    }

    public void pushCombined(String groupId, JsonObject... objects) {
        JsonArray jsonElements = new JsonArray();
        for (JsonObject obj : objects) {
            jsonElements.add(obj);
        }
        perform("send_group_forward_msg",
                Pair.of("group_id", new JsonPrimitive(groupId)),
                Pair.of("messages", jsonElements)
        );
    }


    public void push(String groupId, String message) {
        push(groupId, Message.text(message));
    }


    @Override
    public void run() {
        if (webSocket == null || webSocket.isInputClosed() || webSocket.isOutputClosed()) {
            reconnect();
        }
    }
}
