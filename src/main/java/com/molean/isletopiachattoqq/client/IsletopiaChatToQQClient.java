package com.molean.isletopiachattoqq.client;

import com.molean.isletopiachattoqq.onebot.Onebot;
import net.fabricmc.api.ClientModInitializer;

import java.util.Timer;

public class IsletopiaChatToQQClient implements ClientModInitializer {
    public static IsletopiaChatToQQClient mod;
    public Onebot onebot;
    private Timer timer;

    @Override
    public void onInitializeClient() {
        mod = this;
        onebot = new Onebot();
        onebot.reconnect();

        timer = new Timer(true);
        timer.scheduleAtFixedRate(onebot, 60 * 1000, 60 * 1000);
    }
}
