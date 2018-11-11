package com.example.elif.quizzapp

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.concurrent.TimeUnit

class ingilizceActivity : AppCompatActivity() {
    var quizs = ArrayList<Quiz>() //Quiz tipinde quizs sınıfını oluşturduk.
    var toplamDogruSayisi: Int = 0 //doğru cevap sayısı
    var suankisoruindexi: Int = 0 //sınav sorularının indexi atamak için degisken ürettik.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingilizce)
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
        quizs.add(Quiz("__ is my uncle.\n" +
                "Cümlesinde boş bırakılan yere aşağıdakilerden hangisi getirilmelidir?", "I", "He", "She", 3))
        quizs.add(Quiz("'' r t h o r b e '' harflerinden oluşan doğru kelime aşağıdakilerden hangisidir?", "betrhor", "brother", "brtoher",2))
        quizs.add(Quiz("Sister\n" +
                "Father\n" +
                "Teacher\n" +
                "Kelimelerinden hangisi diğerlerinden farklıdır?", "Father", "Sister", "Teacher", 3))
        quizs.add(Quiz("Umut : __________\n" + "Murat : She is my sister.\n"  + "İfadesinde boş bırakılan yere aşağıdakilerden hangisi getirilmelidir?", "How old are you?", "Where are you?", "Who is she?", 3))
        quizs.add(Quiz("Seda : ____ ?\n" +
                "Selin : I' m eating a hamburger\n" +
                "Boş bırakılan yere hangisi gelmelidir ?", "Who are you?", "What are you doing?", "What is your name?", 2))
        quizs.add(Quiz("He ___ eating hamburger.\n" +
                "Resme göre boşluğu tamamlayan ifade hangi seçenekte doğru verilmişti", "is", "am", "are", 1))
        quizs.add(Quiz("'' surprised / I / feel '' Verilen kelimelerin doğru sıralanışı hangi seçenekte verilmiştir?  ", "I surprised feel.", "Feel I suprised.", "I feel suprised.", 3))
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
