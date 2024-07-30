package com.molean.isletopiachattoqq;

import com.molean.isletopiachattoqq.onebot.Onebot;
import net.fabricmc.api.ModInitializer;

import java.util.Timer;

public class IsletopiaChatToQQ implements ModInitializer {
    public static IsletopiaChatToQQ mod;
    public Onebot onebot;
    private Timer timer;

    @Override
    public void onInitialize() {
        mod = this;
        onebot = new Onebot();
        onebot.reconnect();

        timer = new Timer(true);
        timer.scheduleAtFixedRate(onebot, 60 * 1000, 60 * 1000);
    }
}
