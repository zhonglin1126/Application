package com.lin.application;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextHtml();
        TextSpan();
        TextClick();
    }

    private void TextHtml() {
        TextView textView = (TextView) findViewById(R.id.tv_text);
        String string = "图片:<a href='http://www.baidu.com'><img src='ic_launcher'></a></br>";
        textView.setText(Html.fromHtml(string, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String s) {
                Drawable drawable = null;
                try {
                    Field field = R.mipmap.class.getField(s);
                    int i = Integer.parseInt(field.get(null).toString());
                    drawable = getResources().getDrawable(i);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return drawable;
            }
        }, null));
    }

    private void TextSpan() {
        TextView textView1 = (TextView) findViewById(R.id.tv_text2);
        SpannableString spannableString = new SpannableString("红色打电话斜体下划线删除线绿色下划线图片:.");
        /****
         * 设置字体颜色
         */
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /***
         * 用超链接标记文本
         */
        spannableString.setSpan(new URLSpan("tel:18126132371"), 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /***
         * 用样式标记文本(粗体,斜体)
         */
        spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /***
         * 下划线
         */
        spannableString.setSpan(new UnderlineSpan(), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /***
         * 删除线
         */
        spannableString.setSpan(new StrikethroughSpan(), 10, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        /***
         * 下划线,字体颜色
         */
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), spannableString.length() - 10, spannableString.length() - 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new UnderlineSpan(), spannableString.length() - 10, spannableString.length() - 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        /***
         * 图片代替文本
         */
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher_round);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(spannableString);
    }

    private void TextClick() {
        TextView textView = (TextView) findViewById(R.id.tv_text3);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append("好友" + i);
            if (i < 20) {
                stringBuilder.append(",");
            }
        }
        String likeuser = stringBuilder.toString();
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(addClick(likeuser), TextView.BufferType.SPANNABLE);
    }

    private SpannableStringBuilder addClick(String str) {
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher_round);
        SpannableString spannableString = new SpannableString("p:");
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannableString);
        spannableStringBuilder.append(str);
        String[] split = str.split(",");
        Log.i("Main", split.length + "===========");
        if (split.length > 0) {
            for (int i = 0; i < split.length; i++) {
                final String name = split[i];
                int start = str.indexOf(name) + spannableString.length();
                spannableStringBuilder.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                }, start, start + name.length(), 0);
            }
        }
        return spannableStringBuilder.append("等" + split.length + "个人觉得很赞");
    }
}
