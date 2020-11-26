package com.example.mob

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import kotlinx.android.synthetic.main.activity_main.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import kotlinx.android.synthetic.main.activity_main.*

    @Suppress("DEPRECATION", "UNREACHABLE_CODE")
    class MainActivity : AppCompatActivity(),LoaderManager.LoaderCallbacks<List<String>> {
        val URL = "http://scn-test/freewms_sandbox/ws/TSD.1cws?wsdl"
        val NAMESPACE = "http://www.axelot.ru/wms4/package"
        private val METHOD_NAME = "Ping"
        val SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME
        val psw = "Qwerty1"
        val log = "userTSD"
       private val LOADER_ID = 1

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            loaderManager.initLoader(LOADER_ID, null, this)


        }

        override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<String>> {
            return object : AsyncTaskLoader<List<String>>(this){
                override fun onStartLoading(){
                    super.onStartLoading()
                    forceLoad()
                }


                override fun loadInBackground(): List<String>? {
                    val request = SoapObject(NAMESPACE,METHOD_NAME)
                    request.addProperty("Ping","") //TODO: Change 2nd arg to edited input
                    TODO("Not yet implemented")
                    val envelope = SoapSerializationEnvelope(SoapEnvelope.VER12)
                    envelope.dotNet = true
                    envelope.setOutputSoapObject(request)
                    val httpTransport = HttpTransportSE(URL)
                    try{
                        httpTransport.call(SOAP_ACTION,envelope)
                    }
                    catch(e:Exception){
                        e.printStackTrace()
                    }

                    Log.d("LOG_TAG", envelope.bodyIn.toString())

                    return null
                }

            }
            TODO("Not yet implemented")
        }

        override fun onLoadFinished(loader: Loader<List<String>>, data: List<String>?) {
            textView.setText("Hello wd")

        }

        override fun onLoaderReset(loader: Loader<List<String>>) {
            TODO("Not yet implemented")
        }

    }

private fun LoaderManager.initLoader(loaderId: Int, nothing: Nothing?, mainActivity: MainActivity) {

}


