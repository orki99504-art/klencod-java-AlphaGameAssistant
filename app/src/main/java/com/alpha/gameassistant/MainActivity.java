
package com.alpha.gameassistant;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;
import android.view.Gravity;

public class MainActivity extends Activity {

    private static final String CODE = "ALPHA-2026";
    private static final int BG = Color.rgb(15, 12, 22);
    private static final int PANEL = Color.rgb(39, 29, 51);
    private static final int PURPLE = Color.rgb(160, 70, 255);

    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        showLogin();
    }

    private GradientDrawable shape(int color) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(22);
        return d;
    }

    private TextView label(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(8, 12, 8, 12);
        return t;
    }

    private Button makeButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setMinHeight(58);
        b.setBackground(shape(PURPLE));
        return b;
    }

    private void base() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BG);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(24, 24, 24, 24);

        scroll.addView(root);
        setContentView(scroll);
    }

    private void showLogin() {
        base();

        root.addView(label("ALPHA GAME", 30, PURPLE));
        root.addView(label("مساعد الألعاب", 20, Color.WHITE));
        root.addView(label("أدخل كود التفعيل", 17, Color.WHITE));

        EditText code = new EditText(this);
        code.setSingleLine(true);
        code.setHint("كود التفعيل");
        code.setHintTextColor(Color.LTGRAY);
        code.setTextColor(Color.WHITE);
        code.setTextSize(17);
        code.setGravity(Gravity.CENTER);
        code.setBackground(shape(PANEL));

        LinearLayout.LayoutParams fieldParams =
            new LinearLayout.LayoutParams(-1, 58);
        fieldParams.setMargins(0, 14, 0, 14);
        root.addView(code, fieldParams);

        Button activate = makeButton("تفعيل الدخول");
        root.addView(activate, new LinearLayout.LayoutParams(-1, 60));

        activate.setOnClickListener(v -> {
            if (CODE.equals(code.getText().toString().trim())) {
                showHome();
            } else {
                Toast.makeText(this, "كود التفعيل غير صحيح",
                    Toast.LENGTH_SHORT).show();
            }
        });

        root.addView(label("ALPHA © 2026", 12, Color.LTGRAY));
    }

    private void showHome() {
        base();

        root.addView(label("ALPHA GAME", 29, PURPLE));
        root.addView(label("اختار نسخة لعبتك", 19, Color.WHITE));
        root.addView(label("اضغط على النسخة المثبتة عندك",
            14, Color.LTGRAY));

        addGameButton("ببجي العالمية", "com.tencent.ig");
        addGameButton("ببجي الكورية", "com.pubg.krmobile");
        addGameButton("ببجي الفيتنامية", "com.vng.pubgmobile");
        addGameButton("ببجي التايوانية", "com.rekoo.pubgm");

        root.addView(label(
            "إذا لم تُفتح اللعبة، قد يختلف معرّف الحزمة.",
            12, Color.LTGRAY));

        Button logout = makeButton("تسجيل الخروج");
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 56);
        p.setMargins(0, 12, 0, 12);
        root.addView(logout, p);
        logout.setOnClickListener(v -> showLogin());
    }

    private void addGameButton(String title, String packageName) {
        Button b = makeButton(title);
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 60);
        p.setMargins(0, 6, 0, 6);
        root.addView(b, p);

        b.setOnClickListener(v -> {
            Intent launch = getPackageManager()
                .getLaunchIntentForPackage(packageName);

            if (launch != null) {
                try {
                    startActivity(launch);
                } catch (Exception e) {
                    Toast.makeText(this,
                        "تعذّر فتح اللعبة",
                        Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this,
                    "لم أستطع العثور على هذه النسخة. قد يكون معرّفها مختلفاً.",
                    Toast.LENGTH_LONG).show();
            }
        });
    }
}
