package com.digifactbeta;

import com.facebook.react.ReactActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeArray;
import com.kinpos.printer.A920Printer;
import com.kinpos.printer.ALINEAMIENTO;
import com.kinpos.printer.OperationResult;
import com.kinpos.printer.TAMANIO_LETRA;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExampleActivity extends ReactActivity {


    private EditText prueba;
    private EditText prueba2;
    private EditText prueba3;
    private EditText autorizacion;


    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type))
            {
                handleSendText(intent); // Handle text being sent
            }
        }

        // Display app and React Native versions:
        //this.<TextView>findViewById(R.id.app_version).setText(BuildConfig.VERSION_NAME);
        //this.<TextView>findViewById(R.id.react_native_version).setText(BuildConfig.REACT_NATIVE_VERSION);

        findViewById(R.id.go_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.digifactbeta");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(ExampleActivity.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                }


            }
        });

        findViewById(R.id.impresionBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                imprimirDocumento();
            }
        });

    }



    public void imprimirDocumento ()
    {
        A920Printer printer = new A920Printer( ExampleActivity.this );
        printer.setGray( 300 ); // Toner intensity 100 to 500, 100 being the lowest
        //--------------------------------------------------------------------
        // Logo in header
        //--------------------------------------------------------------------
        // Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.qrcode);
        // printer.addLogoHeader(logo);
        //--------------------------------------------------------------------
        // Read data to print on receipt
        //--------------------------------------------------------------------
        //String numeroTarjeta = txtCard.getText().toString();
        //String datePrint = datedisplay.getText().toString();
        //String timePrint = timedisplay.getText().toString();
        //String namePrint = namedisplay.getText().toString();
        //String nitPrint = namedisplay.getText().toString();
        //String totalPrint = payment.getText().toString();
        //String transaccionTarjetaPrint = txtCardTransaction.getText().toString();


        String nombreComercio = "Restaurante";
        String nitComercio = "1599A";
        String direccionComercio = "5ta avenida zona 1";
        String tipoDocumentoTributario = "Documento Tributario Electronico";
        String strFactura = "Factura";
        String serieFactura = "123548597";
        String numeroFactura = "456123";
        String numeroAutorizacion = "12135646512345679845123465";
        String controlFactura = "fsjafgasgas";
        //String fechaEmision = datedisplay.getText().toString();
        //String horaEmision = timedisplay.getText().toString();

        // Cantidad item, nombre item, precio unitario item
        ArrayList<String> cantidadItemsLista = new ArrayList<>();
        ArrayList<String> nombreItemsLista = new ArrayList<>();
        ArrayList<String> precioItemsLista = new ArrayList<>();

        cantidadItemsLista.add("2");
        nombreItemsLista.add("Cafe");
        precioItemsLista.add("Q10");
        cantidadItemsLista.add("1");
        nombreItemsLista.add("te");
        precioItemsLista.add("Q5");
        cantidadItemsLista.add("3");
        nombreItemsLista.add("Hot Dog");
        precioItemsLista.add("Q8");
        //--------------------------------------------------------------------
        // Prueba
        //--------------------------------------------------------------------
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( ""+nombreComercio+"\n",TAMANIO_LETRA.MEDIANO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "NIT:"+nitComercio+"\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( ""+direccionComercio+"\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( ""+tipoDocumentoTributario+"\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( ""+strFactura+"\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "Serie:           "+serieFactura+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "Numero:          "+numeroFactura+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "Numero de Autorizacion:\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( ""+numeroAutorizacion+"\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "Control:         "+controlFactura+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        //printer.addLineaImprimir( "Fecha Emision:   "+fechaEmision+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        //printer.addLineaImprimir( "Hora Emision:    "+horaEmision+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        //if (namePrint.replaceAll("\\s+","").isEmpty() || nitPrint.replaceAll("\\s+","").isEmpty())
       // {
        //    printer.addLineaImprimir( "Nombre:     Consumidor Final \n" , TAMANIO_LETRA.MEDIANO, ALINEAMIENTO.IZQUIERDA);
        //    printer.addLineaImprimir( "NIT:        CF \n" , TAMANIO_LETRA.MEDIANO, ALINEAMIENTO.IZQUIERDA);
       // }
       // else
        //{
        //    printer.addLineaImprimir( "Nombre:    "+namePrint+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        //    printer.addLineaImprimir( "NIT:       "+nitPrint+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        //}
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        String formatedTitlesstr = String.format("%8s     %8s      %12s  ", "Cant.", "Prod.", "Precio");
        printer.addLineaImprimir( formatedTitlesstr+"", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        if ((cantidadItemsLista.size() == nombreItemsLista.size())&&(cantidadItemsLista.size() == precioItemsLista.size()))
        {


            for (int i = 0; i < cantidadItemsLista.size(); i++) {

                String formatedstr = String.format("%8s     %8s           %5s  ", cantidadItemsLista.get(i), nombreItemsLista.get(i), precioItemsLista.get(i));
                printer.addLineaImprimir( formatedstr+"\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA);
                //printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
            }
        }
        else
        {
            printer.addLineaImprimir( "No hay productos"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.DERECHA);
        }
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);


        //printer.addLineaImprimir( "Tarjeta:    "+numeroTarjeta+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA);
        //printer.addLineaImprimir( "Numero de Transaccion "+transaccionTarjetaPrint+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA);

        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
       // printer.addLineaImprimir( "Total:    Q"+totalPrint+"\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "Datos Certificador\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "CYBER ESPACIO S.A.\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "NIT: 77454820\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "4ta Avenida 15-70 Zona 10\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( "Local 3 Edificio Paladium\n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);
        printer.addLineaImprimir( " \n", TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO);


        //--------------------------------------------------------------------
        // check if there is paper before continuing
        //--------------------------------------------------------------------
        OperationResult result = printer.checkPaper();
        if (result .isSuccess())
        {
            result = printer.imprimirDocumento();
        }
        Log.i("TAG", result.toString());
    }




    void handleSendText(Intent intent) {
        //String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        String jsonstringdoc = intent.getStringExtra("jsondatadocumento");
        String jsonstringusuario = intent.getStringExtra("jsondatausuario");
        String jsonstringitems = intent.getStringExtra("jsondataitems");
        //if (sharedText != null) {
        if (jsonstringdoc != null) {


            prueba = findViewById(R.id.editText);
            prueba2 = findViewById(R.id.editText2);
            prueba3 = findViewById(R.id.editText3);

            //prueba.setText(sharedText);
            prueba.setText(jsonstringdoc);
            prueba2.setText(jsonstringusuario);
            prueba3.setText(jsonstringitems);
            autorizacion = findViewById(R.id.editTextAuth);

            // Remove brackets from


            String jsonstring = jsonstringdoc.replace("[","").replace("]","");
            String jsonuserstring = jsonstringusuario.replace("[","").replace("]","");
            String jsonitemsstring = jsonstringitems.replace("[","").replace("]","");
            //String jsonstring = sharedText.replace("[","").replace("]","");
            //String newjsonstring = jsonstring.replace("]","");

            try {
                //JSONObject jsonDataDoc = new JSONObject(newjsonstring);
                JSONObject jsonDataDoc = new JSONObject(jsonstring);
                JSONObject jsonDataUser = new JSONObject(jsonuserstring);
                String numeroAutorizacion = jsonDataDoc.getString("auth_number");
                //String numeroDocumento = jsonDataDoc.getString("number");
                //String numeroSerie = jsonDataDoc.getString("serie");
                //String numeroTotal = jsonDataDoc.getString("amount");
                //String fechaEmision = jsonDataDoc.getString("date");
                //String nitCliente = jsonDataDoc.getString("receiver_nit");
                //String statusDocumento = jsonDataDoc.getString("status");
                //String nombreCliente = jsonDataDoc.getString("receiver_name");

                String emailEmisor = jsonDataUser.getString("email");
                String nombreEmisor = jsonDataUser.getString("name");
                String nitEmisor = jsonDataUser.getString("nit");
                String documentoIdEmisor = jsonDataUser.getString("document_id");
                String nombreContactoEmisor = jsonDataUser.getString("contact_name");
                String telefonoEmisor = jsonDataUser.getString("phone");
                String caertificadoEmisor = jsonDataUser.getString("certificate");









                autorizacion.setText(numeroAutorizacion);
            }
            catch (JSONException err){
                Log.d("Error", err.toString());
                autorizacion.setText("Error Obteniendo Datos de Factura");
            }

        }
    }

}
