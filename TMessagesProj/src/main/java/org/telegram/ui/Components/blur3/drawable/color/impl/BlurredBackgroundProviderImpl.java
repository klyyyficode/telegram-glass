package org.telegram.ui.Components.blur3.drawable.color.impl;

import static org.telegram.messenger.AndroidUtilities.dpf2;

import android.graphics.Color;

import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;

import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProviderBuilder;

public class BlurredBackgroundProviderImpl {

    // Настоящее прозрачное жидкое стекло iOS (легкая стеклянная вуаль вместо глухой краски)
    private static int getIosGlassTint(Theme.ResourcesProvider r, boolean isDark, float darkAlpha, float lightAlpha) {
        if (isDark) {
            // В темной теме: кристально-прозрачный ледяной оттенок с легким высветлением линзы
            return ColorUtils.setAlphaComponent(0xFF2A303D, Math.round(darkAlpha * 255f));
        } else {
            return ColorUtils.setAlphaComponent(0xFFFFFFFF, Math.round(lightAlpha * 255f));
        }
    }

    public static BlurredBackgroundProvider mainTabs(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.22f, 0.45f))
            .setStrokeColorTop(0xCCFFFFFF, 0x80FFFFFF)
            .setStrokeColorBottom(0x66FFFFFF, 0x38FFFFFF)
            .setShadowColor(0x38000000, 0x50000000)
            .setShadowLayer(dpf2(8f), 0, dpf2(2.5f))
            .setStrokeWidth(dpf2(1.1f), dpf2(0.85f))
            .build();
    }

    public static BlurredBackgroundProvider topPanel(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.22f, 0.45f))
            .setStrokeColorTop(0xCCFFFFFF, 0x80FFFFFF)
            .setStrokeColorBottom(0x66FFFFFF, 0x38FFFFFF)
            .setShadowColor(0x33000000, 0x48000000)
            .setShadowLayer(dpf2(7f), 0, dpf2(2f))
            .setStrokeWidth(dpf2(1.1f), dpf2(0.85f))
            .build();
    }

    public static BlurredBackgroundProvider tabsPanelNoShadow(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.20f, 0.42f))
            .setStrokeColorTop(0x99FFFFFF, 0x55FFFFFF)
            .setStrokeColorBottom(0x44FFFFFF, 0x28FFFFFF)
            .setShadowColor(0, 0)
            .setStrokeWidth(dpf2(0.9f), dpf2(0.7f))
            .build();
    }

    public static BlurredBackgroundProvider emojiViewButton(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.22f, 0.45f))
                .setStrokeColorTop(0xDDFFFFFF, 0x80FFFFFF)
                .setStrokeColorBottom(0x88FFFFFF, 0x38FFFFFF)
                .setShadowColor(0x40000000, 0x48000000)
                .setShadowLayer(dpf2(6f), 0, dpf2(1.5f))
                .setStrokeWidth(dpf2(1.0f), dpf2(0.8f))
                .build();
    }

    public static BlurredBackgroundProvider counterMini(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> 0x22FFFFFF)
                .setStrokeColorTop(0xAAFFFFFF, 0x88FFFFFF)
                .setStrokeColorBottom(0x55FFFFFF, 0x44FFFFFF)
                .setShadowColor(0, 0)
                .setShadowLayer(0, 0, 0)
                .setStrokeWidth(dpf2(0.8f), dpf2(0.8f))
                .build();
    }

    public static BlurredBackgroundProvider messageMenuReactionsBackground(Theme.ResourcesProvider resourcesProvider) {
        return messageMenuBackground(resourcesProvider);
    }

    public static BlurredBackgroundProvider messageMenuBackground(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.28f, 0.50f))
                .setStrokeColorTop(0xCCFFFFFF, 0x77FFFFFF)
                .setStrokeColorBottom(0x66FFFFFF, 0x38FFFFFF)
                .setShadowColor(0x40000000, 0x55000000)
                .setShadowLayer(dpf2(8f), 0, dpf2(2.5f))
                .setStrokeWidth(dpf2(1.1f), dpf2(0.85f))
                .build();
    }

    public static BlurredBackgroundProvider scrimMenuBackground(Theme.ResourcesProvider resourcesProvider) {
        return messageMenuBackground(resourcesProvider);
    }

    public static BlurredBackgroundProvider attachMenuSearch(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.22f, 0.45f))
                .setStrokeColorTop(0xAAFFFFFF, 0x66FFFFFF)
                .setStrokeColorBottom(0x55FFFFFF, 0x33FFFFFF)
                .setShadowColor(0x28000000, 0x38000000)
                .setShadowLayer(dpf2(5f), 0, dpf2(1.5f))
                .setStrokeWidth(dpf2(0.9f), dpf2(0.8f))
                .build();
    }

    public static BlurredBackgroundProvider searchFloatingDate(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.22f, 0.42f))
                .setStrokeColorTop(0xAAFFFFFF, 0x66FFFFFF)
                .setStrokeColorBottom(0x55FFFFFF, 0x33FFFFFF)
                .setShadowColor(0x28000000, 0x38000000)
                .setShadowLayer(dpf2(4f), 0, dpf2(1.2f))
                .setStrokeWidth(dpf2(0.9f), dpf2(0.8f))
                .build();
    }

    public static BlurredBackgroundProvider bottomPanelChatActivity(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.20f, 0.42f))
                .setStrokeColorTop(0xDDFFFFFF, 0x85FFFFFF)
                .setStrokeColorBottom(0x77FFFFFF, 0x40FFFFFF)
                .setShadowColor(0x35000000, 0x50000000)
                .setShadowLayer(dpf2(7.5f), 0, dpf2(2.2f))
                .setStrokeWidth(dpf2(1.15f), dpf2(0.9f))
                .build();
    }

    public static BlurredBackgroundProvider topPanelChatActivity(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.20f, 0.42f))
                .setStrokeColorTop(0xDDFFFFFF, 0x85FFFFFF)
                .setStrokeColorBottom(0x77FFFFFF, 0x40FFFFFF)
                .setShadowColor(0x35000000, 0x50000000)
                .setShadowLayer(dpf2(7.5f), 0, dpf2(2.2f))
                .setStrokeWidth(dpf2(1.15f), dpf2(0.9f))
                .build();
    }

    public static BlurredBackgroundProvider topPanelChatActivityTags(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.20f, 0.42f))
            .setStrokeColorTop(0x99FFFFFF, 0x55FFFFFF)
            .setStrokeColorBottom(0x44FFFFFF, 0x28FFFFFF)
            .setShadowColor(0, 0)
            .setShadowLayer(0, 0, 0)
            .setStrokeWidth(dpf2(0.85f), dpf2(0.75f))
            .build();
    }

    public static BlurredBackgroundProvider topPanelChatActivitySearchListBg(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.25f, 0.48f))
                .setStrokeColorTop(0, 0)
                .setStrokeColorBottom(0, 0)
                .setShadowColor(0, 0)
                .setShadowLayer(0, 0, 0)
                .setStrokeWidth(0, 0)
                .build();
    }

    public static BlurredBackgroundProvider bulletin(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.25f, 0.48f))
            .setStrokeColorTop(0xBBFFFFFF, 0x70FFFFFF)
            .setStrokeColorBottom(0x55FFFFFF, 0x33FFFFFF)
            .setShadowColor(0x33000000, 0x45000000)
            .setShadowLayer(dpf2(6.5f), 0, dpf2(2f))
            .setStrokeWidth(dpf2(1.0f), dpf2(0.85f))
            .build();
    }

    public static BlurredBackgroundProvider inputFieldDialogActivity(Theme.ResourcesProvider resourcesProvider) {
        return topPanel(resourcesProvider);
    }

    public static BlurredBackgroundProvider inputFieldShareAlert(Theme.ResourcesProvider resourcesProvider) {
        return topPanel(resourcesProvider);
    }

    public static BlurredBackgroundProvider photoViewer(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> 0x22FFFFFF)
                .setStrokeColorTop(0xAAFFFFFF, 0x77FFFFFF)
                .setStrokeColorBottom(0x55FFFFFF, 0x38FFFFFF)
                .setStrokeWidth(dpf2(1.0f), dpf2(0.85f))
                .build();
    }

    public static BlurredBackgroundProvider photoViewerMenu(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
                .setBackgroundColor((r, isDark) -> 0x331E222B)
                .setStrokeColorTop(0xAAFFFFFF, 0x77FFFFFF)
                .setStrokeColorBottom(0x55FFFFFF, 0x38FFFFFF)
                .setStrokeWidth(dpf2(1.0f), dpf2(0.85f))
                .build();
    }

    public static BlurredBackgroundProvider premiumButton(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setBackgroundColor((r, isDark) -> getIosGlassTint(r, isDark, 0.25f, 0.48f))
            .setStrokeColorTop(0xFFFFFFFF, 0x80FFFFFF)
            .setStrokeColorBottom(0x66FFFFFF, 0x38FFFFFF)
            .setShadowColor(0x48000000, 0x48000000)
            .setShadowLayer(dpf2(6f), 0, dpf2(1.8f))
            .setStrokeWidth(dpf2(1.0f), dpf2(0.85f))
            .build();
    }

    public static BlurredBackgroundProvider shadow(Theme.ResourcesProvider resourcesProvider) {
        return new BlurredBackgroundProviderBuilder(resourcesProvider)
            .setStrokeColorTop(0x88FFFFFF, 0x55FFFFFF)
            .setStrokeColorBottom(0x44FFFFFF, 0x28FFFFFF)
            .setShadowColor(0x35000000, 0x45000000)
            .setShadowLayer(dpf2(6f), 0, dpf2(1.8f))
            .setStrokeWidth(dpf2(0.9f), dpf2(0.8f))
            .build();
    }

    public static int solveSrcColor(int bgColor, int outColor, float alpha) {
        return ColorUtils.setAlphaComponent(outColor, Math.round(MathUtils.clamp(alpha, 0f, 1f) * 255f));
    }

    public static boolean checkBlurEnabled(Theme.ResourcesProvider resourcesProvider) {
        return true;
    }

    public static boolean checkBlurEnabled(int currentAccount, Theme.ResourcesProvider resourcesProvider) {
        return true;
    }
}