package udemy.sergi.calculadoraviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * Função responsável por fazer a criação da Activity
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Adiciona evento ao elemento de interface
        btn_calculate.setOnClickListener(this)
    }

    /**
     * Função responsável por tratar qualquer evento de click nos elementos
     * */

    override fun onClick(view: View) {
        // Obtém o Id do elemento clicado
        val id = view.id
        // Verifica se o elemento é o que nos interessa
        if (id == R.id.btn_calculate){
            calculate()
        }
    }
    /**
     * Função responsável por realizar o cálculo dos gastos com a viagem
     * Baseado na distância percorrida * preço médio do combustível / pela autonomia do veículo
     */
    private fun calculate() {
        if (validationOK()) {
            try {
                // Realiza o cálculo ((distancia * preço) / autonomia)
                val price = edit_Price.text.toString().toFloat()
                val distance = edit_Distance.text.toString().toFloat()
                val autonomy = edit_Autonomy.text.toString().toFloat()

                val totalValue = (distance * price) / autonomy

                // Seta o valor calculado na tela - Formatado com duas casas
                tex_TotalValue.text = "${"%.2f".format(totalValue)} EUR€"

            }catch (nfe: NumberFormatException){
                // Caso ocorra erro de conversão numérica, solicita ao usuário para preencher com valores válidos
                Toast.makeText(this, getString(R.string.valores_validos), Toast.LENGTH_SHORT).show()
            }
        }else{
            // Caso não tenha preenchido todos os campos, solicita o preenchimento
            Toast.makeText(this, getString(R.string.preenche_todos_campos), Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Verifica se todos os campos foram preenchidos
     */
    private fun validationOK(): Boolean {
        return (edit_Price.text.toString() !== ""
                && edit_Autonomy.text.toString() !== ""
                && edit_Distance.text.toString() !== ""
                && edit_Distance.text.toString() !== "0")
    }

}
