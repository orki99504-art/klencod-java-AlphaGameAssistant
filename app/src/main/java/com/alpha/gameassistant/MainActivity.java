
package com.alpha.gameassistant;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ScrollView;

public class MainActivity extends Activity {

    private final int BG = Color.rgb(12, 10, 18);
    private final int PURPLE = Color.rgb(174, 65, 255);
    private final int WHITE = Color.WHITE;
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        showHome();
    }

    private GradientDrawable makeShape(int color) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(24);
        d.setStroke(2, PURPLE);
        return d;
    }

    private TextView makeText(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        t.setGravity(Gravity.CENTER);
        t.setPadding(12, 14, 12, 14);
        t.setTextDirection(View.TEXT_DIRECTION_LOCALE);
        return t;
    }

    private Button makeButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextSize(19);
        b.setTextColor(WHITE);
        b.setAllCaps(false);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setMinHeight(68);
        b.setPadding(12, 12, 12, 12);
        b.setBackground(makeShape(Color.rgb(66, 25, 100)));
        return b;
    }

    private void showHome() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BG);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(22, 28, 22, 28);
        root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        scroll.addView(root);
        setContentView(scroll);

        TextView logo = makeText("ALPHA GAME", 32, PURPLE);
        root.addView(logo);

        TextView title = makeText(
            "مساعد الألعاب", 27, WHITE
        );
        root.addView(title);

        TextView subtitle = makeText(
            "ببجي موبايل العالمية", 21, PURPLE
        );
        root.addView(subtitle);

        TextView info = makeText(
            "اضغط على الزر لفتح اللعبة المثبتة عندك",
            17, WHITE
        );
        root.addView(info);

        Button pubg = makeButton("🎮  تشغيل ببجي العالمية");
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 76);
        p.setMargins(0, 24, 0, 12);
        root.addView(pubg, p);

        pubg.setOnClickListener(v -> launchPubg());

        TextView note = makeText(
            "ALPHA  •  واجهة الألعاب",
            15, Color.LTGRAY
        );
        root.addView(note);
    }

    private void launchPubg() {
        String packageName = "com.tencent.ig";
        PackageManager pm = getPackageManager();

        Intent launch = pm.getLaunchIntentForPackage(packageName);

        if (launch != null) {
            try {
                launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(launch);
            } catch (Exception e) {
                Toast.makeText(
                    this,
                    "تعذر فتح ببجي. تأكد أنها مثبتة.",
                    Toast.LENGTH_LONG
                ).show();
            }
        } else {
            Toast.makeText(
                this,
                "لم أجد ببجي العالمية. تأكد من تثبيتها وتحديث التطبيق.",
                Toast.LENGTH_LONG
            ).show();
        }
    }
}
