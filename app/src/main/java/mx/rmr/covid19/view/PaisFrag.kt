package mx.rmr.covid19.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yabu.livechart.model.DataPoint
import com.yabu.livechart.model.Dataset
import mx.rmr.covid19.viewmodel.PaisVM
import mx.rmr.covid19.R
import mx.rmr.covid19.databinding.PaisFragmentBinding
import java.util.ArrayList

class PaisFrag : Fragment() {

    private val args: PaisFragArgs by navArgs()

    private val viewModel: PaisVM by viewModels()

    private lateinit var binding: PaisFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaisFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarVista()
        configurarObservadores()
        configurarGrafica()
        viewModel.descargarHistorico(args.paisSeleccionado.nombre, "all")
    }

    private fun informacionCasos(){
        val totales = binding.tvCasos
        val muertes = binding.tvCasos
        val recuperados = binding.tvCasos
    }

    private fun configurarGrafica() {
        val liveChart = binding.grafCasos

        val dataset = Dataset(mutableListOf(
            DataPoint(0f, 1f),
            DataPoint(1f, 3f),
            DataPoint(2f, 10f),
            DataPoint(3f, 6f)))

        liveChart.setDataset(dataset)
            .drawYBounds()
            .drawBaseline()
            .drawFill()
            .drawDataset()
    }

    private fun configurarVista() {
        //Titulo el pais que llego
        binding.tvNombrePais.text = args.paisSeleccionado.nombre
    }

    private fun configurarObservadores(){
        viewModel.arrContagios.observe(viewLifecycleOwner){lista ->
            configurarGrafica(lista)
        }

    }

    private fun configurarGrafica(lista: ArrayList<Int>?) {
        val liveChart = binding.grafCasos

        val arrPuntos = mutableListOf<DataPoint>()

        if (lista != null) {
            val arr = lista!!

            for (i in 1 until arr.size) {
                arrPuntos.add(DataPoint(i.toFloat(), arr[i].toFloat()-arr[i-1]))
            }

            val dataset = Dataset(arrPuntos)

            liveChart.setDataset(dataset)
                .drawYBounds()
                .drawBaseline()
                .drawFill()
                .drawDataset()
        }

    }
}