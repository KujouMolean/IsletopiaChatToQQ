package com.molean.isletopiachattoqq.mixin;

import com.molean.isletopiachattoqq.IsletopiaChatToQQ;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class ChatMixin extends Screen {
    protected ChatMixin(Component component) {
        super(component);
    }

    @Inject(method = "handleChatInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;sendChat(Ljava/lang/String;)V"), cancellable = true)
    public void on(String string, boolean bl, CallbackInfo ci) {
        try {
            IsletopiaChatToQQ.mod.onebot.push(string);
            ci.cancel();
        } catch (Exception e) {
            this.minecraft.player.sendSystemMessage(Component.literal("消息发动到QQ失败，可能是没登录QQ、没安装插件、没加群等原因导致。"));
            this.minecraft.player.sendSystemMessage(Component.literal("请按照教程安装，并重新登陆qq。"));
            e.printStackTrace();
        }
    }
}
