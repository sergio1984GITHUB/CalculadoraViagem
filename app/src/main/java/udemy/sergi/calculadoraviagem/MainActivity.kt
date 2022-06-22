package udemy.sergi.calculadoraviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import udemy.sergi.calculadoraviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityMainBinding

    /**
     * Função responsável por fazer a criação da Activity
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adiciona evento ao elemento de interface
        binding.btnCalculate.setOnClickListener(this)

    }

    /**
     * Função responsável por tratar qualquer evento de click nos elementos
     * */
    override fun onClick(view: View) {

        val id = view.id

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

                val price =  binding.editPrice.text.toString().toFloat()
                val distance = binding.editDistance.text.toString().toFloat()
                val autonomy = binding.editAutonomy.text.toString().toFloat()

                // Realiza o cálculo ((distancia * preço) / autonomia)
                val totalValue = (distance * price) / autonomy

                // Seta o valor calculado na tela - Formatado com duas casas
                "${"%.2f".format(totalValue)} EUR€".also { binding.texTotalValue.text = it }

            }catch (nfe: NumberFormatException){

                // Caso não tenha preenchido todos os campos, solicita o preenchimento
                Toast.makeText(this, getString(R.string.valores_validos), Toast.LENGTH_SHORT).show()
            }
        }else{

            Toast.makeText(this, getString(R.string.preenche_todos_campos), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Verifica se todos os campos foram preenchidos
     */
    private fun validationOK(): Boolean {
        return (binding.editPrice.text.toString() !== ""
                && binding.editAutonomy.text.toString() !== ""
                && binding.editDistance.text.toString() !== ""
                && binding.editDistance.text.toString() !== "0")
    }

}
