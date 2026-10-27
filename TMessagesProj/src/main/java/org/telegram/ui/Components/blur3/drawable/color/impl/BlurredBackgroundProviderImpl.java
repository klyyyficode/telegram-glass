package org.telegram.ui.Components.blur3.drawable.color.impl;

import static org.telegram.messenger.AndroidUtilities.dpf2;

import android.graphics.Color;

import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;

import org.telegram.messenger.LiteMode;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProviderBuilder;

public class BlurredBackgroundProviderImpl {

    // Оптическая плотность стекла iOS Liquid Glass (прозрачное тело линзы)
    private static final float IOS_GLASS_ALPHA = 0.58f;

    public static BlurredBackgroundProvider mainTabs(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> {
                final int colorBg = Theme.getColor(Theme.key_windowBackgroundWhite, r);
                final int colorTarget = Theme.getColor(Theme.key_glass_targetMainTabs, r);
                return solveSrcColor(colorBg, colorTarget, IOS_GLASS_ALPHA);
            })
            .setStrokeColorTop(0x99FFFFFF, 0x38FFFFFF)
            .setStrokeColorBottom(0x40FFFFFF, 0x1EFFFFFF)
            .setShadowColor(0x2E000000, 0x38000000)
            .setShadowLayer(dpf2(6f), 0, dpf2(2f))
            .setStrokeWidth(dpf2(0.75f), dpf2(0.6f))
            .build();
    }

    public static BlurredBackgroundProvider topPanel(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> {
                final int colorBg = Theme.getColor(Theme.key_windowBackgroundWhite, r);
                final int colorTarget = Theme.getColor(Theme.key_glass_targetMainTopPanel, r);
                return solveSrcColor(colorBg, colorTarget, IOS_GLASS_ALPHA);
            })
            .setStrokeColorTop(0x99FFFFFF, 0x38FFFFFF)
            .setStrokeColorBottom(0x40FFFFFF, 0x1EFFFFFF)
            .setShadowColor(0x28000000, 0x33000000)
            .setShadowLayer(dpf2(5f), 0, dpf2(1.5f))
            .setStrokeWidth(dpf2(0.75f), dpf2(0.6f))
            .build();
    }

    public static BlurredBackgroundProvider tabsPanelNoShadow(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) ->
                Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, r), IOS_GLASS_ALPHA))
            .setStrokeColorTop(0x66FFFFFF, 0x28FFFFFF)
            .setStrokeColorBottom(0x33FFFFFF, 0x14FFFFFF)
            .setShadowColor(0, 0)
            .setStrokeWidth(dpf2(0.6f), dpf2(0.5f))
            .build();
    }

    public static BlurredBackgroundProvider emojiViewButton(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> {
                    final int colorBg = Theme.getColor(Theme.key_windowBackgroundWhite, r);
                    return Theme.multAlpha(colorBg, IOS_GLASS_ALPHA);
                })
                .setStrokeColorTop(0xFFFFFFFF, 0x38FFFFFF)
                .setStrokeColorBottom(0xFFFFFFFF, 0x1EFFFFFF)
                .setShadowColor(0x40000000, 0x40000000)
                .setShadowLayer(dpf2(4.5f), 0, dpf2(1.2f))
                .setStrokeWidth(dpf2(0.7f), dpf2(0.6f))
                .build();
    }

    public static BlurredBackgroundProvider counterMini(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> {
                    final float alpha = 0.12f;
                    final int colorBg = Color.BLACK;
                    return Theme.multAlpha(colorBg, alpha);
                })
                .setStrokeColorTop(0x80FFFFFF, 0x60FFFFFF)
                .setStrokeColorBottom(0x33FFFFFF, 0x28FFFFFF)
                .setShadowColor(0, 0)
                .setShadowLayer(0, 0, 0)
                .setStrokeWidth(dpf2(0.55f), dpf2(0.55f))
                .build();
    }

    public static BlurredBackgroundProvider messageMenuReactionsBackground(Theme.ResourcesProvider resourcesProvider) {
        return messageMenuBackground(resourcesProvider);
    }

    public static BlurredBackgroundProvider messageMenuBackground(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) ->
                    Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r), isDark ? 0.62f : 0.65f))
                .setStrokeColorTop(0x88FFFFFF, 0x38FFFFFF)
                .setStrokeColorBottom(0x44FFFFFF, 0x1EFFFFFF)
                .setShadowColor(0x38000000, 0x44000000)
                .setShadowLayer(dpf2(6f), 0, dpf2(2f))
                .setStrokeWidth(dpf2(0.8f), dpf2(0.7f))
                .build();
    }

    public static BlurredBackgroundProvider scrimMenuBackground(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) ->
                Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r), isDark ? 0.62f : 0.65f))
            .setStrokeColorTop(0x88FFFFFF, 0x38FFFFFF)
            .setStrokeColorBottom(0x44FFFFFF, 0x1EFFFFFF)
            .setShadowColor(0x30000000, 0x40000000)
            .setShadowLayer(dpf2(6f), 0, dpf2(2f))
            .setStrokeWidth(dpf2(0.8f), dpf2(0.7f))
            .build();
    }

    public static BlurredBackgroundProvider attachMenuSearch(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> {
                    final int colorBg = Theme.getColor(Theme.key_windowBackgroundWhite, r);
                    return Theme.multAlpha(colorBg, IOS_GLASS_ALPHA);
                })
                .setStrokeColorTop(0x66FFFFFF, 0x33FFFFFF)
                .setStrokeColorBottom(0x33FFFFFF, 0x1AFFFFFF)
                .setShadowColor(0x22000000, 0x28000000)
                .setShadowLayer(dpf2(3.5f), 0, dpf2(1f))
                .setStrokeWidth(dpf2(0.65f), dpf2(0.65f))
                .build();
    }

    public static BlurredBackgroundProvider searchFloatingDate(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> 0x40000000)
                .setStrokeColorTop(0x66FFFFFF, 0x33FFFFFF)
                .setStrokeColorBottom(0x33FFFFFF, 0x1AFFFFFF)
                .setShadowColor(0x22000000, 0x22000000)
                .setShadowLayer(dpf2(3f), 0, dpf2(1f))
                .setStrokeWidth(dpf2(0.65f), dpf2(0.65f))
                .build();
    }

    public static BlurredBackgroundProvider bottomPanelChatActivity(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> {
                    final int colorBg = Theme.getColor(Theme.key_chat_messagePanelBackground, r);
                    return Theme.multAlpha(colorBg, IOS_GLASS_ALPHA);
                })
                .setStrokeColorTop(0xB3FFFFFF, 0x3DFFFFFF)
                .setStrokeColorBottom(0x66FFFFFF, 0x22FFFFFF)
                .setShadowColor(0x2B000000, 0x38000000)
                .setShadowLayer(dpf2(5.5f), 0, dpf2(1.8f))
                .setStrokeWidth(dpf2(0.8f), dpf2(0.7f))
                .build();
    }

    public static BlurredBackgroundProvider topPanelChatActivity(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> {
                    final int colorBg = Theme.getColor(Theme.key_chat_topPanelBackground, r);
                    return Theme.multAlpha(colorBg, IOS_GLASS_ALPHA);
                })
                .setStrokeColorTop(0xB3FFFFFF, 0x3DFFFFFF)
                .setStrokeColorBottom(0x66FFFFFF, 0x22FFFFFF)
                .setShadowColor(0x2B000000, 0x38000000)
                .setShadowLayer(dpf2(5.5f), 0, dpf2(1.8f))
                .setStrokeWidth(dpf2(0.8f), dpf2(0.7f))
                .build();
    }

    public static BlurredBackgroundProvider topPanelChatActivityTags(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> {
                final int colorBg = Theme.getColor(Theme.key_chat_topPanelBackground, r);
                return Theme.multAlpha(colorBg, IOS_GLASS_ALPHA);
            })
            .setStrokeColorTop(0x66FFFFFF, 0x28FFFFFF)
            .setStrokeColorBottom(0x33FFFFFF, 0x14FFFFFF)
            .setShadowColor(0, 0)
            .setShadowLayer(0, 0, 0)
            .setStrokeWidth(dpf2(0.55f), dpf2(0.55f))
            .build();
    }

    public static BlurredBackgroundProvider topPanelChatActivitySearchListBg(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> {
                    final int colorBg = Theme.getColor(Theme.key_windowBackgroundWhite, r);
                    return Theme.multAlpha(colorBg, IOS_GLASS_ALPHA);
                })
                .setStrokeColorTop(0, 0)
                .setStrokeColorBottom(0, 0)
                .setShadowColor(0, 0)
                .setShadowLayer(0, 0, 0)
                .setStrokeWidth(0, 0)
                .build();
    }

    public static BlurredBackgroundProvider bulletin(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> {
                final int colorBg = Theme.getColor(Theme.key_undo_background, r);
                return Theme.multAlpha(colorBg, IOS_GLASS_ALPHA);
            })
            .setStrokeColorTop(0x88FFFFFF, 0x33FFFFFF)
            .setStrokeColorBottom(0x44FFFFFF, 0x1AFFFFFF)
            .setShadowColor(0x28000000, 0x33000000)
            .setShadowLayer(dpf2(5f), 0, dpf2(1.5f))
            .setStrokeWidth(dpf2(0.7f), dpf2(0.6f))
            .build();
    }

    public static BlurredBackgroundProvider inputFieldDialogActivity(Theme.ResourcesProvider resourcesProvider) {
        return topPanel(resourcesProvider);
    }

    public static BlurredBackgroundProvider inputFieldShareAlert(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> {
                    final int colorBg = Theme.getColor(Theme.key_windowBackgroundWhite, r);
                    final int colorTarget = Theme.getColor(Theme.key_chat_messagePanelBackground, r);
                    return solveSrcColor(colorBg, colorTarget, IOS_GLASS_ALPHA);
                })
                .setStrokeColorTop(0x88FFFFFF, 0x38FFFFFF)
                .setStrokeColorBottom(0x44FFFFFF, 0x1EFFFFFF)
                .setShadowColor(0x28000000, 0x33000000)
                .setShadowLayer(dpf2(5f), 0, dpf2(1.5f))
                .setStrokeWidth(dpf2(0.8f), dpf2(0.7f))
                .build();
    }

    public static BlurredBackgroundProvider photoViewer(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> 0x1A000000)
                .setStrokeColorTop(0x66FFFFFF, 0x38FFFFFF)
                .setStrokeColorBottom(0x33FFFFFF, 0x1EFFFFFF)
                .setStrokeWidth(dpf2(0.75f), dpf2(0.75f))
                .build();
    }

    public static BlurredBackgroundProvider photoViewerMenu(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> 0x40000000)
                .setStrokeColorTop(0x66FFFFFF, 0x38FFFFFF)
                .setStrokeColorBottom(0x33FFFFFF, 0x1EFFFFFF)
                .setStrokeWidth(dpf2(0.75f), dpf2(0.75f))
                .build();
    }

    public static BlurredBackgroundProvider premiumButton(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) ->
                Theme.multAlpha(Theme.getColor(Theme.key_dialogBackground, r), 0.65f))
            .setStrokeColorTop(0xFFFFFFFF, 0x38FFFFFF)
            .setStrokeColorBottom(0x44FFFFFF, 0x20FFFFFF)
            .setShadowColor(0x48000000, 0x33000000)
            .setShadowLayer(dpf2(4f), 0, dpf2(1f))
            .setStrokeWidth(dpf2(0.75f), dpf2(0.75f))
            .build();
    }

    public static BlurredBackgroundProvider shadow(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setStrokeColorTop(0x66FFFFFF, 0x38FFFFFF)
            .setStrokeColorBottom(0x33FFFFFF, 0x1EFFFFFF)
            .setShadowColor(0x30000000, 0x33000000)
            .setShadowLayer(dpf2(5f), 0, dpf2(1.5f))
            .setStrokeWidth(dpf2(0.65f), dpf2(0.65f))
            .build();
    }

    public static int solveSrcColor(int bgColor, int outColor, float alpha) {
        alpha = MathUtils.clamp(alpha, 0, 1);

        if (alpha <= 0f) {
            return Color.argb(0, 0, 0, 0);
        }
        if (alpha >= 1f) {
            return Color.argb(255, Color.red(outColor), Color.green(outColor), Color.blue(outColor));
        }

        final int bgR = Color.red(bgColor);
        final int bgG = Color.green(bgColor);
        final int bgB = Color.blue(bgColor);

        final int outR = Color.red(outColor);
        final int outG = Color.green(outColor);
        final int outB = Color.blue(outColor);

        final float invA = 1f - alpha;

        final int srcR = MathUtils.clamp(Math.round((outR - bgR * invA) / alpha), 0, 255);
        final int srcG = MathUtils.clamp(Math.round((outG - bgG * invA) / alpha), 0, 255);
        final int srcB = MathUtils.clamp(Math.round((outB - bgB * invA) / alpha), 0, 255);

        final int a8 = MathUtils.clamp(Math.round(alpha * 255f), 0, 255);

        return Color.argb(a8, srcR, srcG, srcB);
    }

    public static boolean checkBlurEnabled(Theme.ResourcesProvider resourcesProvider) {
        return true;
    }

    public static boolean checkBlurEnabled(int currentAccount, Theme.ResourcesProvider resourcesProvider) {
        return true; // Принудительно разрешаем Liquid Glass во всех темах
    }
}