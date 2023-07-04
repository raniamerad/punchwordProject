package com.example.fetchdatatestpunch

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConversionFonctions : AppCompatActivity() {

    private lateinit var somme: EditText // la somme entrée par l'utilisateur
    private var sommeValue: Double = 10.0
    private var papunch: Double = 0.0006153// prix actuel du punch que je ramène de l'API en USD
    private var paeur: Double = 1.0780 // prix actuel euro
    private var padzd: Double = 0.0074 // prix actuel dinar
    private var finalSomme: Double = 0.0 // la somme finale convertie

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcomepage)

        somme = findViewById(R.id.somme)

        val btn_dahabia: Button = findViewById(R.id.dahabia)
        val btn_cvisa: Button = findViewById(R.id.visa)
        val btn_masterc: Button = findViewById(R.id.mastercard)
        val btn_cib: Button = findViewById(R.id.cib)
        val btn_valider: Button = findViewById(R.id.valider)
        val btn_punch: Button = findViewById(R.id.punch)



        //convertir contenu edit text
        fun convertToDouble(text: String): Double {
            return try {
                text.toDouble()
            } catch (e: NumberFormatException) {
                0.0
            }
        }


        //fonction d'affichage Test
        fun showToast(message: String) {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }

        fun calculerEURO() {
            sommeValue = convertToDouble(somme.text.toString())

            finalSomme = (sommeValue * paeur) / papunch
            showToast("PAYEZ : $sommeValue eur RECEVEZ: $finalSomme punchs")
        }
        fun calculerDZD(){
            sommeValue = convertToDouble(somme.text.toString())
            finalSomme = (sommeValue * padzd) / papunch
            showToast("PAYEZ: $sommeValue dzd RECEVEZ: $finalSomme punchs")


        }
        fun calculerPunchEUR(){

            sommeValue = convertToDouble(somme.text.toString())
            finalSomme =  (sommeValue * papunch)/paeur
            // lien vers le paiement
            showToast("RECEVEZ :$sommeValue punch PAYEZ : $finalSomme euros")


        }
        fun calculerPunchDZD(){

            sommeValue = convertToDouble(somme.text.toString())
            finalSomme =  (sommeValue * papunch)/padzd


            showToast("RECEVEZ:$sommeValue punch PAYEZ: $finalSomme dinars")



        }
        btn_cvisa.setOnClickListener {
            btn_valider.setOnClickListener {
              calculerEURO()
                // lien vers le paiement

            }

            btn_masterc.setOnClickListener {
                btn_valider.setOnClickListener {
                    calculerEURO()
                   // lien vers le paiement
                }
            }
            btn_dahabia.setOnClickListener {
                btn_valider.setOnClickListener {
                calculerDZD()
                    // lien vers le paiement
                }
            }

        }
        btn_cib.setOnClickListener {
            btn_valider.setOnClickListener {
                calculerDZD()
                // lien vers le paiement
            }
        }

btn_punch.setOnClickListener {
    btn_cvisa.setOnClickListener {
          btn_valider.setOnClickListener {
              calculerPunchEUR()
        // lien vers le paiement

    }

    btn_masterc.setOnClickListener {
        btn_valider.setOnClickListener {
            calculerPunchEUR()
            // lien vers le paiement
        }
    }
    btn_dahabia.setOnClickListener {
        btn_valider.setOnClickListener {
            calculerPunchDZD()
            // lien vers le paiement
        }
    }

}
    btn_cib.setOnClickListener {
        btn_valider.setOnClickListener {
            calculerPunchDZD()
            // lien vers le paiement
                                      }
                               }
                             }

    }
    }