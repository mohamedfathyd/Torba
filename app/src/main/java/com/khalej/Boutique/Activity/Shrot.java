package com.khalej.Boutique.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.khalej.Boutique.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Shrot extends AppCompatActivity {
    TextView confirm;
    TextView b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrot);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Nasser.otf", true);
        confirm=findViewById(R.id.confirm);
        String html="\n" +
                "-\tالالتزام بطلبه.\n" +
                "-\tلا يمكن ارجاع الطلب الا في حال وصول المنتج غيرالذي تم طلبه .\n" +
                "-\tأن تكون بالغاً السن القانونية لتتمكن من شراء المنتجات في بلد إقامتك.\n" +
                "-\tأن تكون قادراً على تقديم عنوان في الإمارات العربية المتحدة\n" +
                "عند استخدامك للتطبيق:\n" +
                "عند استخدامك للخدمات أو وصولك إليها، فأنت توافق على ما يلي:\n" +
                "1-\tمسؤوليتك عن الحفاظ على الخصوصية وتقييد الوصول إلى الحساب الخاص بك واستخدامه هو وكلمة المرور، والموافقة على تحمل مسؤولية جميع الأنشطة التي تتم باسم الحساب الخاص بك وكلمة المرور الخاصة بك.\n" +
                "2-\tالموافقة على إخطارنا فورا عن أي استخدام غير مصرح به لكلمة المرور أو الحساب الخاص بك أو أي خرقٍ آخر لمعايير الاستخدام الآمن للموقع.\n" +
                "3-\tتقديم المعلومات الكاملة والحقيقية والدقيقة والحالية عن نفسك وعن استخدامك للخدمات كما هو محدد من قبلنا.\n" +
                "4-\tعدم الإفصاح للغير (باستثناء ما يلزم أو ما هو محدد من قبلنا) عن معلومات المستخدم المقدمة لك.\n" +
                "5-\tالتعاون مع الطلبات الصادرة عنا للحصول على معلومات إضافية فيما يتعلق بأهليتكك واستخدامك لخدماتنا.\n" +
                "\n" +
                "\n";
        b= findViewById(R.id.b);
        b.setText(html);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Shrot.this,MainActivity.class));
                finish();
            }
        });
    }
}
