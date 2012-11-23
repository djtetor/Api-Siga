package siga.api;

import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import org.ksoap2.*;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;

public class main extends Activity{
	
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String METHOD_NAME = "getPersona";
	private static final String URL = "http://sistemaenaccion.com/pruebaAPI/conexion.asmx";// no longer avaible
	private static final String SOAP_ACTION = NAMESPACE+METHOD_NAME;
	
	private ArrayAdapter<String> adaptador;
	private AutoCompleteTextView actv;
	private ArrayList<String> listaResultados;
	private String[] mString;
			
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.personadetalle);   
        actv = (AutoCompleteTextView) findViewById(R.id.acPersonas);
        actv.addTextChangedListener(responsable);
    }
    final TextWatcher responsable = new TextWatcher(){
		@Override
		public void afterTextChanged(Editable arg0) {}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			actualizar(s,adaptador,actv);
		}
    };
    private void actualizar(CharSequence s, ArrayAdapter<String> adap, AutoCompleteTextView auto){
    	listaResultados = getListado(s.toString());
    	mString = (String[]) listaResultados.toArray(new String[listaResultados.size()]);
    	adap = new ArrayAdapter<String>(this,R.layout.auto,mString);
    	adap.setNotifyOnChange(true);
    	auto.setAdapter(adap);
    }
    public ArrayList<String> getListado(String busqueda) {
    	
    	ArrayList<String>  resultado = new ArrayList<String>();
    	
    	SoapObject peticion = new SoapObject(NAMESPACE, METHOD_NAME);
    	peticion.addProperty("busqueda", busqueda);
    	SoapSerializationEnvelope envoltura = 
    		new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	envoltura.dotNet = true;
    	envoltura.setOutputSoapObject(peticion);
    	HttpTransportSE transporte = new HttpTransportSE(URL);
    	
    	try{
		transporte.call(SOAP_ACTION, envoltura);
		SoapObject respuesta = (SoapObject) envoltura.getResponse();
		String[] lista = new String[respuesta.getPropertyCount()];
		for(int i = 0; i<= lista.length;i++){
			resultado.add(respuesta.getProperty(i).toString());
		}	
    	}catch(Exception ex){
    		ex.toString();
    	}
    	return resultado;    	
    }
}
