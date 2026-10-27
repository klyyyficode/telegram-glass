package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.telegram.messenger.AndroidUtilities;

public class LiquidGlassDrawable extends Drawable {

    public static final int STYLE_IOS_FROSTED = 0;     // Основные парящие капсулы (шапка, поле Message, меню)
    public static final int STYLE_ACCENT_BLUE = 1;     // Акцентные кнопки (Cancel / активные элементы)
    public static final int STYLE_CLEAR_DROPLET = 2;   // Прозрачная линза-ползунок (как на вкладке Channels 00:10)

    // AGSL-шейдер физического преломления света на краях капсулы (для Android 13+)
    private static final String LIQUID_REFRACTION_SHADER =
            "uniform shader content;\n" +
            "uniform float2 resolution;\n" +
            "uniform float radius;\n" +
            "uniform float refractionStrength;\n" +
            "\n" +
            "float sdRoundBox(float2 p, float2 b, float r) {\n" +
            "    float2 q = abs(p) - b + r;\n" +
            "    return min(max(q.x, q.y), 0.0) + length(max(q, 0.0)) - r;\n" +
            "}\n" +
            "\n" +
            "half4 main(float2 coord) {\n" +
            "    float2 halfSize = resolution * 0.5;\n" +
            "    float2 p = coord - halfSize;\n" +
            "    float d = sdRoundBox(p, halfSize - 2.0, radius);\n" +
            "    \n" +
            "    // Вычисляем изгиб стеклянной фаски у края капсулы (последние 14px)\n" +
            "    float bezel = smoothstep(-14.0, 0.0, d) * step(d, 0.0);\n" +
            "    float2 normal = normalize(p + 0.0001);\n" +
            "    \n" +
            "    // Смещаем пиксели фона для эффекта выпуклой линзы\n" +
            "    float2 refractedCoord = coord - normal * bezel * refractionStrength;\n" +
            "    return content.eval(refractedCoord);\n" +
            "}";

    private final Paint shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint innerBevelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint rimPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private final RectF fillRect = new RectF();
    private final RectF strokeRect = new RectF();

    private float cornerRadiusDp = 24f;
    private final int style;

    public LiquidGlassDrawable(int style) {
        this.style = style;

        // Внешняя мягкая тень парящего островка
        shadowPaint.setColor(0x00000000);
        shadowPaint.setShadowLayer(
                AndroidUtilities.dp(12f),
                0f,
                AndroidUtilities.dp(4f),
                0x33000000
        );

        rimPaint.setStyle(Paint.Style.STROKE);
        rimPaint.setStrokeWidth(AndroidUtilities.dp(1.2f));
    }

    /**
     * Подключает аппаратное преломление краев (Lens Distortion) к любому View
     */
    public static void applyLensRefractionToView(View targetView, float radiusDp, float strengthPx) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && targetView != null) {
            targetView.addOnLayoutChangeListener((v, left, top, right, bottom, oldL, oldT, oldR, oldB) -> {
                int w = right - left;
                int h = bottom - top;
                if (w > 0 && h > 0) {
                    try {
                        RuntimeShader shader = new RuntimeShader(LIQUID_REFRACTION_SHADER);
                        shader.setFloatUniform("resolution", w, h);
                        shader.setFloatUniform("radius", AndroidUtilities.dp(radiusDp));
                        shader.setFloatUniform("refractionStrength", strengthPx);
                        v.setRenderEffect(RenderEffect.createRuntimeShaderEffect(shader, "content"));
                    } catch (Exception ignored) {
                    }
                }
            });
        }
    }

    public void setCornerRadiusDp(float dp) {
        if (this.cornerRadiusDp != dp) {
            this.cornerRadiusDp = dp;
            invalidateSelf();
        }
    }

    @Override
    protected void onBoundsChange(@NonNull Rect bounds) {
        super.onBoundsChange(bounds);
        fillRect.set(bounds);

        float inset = AndroidUtilities.dp(0.7f);
        strokeRect.set(bounds);
        strokeRect.inset(inset, inset);

        float w = fillRect.width();
        float h = fillRect.height();
        if (w <= 0 || h <= 0) {
            return;
        }

        // 1. Матовая полупрозрачная заливка стекла
        if (style == STYLE_ACCENT_BLUE) {
            LinearGradient blueGradient = new LinearGradient(
                    fillRect.left, fillRect.top,
                    fillRect.left, fillRect.bottom,
                    new int[]{0xD90A84FF, 0xD90066CC},
                    new float[]{0.0f, 1.0f},
                    Shader.TileMode.CLAMP
            );
            basePaint.setShader(blueGradient);
        } else if (style == STYLE_CLEAR_DROPLET) {
            // Почти полностью прозрачная капля для переключателя вкладок (00:10)
            LinearGradient dropletGradient = new LinearGradient(
                    fillRect.left, fillRect.top,
                    fillRect.left, fillRect.bottom,
                    new int[]{0x33FFFFFF, 0x0DFFFFFF, 0x26FFFFFF},
                    new float[]{0.0f, 0.5f, 1.0f},
                    Shader.TileMode.CLAMP
            );
            basePaint.setShader(dropletGradient);
        } else {
            // Универсальное матовое стекло (адаптируется под фон под ним)
            LinearGradient frostedGradient = new LinearGradient(
                    fillRect.left, fillRect.top,
                    fillRect.left, fillRect.bottom,
                    new int[]{0x8C252830, 0x73181A20},
                    new float[]{0.0f, 1.0f},
                    Shader.TileMode.CLAMP
            );
            basePaint.setShader(frostedGradient);
        }

        // 2. Внутренняя объемная фаска (создает ощущение толщины стеклянной капли)
        LinearGradient bevelGradient = new LinearGradient(
                fillRect.left, fillRect.top,
                fillRect.left, fillRect.bottom,
                new int[]{0x40FFFFFF, 0x00FFFFFF, 0x1A000000},
                new float[]{0.0f, 0.35f, 1.0f},
                Shader.TileMode.CLAMP
        );
        innerBevelPaint.setShader(bevelGradient);

        // 3. Яркая стеклянная грань по периметру (Specular Rim)
        LinearGradient rimGradient = new LinearGradient(
                strokeRect.left, strokeRect.top,
                strokeRect.right, strokeRect.bottom,
                new int[]{
                        0x99FFFFFF, // Яркий блик сверху-слева
                        0x26FFFFFF, // Полупрозрачный центр
                        0x66FFFFFF  // Стеклянный отсвет снизу-справа
                },
                new float[]{0.0f, 0.5f, 1.0f},
                Shader.TileMode.CLAMP
        );
        rimPaint.setShader(rimGradient);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (fillRect.isEmpty()) {
            return;
        }
        float radiusPx = AndroidUtilities.dp(cornerRadiusDp);

        // 1. Внешняя мягкая тень
        canvas.drawRoundRect(fillRect, radiusPx, radiusPx, shadowPaint);

        // 2. Полупрозрачное тело линзы
        canvas.drawRoundRect(fillRect, radiusPx, radiusPx, basePaint);

        // 3. Объемная фаска
        canvas.drawRoundRect(fillRect, radiusPx, radiusPx, innerBevelPaint);

        // 4. Преломляющий контур
        canvas.drawRoundRect(strokeRect, radiusPx, radiusPx, rimPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        basePaint.setAlpha(alpha);
        innerBevelPaint.setAlpha(alpha);
        rimPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        basePaint.setColorFilter(colorFilter);
        innerBevelPaint.setColorFilter(colorFilter);
        rimPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}