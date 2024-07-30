package com.molean.isletopiachattoqq.mixin;

import com.molean.isletopiachattoqq.client.IsletopiaChatToQQClient;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class ChatMixin {
    @Inject(method = "handleChatInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;sendChat(Ljava/lang/String;)V"))
    public void on(String string, boolean bl, CallbackInfo ci) {
        IsletopiaChatToQQClient.mod.onebot.push(string);
    }
}
