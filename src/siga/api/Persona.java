package siga.api;

import java.util.ArrayList;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.R.integer;

public class Persona implements KvmSerializable{

	public String nombre;
	public String pApellido;
	public String sApellido;
	public int edad;
	
	public Persona(){}
	
	public Persona(String name, String paterno, String materno,int e){
		nombre = name;
		pApellido = paterno;
		sApellido = materno;
		edad = e;		
	}
	@Override
	public Object getProperty(int arg0) {
		switch(arg0){
		case 0:
			return nombre;
		case 1:
			return pApellido;
		case 2:
			return sApellido;
		case 3:
			return edad;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 4;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch(index){
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "nombre";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "pApellido";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "sApellido";
			break;
		case 3:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "edad";
			break;
		default:break;
		}
	}

	@Override
	public void setProperty(int index, Object value) {
		switch(index){
		case 0:
			nombre = value.toString();
			break;
		case 1:
			pApellido = value.toString();
			break;
		case 2:
			sApellido = value.toString();
			break;
		case 3:
			edad = Integer.parseInt(value.toString());
			break;
		default:break;
		}
	}
}
