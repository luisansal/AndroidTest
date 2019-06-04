package com.luisansal.jetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisansal.jetpack.model.domain.Author
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentMVP
import com.luisansal.jetpack.ui.mvp.author.NewAuthorFragmentPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_new_author.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.widget.TextView
import com.luisansal.jetpack.R
import kotlinx.android.synthetic.main.author_list_row.view.*


class NewAuthorFragment : Fragment(), NewAuthorFragmentMVP.View {
    override fun ocultarcontador() {
        tvContador.visibility = View.GONE
    }

    override fun onClickBtnMostrar() {
        btnMostrarAuthors.setOnClickListener {
            mPresenter.mostrarAuthors()
        }
    }

    inner class AuthorAdapter(private val authors: List<Author>) : RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.author_list_row, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val dni = authors.get(position).dni
            val name = authors.get(position).nombre
            holder.dni.setText(dni)
            holder.fullName.setText(name)
        }

        override fun getItemCount(): Int {
            return authors.size
        }

        inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val dni: TextView
            val fullName: TextView

            init {
                dni = v.tvDni
                fullName = v.tvNombreCompleto
            }
        }
    }

    override fun mostrarAuthors(authors: List<Author>) {
        rvAuthors.adapter = AuthorAdapter(authors)
    }

    override fun setupAdapterAuthors() {
        rvAuthors.layoutManager = LinearLayoutManager(context)
        rvAuthors.setHasFixedSize(true)
    }

    override var seconds: Int? = null

    override fun contadorNSegundos(n: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            for (i in n downTo 0) { // countdown from 10 to 1
                delay(1000)
                tvContador.text = i.toString()
                seconds = i
                if (seconds == 0) {
                    notificarRestriccionNSegundos()
                    ocultarcontador()
                }
            }
        }
    }

    override fun notificarRestriccionNSegundos() {
        Toast.makeText(context, "Usted se ha excedido del tiempo maximo para guardar", Toast.LENGTH_LONG).show()
    }

    @Inject
    lateinit var mPresenter: NewAuthorFragmentPresenter

    override fun onClickBtnGuardar() {
        btnGuardar.setOnClickListener {
            author = Author(etDni.text.toString(), etNombre.text.toString(), etApellido.text.toString())
            mPresenter.guardarAuthor()
        }
    }

    override fun onClickBtnBuscar() {
        btnBuscar.setOnClickListener {
            dni = etDni.text.toString()
            mPresenter.buscarAuthor()
        }
    }

    override fun notificarGuardado() {
        Toast.makeText(context, "Author ${author?.nombre} ${author?.apellido} Guardado", Toast.LENGTH_LONG).show()
        author = Author("", "", "")
        camposVacios()
    }

    override fun cargarCamposEnVista() {
        etNombre.setText(author?.nombre)
        etApellido.setText(author?.apellido)
    }

    override fun authorEncontrado() {
        cargarCamposEnVista()
        notificarEncontrado()
    }

    override fun notificarEncontrado() {
        Toast.makeText(context, "Author ${author?.nombre} ${author?.apellido} Encontrado", Toast.LENGTH_LONG).show()
    }

    override fun notificarNoEncontrado() {
        Toast.makeText(context, "Author no Encontrado", Toast.LENGTH_LONG).show()
    }

    override fun camposVacios() {
        etDni.setText(author?.dni)
        etNombre.setText(author?.nombre)
        etApellido.setText(author?.apellido)
    }

    override fun mostrarErrorDni() {
        Toast.makeText(context, "Longitud de caracteres incorrecta de DNI", Toast.LENGTH_LONG).show()
    }

    override fun mostrarErrorCamposObligatorios() {
        Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
    }

    override var author: Author? = null

    override var dni: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_author, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.setView(this)
        mPresenter.init()
    }

    override fun authorDuplicado(mensaje: String) {
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
    }

    companion object {

        var TAG = NewAuthorFragment::class.java.getName()

        fun newInstance(): NewAuthorFragment {
            val fragment = NewAuthorFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}