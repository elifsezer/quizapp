package com.example.elif.quizzapp

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*
import org.w3c.dom.Text

class QuizActivity : AppCompatActivity() {
    var quizs = ArrayList<Quiz>() //Quiz tipinde quizs sınıfını oluşturduk.
    var toplamDogruSayisi: Int = 0 //doğru cevap sayısı
    var suankisoruindexi: Int = 0 //sınav sorularının indexi atamak için degisken ürettik.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //42000 kısmı kaç ms olacağını gösteriyor. 42 sn anlamına gelmektedir.
        object : CountDownTimer(42000, 1000) {
            //süre bittiğinde mainactiviye yönlendirme yapıldı.
            override fun onFinish() {
                homeActivity()
                tv_sure.setText("Bitti!")
            }

            //kalan süreyi gösterir.
            override fun onTick(millisUntilFinished: Long) {
                tv_sure.setText("Kalan Zaman : " + millisUntilFinished / 1000)
            }

        }.start()
        //ilk oncreate anında last question butonun gözükmemesi için kullanıldı.
        btn_last_question.visibility = View.INVISIBLE
        quizs.add(Quiz("Aşağıdaki sözcüklerden hangisi eş seslidir ?", "Yel", "Yüz", "Kapı", 2))
        quizs.add(Quiz("Mustafa Kemalin babasının ismi nedir?", "Ali Rıza Efendi", "Karabekir", "Ahmet Efendi", 1))
        quizs.add(Quiz("Türkiyenin başkenti neresidir?", "Bursa", "Ankara", "Bolu", 2))
        quizs.add(Quiz("3+5-2=?", "7", "6", "5", 2))
        quizs.add(Quiz("İstiklal marşının yazarı kimdir?", "Atilla İlhan", "Orhan Veli Kanık", "Mehmet Akif Ersoy", 3))
        quizs.add(Quiz("8+8 işleminin sonucu nedir?", "14", "18", "16", 3))
        quizs.add(Quiz("10-9 işleminin sonucu nedir?", "19", "18", "1", 3))

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

        btn_next_question.setOnClickListener {
            //sonraki soruya geçmeyi sağlar. aslında indexi bir artırır.
            suankisoruindexi++
            //bir sonraki soruya gecisindeki radio butonun secili olması engelledik.
            radio_group.clearCheck()
            btn_last_question.visibility = View.VISIBLE
            if (suankisoruindexi >= quizs.size) {
                var alert = AlertDialog.Builder(this)
                alert.setTitle("Sonuc")
                alert.setMessage("Tebrikler, Dogru Cevap Sayısı : " + toplamDogruSayisi)
                alert.setPositiveButton("OK")
                { dialogInterface: DialogInterface?, i: Int ->
                    finish()
                }
                alert.show()
            } else {
                sorulari_goster(quizs.get(suankisoruindexi))

            }
        }

        btn_last_question.setOnClickListener {
            suankisoruindexi--
            radio_group.clearCheck()
            sorulari_goster(quizs.get(suankisoruindexi))
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

   private fun homeActivity() {
        var intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
