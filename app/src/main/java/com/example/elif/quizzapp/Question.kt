package com.example.elif.quizzapp

class Quiz(
    var Sorular: String, var secenek1: String, var secenek2: String, var secenek3: String, var sorununDogruCevabi: Int
) {

    fun dogruCevap(sorularinNumaralari: Int): Boolean {
        if (sorularinNumaralari == sorununDogruCevabi) {
            return true
        } else {
            return false
        }
    }

}