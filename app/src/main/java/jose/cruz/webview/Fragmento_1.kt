package jose.cruz.webview

import android.content.Context
import android.net.Uri
import android.opengl.GLES31
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragmento_1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragmento_1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmento_1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // declaracion de widgets
    var et_url:EditText? = null
    var b_ir:Button? = null
    var wv_1:WebView? = null
    var pb_1:ProgressBar? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val miVista = inflater.inflate(R.layout.fragment_fragmento_1, container, false)

        // instanciacion de widgets
        et_url = miVista.findViewById(R.id.et_url)
        b_ir = miVista.findViewById(R.id.b_ir)
        wv_1 = miVista.findViewById(R.id.wv_1)
        pb_1 = miVista.findViewById(R.id.pb_1)

        //wv_1!!.webChromeClient = WebChromeClient()
        wv_1!!.webChromeClient = object:WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                pb_1!!.progress = newProgress
            }

        }
        wv_1!!.settings.javaScriptEnabled = true


        b_ir!!.setOnClickListener {
            var url = et_url!!.text.toString().trim()
            var url_final = ""
            if( url.startsWith("www") ){
               url_final = "http://$url"
            } else {
                url_final = "https://www.google.com/search?q=$url"
            }
            wv_1!!.loadUrl(url_final)

        }

        return miVista
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragmento_1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragmento_1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
