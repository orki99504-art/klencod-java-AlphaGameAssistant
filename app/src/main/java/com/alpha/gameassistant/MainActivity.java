
package com.alpha.gameassistant;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.Gravity;

public class MainActivity extends Activity {

    private final int BG = Color.rgb(15, 12, 22);
    private final int PANEL = Color.rgb(37, 27, 49);
    private final int PURPLE = Color.rgb(160, 70, 255);
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        showLogin();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private TextView label(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(color);
        t.setTextSize(size);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(10, 12, 10, 12);
        return t;
    }

    private Button makeButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setMinHeight(58);
        b.setPadding(12, 8, 12, 8);
        b.setBackground(shape(PURPLE, 18));
        return b;
    }

    private void addGap(int height) {
        TextView gap = new TextView(this);
        root.addView(gap, new LinearLayout.LayoutParams(1, height));
    }

    private void base() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(24, 20, 24, 20);
        root.setBackgroundColor(BG);
        setContentView(root);
    }

    private void showLogin() {
        base();

        root.addView(label("ALPHA GAME", 30, PURPLE));
        root.addView(label("GAME ASSISTANT", 18, Color.WHITE));
        addGap(12);
        root.addView(label("أدخل كود التفعيل", 17, Color.WHITE));

        EditText code = new EditText(this);
        code.setSingleLine(true);
        code.setHint("Activation Code");
        code.setHintTextColor(Color.LTGRAY);
        code.setTextColor(Color.WHITE);
        code.setTextSize(16);
        code.setGravity(Gravity.CENTER);
        code.setPadding(12, 8, 12, 8);
        code.setBackground(shape(PANEL, 16));

        LinearLayout.LayoutParams fieldParams =
            new LinearLayout.LayoutParams(-1, 58);
        fieldParams.setMargins(0, 14, 0, 12);
        root.addView(code, fieldParams);

        Button activate = makeButton("تفعيل الدخول");
        root.addView(activate, new LinearLayout.LayoutParams(-1, 60));

        activate.setOnClickListener(v -> {
            if ("ALPHA-2026".equals(
                    code.getText().toString().trim())) {
                showHome();
            } else {
                Toast.makeText(this, "كود التفعيل غير صحيح",
                    Toast.LENGTH_SHORT).show();
            }
        });

        addGap(14);
        root.addView(label("ALPHA © 2026", 12, Color.LTGRAY));
    }

    private void showHome() {
        base();

        root.addView(label("ALPHA GAME", 28, PURPLE));
        root.addView(label("اختار نسخة PUBG المثبتة عندك",
            17, Color.WHITE));
        addGap(10);

        addGameButton("PUBG MOBILE GLOBAL", "com.tencent.ig");
        addGameButton("PUBG MOBILE KR", "com.pubg.krmobile");
        addGameButton("PUBG MOBILE VN", "com.vng.pubgmobile");
        addGameButton("PUBG MOBILE TW", "com.rekoo.pubgm");

        addGap(10);
        root.addView(label(
            "إذا ظهرت رسالة أن اللعبة غير مثبتة، تحقق من النسخة.",
            12, Color.LTGRAY));

        Button logout = makeButton("تسجيل الخروج");
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 54);
        p.setMargins(0, 12, 0, 0);
        root.addView(logout, p);
        logout.setOnClickListener(v -> showLogin());
    }

    private void addGameButton(String title, String packageName) {
        Button b = makeButton(title);
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 60);
        p.setMargins(0, 5, 0, 5);
        root.addView(b, p);

        b.setOnClickListener(v -> {
            Intent intent = getPackageManager()
                .getLaunchIntentForPackage(packageName);

            if (intent != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this,
                    "هذه النسخة غير موجودة أو لا يمكن فتحها",
                    Toast.LENGTH_LONG).show();
            }
        });
    }
}
