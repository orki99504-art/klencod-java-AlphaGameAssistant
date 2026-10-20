
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

public class MainActivity extends Activity {

    private final int PURPLE = Color.rgb(160, 70, 255);
    private final int BG = Color.rgb(15, 12, 22);
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

    private TextView text(String value, int size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextColor(Color.WHITE);
        t.setTextSize(size);
        t.setGravity(17);
        t.setTypeface(null, Typeface.BOLD);
        t.setPadding(12, 12, 12, 12);
        return t;
    }

    private Button button(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(Color.WHITE);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setBackground(shape(PURPLE, 24));
        return b;
    }

    private void base() {
        root = new LinearLayout(this);
        root.setOrientation(1);
        root.setGravity(17);
        root.setPadding(26, 24, 26, 24);
        root.setBackgroundColor(BG);
        setContentView(root);
    }

    private void showLogin() {
        base();

        TextView title = text("ALPHA GAME", 30);
        title.setTextColor(PURPLE);
        root.addView(title);

        root.addView(text("GAME ASSISTANT", 18));
        root.addView(text("أدخل كود التفعيل للمتابعة", 16));

        EditText code = new EditText(this);
        code.setSingleLine(true);
        code.setHint("Activation Code");
        code.setHintTextColor(Color.LTGRAY);
        code.setTextColor(Color.WHITE);
        code.setGravity(17);
        code.setPadding(12, 8, 12, 8);
        code.setBackground(shape(Color.rgb(38, 30, 48), 18));

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 58);
        p.setMargins(0, 20, 0, 14);
        root.addView(code, p);

        Button activate = button("تفعيل الدخول");
        root.addView(activate, p);

        activate.setOnClickListener(v -> {
            if (code.getText().toString().trim()
                    .equals("ALPHA-2026")) {
                showHome();
            } else {
                Toast.makeText(this, "كود التفعيل غير صحيح",
                    Toast.LENGTH_SHORT).show();
            }
        });

        root.addView(text("ALPHA © 2026", 12));
    }

    private void showHome() {
        base();

        TextView title = text("ALPHA GAME", 28);
        title.setTextColor(PURPLE);
        root.addView(title);

        root.addView(text("مرحباً بك في مساعد الألعاب", 18));
        root.addView(text("PUBG MOBILE GLOBAL", 15));

        Button launch = button("فتح PUBG العالمية");
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 60);
        p.setMargins(0, 24, 0, 12);
        root.addView(launch, p);

        launch.setOnClickListener(v -> {
            Intent intent = getPackageManager()
                .getLaunchIntentForPackage("com.tencent.ig");

            if (intent != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this,
                    "PUBG العالمية غير موجودة أو غير مثبتة",
                    Toast.LENGTH_LONG).show();
            }
        });

        Button about = button("حول التطبيق");
        root.addView(about, p);
        about.setOnClickListener(v ->
            Toast.makeText(this,
                "Alpha Game Assistant - Version 1.0",
                Toast.LENGTH_LONG).show());

        Button logout = button("تسجيل الخروج");
        root.addView(logout, p);
        logout.setOnClickListener(v -> showLogin());
    }
}
