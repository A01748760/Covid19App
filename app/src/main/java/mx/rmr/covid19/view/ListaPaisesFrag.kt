package mx.rmr.covid19.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.rmr.covid19.viewmodel.ListaPaisesVM
import mx.rmr.covid19.R
import mx.rmr.covid19.databinding.ListaPaisesFragmentBinding
import mx.rmr.covid19.model.Pais

class ListaPaisesFrag : Fragment(), RenglonListener
{

    companion object {
        fun newInstance() = ListaPaisesFrag()
    }

    private val viewModel: ListaPaisesVM by viewModels()
    private lateinit var binding: ListaPaisesFragmentBinding

    // Adaptador para el RecyclerView
    private val adaptadorListaPaises = AdaptadorListaPaises(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaPaisesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarObservadores()
        configurarEventos()
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.rvListaPaises.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorListaPaises
        }

        // val rv = binding.rvListaPaises
        // rv.layoutManager = LinearLayoutManager(context)
        // rv.adapter = adaptadorListaPaises
    }


    private fun configurarEventos() {
        viewModel.leerDatos()       // Evento (botón)
        adaptadorListaPaises.listener = this    //Esta vista es el listener
    }

    private fun configurarObservadores() {
        viewModel.arrPaises.observe(viewLifecycleOwner) { lista ->
            adaptadorListaPaises.actualizar(lista)
        }
    }
    //Evento (adaptador)
    override fun clickEnRenglon(posicion: Int) {
        //Cambiar pantalla
        val pais = adaptadorListaPaises.arrPaises[posicion]
        println("Cambiar de pantalla: ${pais.nombre}")
        val accion = ListaPaisesFragDirections.actionListaPaisesFragToPaisFrag(pais)
        findNavController().navigate(accion)
    }
}