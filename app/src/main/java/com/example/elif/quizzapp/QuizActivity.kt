package com.example.elif.quizzapp

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*
import org.w3c.dom.Text
import java.sql.Time
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class QuizActivity : AppCompatActivity() {
    var quizs = ArrayList<Quiz>() //Quiz tipinde quizs sınıfını oluşturduk.
    var toplamDogruSayisi: Int = 0 //doğru cevap sayısı
    var suankisoruindexi: Int = 0 //sınav sorularının indexi atamak için degisken ürettik.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //42000 kısmı kaç ms olacağını gösteriyor. 42 sn anlamına gelmektedir.
         object : CountDownTimer(180000, 1000) {
            //süre bittiğinde mainactiviye yönlendirme yapıldı.
            override fun onFinish() {
                sonucGoster()
            }

            //kalan süreyi gösterir.
            override fun onTick(millisUntilFinished: Long) {
                tv_sure.setText(
                    "Kalan Zaman : " + String.format(
                        "%d Dk: %d Sn",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                        )
                    )
                )
            }
        }.start()

        //ilk oncreate anında last question butonun gözükmemesi için kullanıldı.
        btn_last_question.visibility = View.INVISIBLE
        quizs.add(Quiz("Dünyanın şekli aşağıdaki meyvelerden hangisine benzer?", "Portakal", "Kivi", "Ayva", 1))
        quizs.add(Quiz("Aşağıda verilen ifadelerden hangisi doğrudur?", "Dünyanın büyük bölümü karalarla kaplıdır.", "Dünyanın büyük bölümü denizlerle kaplıdır.", "Dünyada yaşam sadece karadadır.", 2))
        quizs.add(Quiz("Canlılar için gerekli gazların karışımı dünyanın hangi bölümünde yer alır?", "Su", "Kara", "Hava", 3))
        quizs.add(Quiz("Canlılar için gerekli gazların karışımı dünyanın hangi bölümünde yer alır?", "Pisagor", "Aristo", "Kristof Kolomb", 1))
        quizs.add(Quiz("Dünyamızın 4'te 3'ünü aşağıdakilerden hangisi oluşturur?", "Kara", "Su", "Hava", 2))
        quizs.add(Quiz("Dünya' nın çevresini gemiyle dolaşarak Dünya' nın yuvarlak olduğunu ispatlayan kişi kimdir ?", "Aristo", "Pisagor", "Kristof Kolomb", 3))
        quizs.add(Quiz("Yağmur, kar gibi olaylar dünyanın hangi tabakasında meydana gelir?", "Hava", "Su", "Kara", 1))
        quizs.add(Quiz("Aşağıda verilen ifadelerden hangisi yanlıştır?", "Dünya katmanlardan oluşur.", "Küre şeklindedir.", "4'te 1'ini denizler oluşturur.", 3))
        quizs.add(Quiz("Dünyamızı oluşturan tabakalardan en içte hangisi yer alır?", "Su tabakası", "Taş tabakası", "Hava tabakası", 3))
        quizs.add(Quiz("Dünyamızı oluşturan tabakalardan en dışta hangisi yer alır?", "Taş tabakası", "Su tabakası", "Hava tabakası", 3))

        sorulari_goster(quizs.get(suankisoruindexi))
    }

    fun sorulari_goster(quiz: Quiz) {
        //bu fonksiyon ile soruları göster  ile soruları göstermeyi sagladık.
        tv_soru.setText(quiz.Sorular)
        radio_button1.setText(quiz.secenek1)
        radio_button2.setText(quiz.secenek2)
        radio_button3.setText(quiz.secenek3)
    }

    fun cevaplari_goster(soruID: Int) {
        //
        val soru = quizs.get(suankisoruindexi)
        if (soru.dogruCevap(soruID)) {
            toplamDogruSayisi++
            Toast.makeText(this, "Doğru Seçim", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Yanlış seçim", Toast.LENGTH_SHORT).show()
        }

        //bir sonraki soruya geçiş için kullanıldı.
        btn_next_question.setOnClickListener {
            //sonraki soruya geçmeyi sağlar. aslında indexi bir artırır.
            suankisoruindexi++
            btn_last_question.visibility = View.VISIBLE
            if (suankisoruindexi >= quizs.size) {
                sonucGoster()
            } else {
                sorulari_goster(quizs.get(suankisoruindexi))

            }
        }

        //bir önceki soruya dönüş için kullanıldı.
        btn_last_question.setOnClickListener {
            suankisoruindexi--
            if (suankisoruindexi == -1) {
                icerikGoster()
            } else {
                sorulari_goster(quizs.get(suankisoruindexi))
            }

        }
    }

    fun radiobutton_1(view: View) {

        cevaplari_goster(1)
    }

    fun radiobutton_2(view: View) {
        cevaplari_goster(2)
    }

    fun radiobutton_3(view: View) {
        cevaplari_goster(3)
    }

    private fun sonucGoster() {
        var alert = AlertDialog.Builder(this)
        alert.setTitle("Sonuc")
        alert.setMessage("Tebrikler, Dogru Cevap Sayısı : " + toplamDogruSayisi)
        alert.setPositiveButton("OK")
        { dialogInterface: DialogInterface?, i: Int ->
            finish()
        }
        alert.show()
    }

    private fun icerikGoster() {
        var intent = Intent(applicationContext, icerikActivity::class.java)
        startActivity(intent)
    }

}
