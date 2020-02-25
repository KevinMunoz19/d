package com.digifactbeta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.kinpos.kpinvocacion.KP_Invocador;
import com.kinpos.kpinvocacion.Trans_Results;
import com.kinpos.printer.A920Printer;
import com.kinpos.printer.ALINEAMIENTO;
import com.kinpos.printer.OperationResult;
import com.kinpos.printer.TAMANIO_LETRA;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import javax.annotation.Nonnull;


public class Main3Activity extends AppCompatActivity implements ReactPackage {
//public class Main3Activity extends ReactContextBaseJavaModule  implements ReactPackage {
        //private String mposURL= "com.kinpos.movipagobg";
        private String      mposURL = "com.kinpos.BASEA920";
        private EditText txtToken        ;
        private Button btVenta         ;
        private Button      btAnulacion     ;
        private Button      btCierre        ;
        private Button      btReenviar      ;
        private Button      btnImprimir     ;
        private String      user        = "jsanchez"            ;
        private String      password    = "K1np0$2usa"          ;
        private String      deviceID    = "JOSE00013"           ;
        private String      eMail       = "prueba@kinpos.com"   ;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);



            setContentView(R.layout.activity_main3);



            txtToken    = findViewById  (   R.id.txtToken   );
            btVenta     = findViewById  (   R.id.btVenta    );
            btVenta.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        KP_Invocador kpInvocador = new KP_Invocador(mposURL, Main3Activity.this);

                        kpInvocador.KP_Sale(user,password,deviceID, 110,0,0,eMail,"0416");
                    }
                    catch(Exception ex)
                    {
                        msgBox("Error",ex.getMessage(),"Aceptar");
                    }
                }
            });

            btAnulacion = findViewById(R.id.btAnulacion);
            btAnulacion.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        String tokenID = txtToken.getText().toString();
                        if (tokenID==null || tokenID.isEmpty())
                        {
                            msgBox("Atención","No hay TokenId para anular","Aceptar");
                            return;
                        }

                        KP_Invocador kpInvocador = new KP_Invocador(mposURL, Main3Activity.this);

                        String recibo = tokenID;

                        kpInvocador.KP_Void(user,password,deviceID, tokenID,eMail,"0212", recibo);

                    }catch(Exception ex){
                        msgBox("Error",ex.getMessage(),"Aceptar");
                    }
                }
            });

            btCierre = findViewById(R.id.btCierre);

            btCierre.setOnClickListener (new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        KP_Invocador kpInvocador = new KP_Invocador(mposURL, Main3Activity.this);

                        kpInvocador.    KP_Close(user,password,deviceID);
                    }
                    catch(Exception ex)
                    {
                        msgBox("Error",ex.getMessage(),"Aceptar");
                    }
                }
            });

            btReenviar = findViewById(R.id.btReenviar);

            btReenviar.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        String tokenID = txtToken.getText().toString();

                        if (tokenID==null || tokenID.isEmpty())
                        {
                            msgBox("Atención","No hay TokenId para reenviar","Aceptar");
                            return;
                        }

                        KP_Invocador kpInvocador = new KP_Invocador(mposURL, Main3Activity.this);
                        kpInvocador.KP_Resend(user,password,deviceID, tokenID, eMail,"0424");

                    }
                    catch(Exception ex)
                    {
                        msgBox("Error",ex.getMessage(),"Aceptar");
                    }
                }
            });


            btnImprimir = findViewById( R.id.btnImprimir );

            if (null != btnImprimir)
            {
                btnImprimir.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        imprimirDocumento();
                    }
                });
            }

        }

        //--------------------------------------------------------------------
        //  Imorime Documento de prueba
        //
        //  ******** IMAGE **********
        //  *                       *
        //  *       LINEA 01        *
        //  * LINEA 02              *
        //  *              LINEA 03 *
        //  *                       *
        //  *       FIRMA           *
        //
        //--------------------------------------------------------------------
        public void imprimirDocumento ()
        {
            A920Printer printer = new A920Printer( Main3Activity.this );


            //--------------------------------------------------------------------
            // Nivel de la tinta
            // Posibles valores;
            //      1. 100
            //      2. 200
            //      3. 300
            //      4. 400
            //      5. 500
            //--------------------------------------------------------------------

            printer.setGray( 100 );

            //--------------------------------------------------------------------
            // Logo
            //--------------------------------------------------------------------
            //Bitmap logoCredomatic = BitmapFactory.decodeResource(getResources(), R.drawable.credomaticprinter);
            //Bitmap logoCredomatic = BitmapFactory.decodeResource(getResources(), R.drawable.credomaticprinter);
            //Bitmap logodigifact = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

            //printer.addLogoHeader(logoCredomatic);


            //--------------------------------------------------------------------
            // Prueba
            //--------------------------------------------------------------------
            printer.addLineaImprimir( "áíúü\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO       );
            printer.addLineaImprimir( "!\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA    );
            printer.addLineaImprimir( "#\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.DERECHA      );
            printer.addLineaImprimir( "     $   \n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA    );
            printer.addLineaImprimir( "%  \n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO       );
            printer.addLineaImprimir( "   &     \n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA    );
            printer.addLineaImprimir( "   /     \n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
            printer.addLineaImprimir( "     *   \n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
            printer.addLineaImprimir( " °\n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
            printer.addLineaImprimir( "\n\n\n\n\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO       );



            /**
             //--------------------------------------------------------------------
             // Section 1, Recibo con letras medianas
             //--------------------------------------------------------------------

             printer.addLineaImprimir( "LINEA 01 \n" , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.CENTRO       );
             printer.addLineaImprimir( "LINEA 02 \n" , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
             printer.addLineaImprimir( "LINEA 03 \n" , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.DERECHA      );
             printer.addLineaImprimir( "         \n" , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
             printer.addLineaImprimir( "FIRMA    \n" , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.CENTRO       );
             printer.addLineaImprimir( "1234567890abcdefghij123456789???\n"  , TAMANIO_LETRA.MEDIANO, ALINEAMIENTO.IZQUIERDA    );
             printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
             printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.IZQUIERDA    );
             *    printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );


             *     //--------------------------------------------------------------------
             *     // Section 1, Recibo con letras grandes
             *    //--------------------------------------------------------------------
             *
             *         printer.addLineaImprimir( "LINEA 01\n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.CENTRO       );
             *         printer.addLineaImprimir( "LINEA 02\n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.IZQUIERDA    );
             *         printer.addLineaImprimir( "LINEA 03\n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.DERECHA      );
             *         printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.IZQUIERDA    );
             *         printer.addLineaImprimir( "FIRMA\n"     , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.CENTRO       );
             *         printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.IZQUIERDA    );
             *         printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.IZQUIERDA    );
             *         printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.IZQUIERDA    );
             *         printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.GRANDE  , ALINEAMIENTO.IZQUIERDA    );
             *
             *
             *   //--------------------------------------------------------------------
             *   // Section 1, Recibo con letras pequeñas
             *   //--------------------------------------------------------------------
             *   printer.addLineaImprimir( "LINEA 01\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO       );
             *   printer.addLineaImprimir( "LINEA 02\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA    );
             *   printer.addLineaImprimir( "LINEA 03\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.DERECHA      );
             *   printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA    );
             *   printer.addLineaImprimir( "FIRMA   \n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO       );
             *   printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.IZQUIERDA    );
             *   printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
             *   printer.addLineaImprimir( "        \n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
             *   printer.addLineaImprimir( "\n\n\n\n\n"  , TAMANIO_LETRA.MEDIANO , ALINEAMIENTO.IZQUIERDA    );
             *   printer.addLineaImprimir( "\n\n\n\n\n"  , TAMANIO_LETRA.PEQUENIO, ALINEAMIENTO.CENTRO       );
             */


            //--------------------------------------------------------------------
            // check if there is paper before continuing
            //--------------------------------------------------------------------
            OperationResult result = printer.checkPaper (   );

            if (result .isSuccess() )
            {
                result = printer.imprimirDocumento      (   );
            }

            Log.i( "TAG", result.toString() );

        }


        /**
         * Mensaje sin ninguna acción, solo informativo
         * @param titulo titulo del mensaje
         * @param mensaje contenido del mensaje
         * @param textoBotonOk texto del botón de cerrar el mensaje
         */
        protected void msgBox(final String titulo, final String mensaje, final String textoBotonOk){
            try
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this);
                        builder.setTitle    (   titulo  );
                        builder.setMessage  (   mensaje );

                        builder.setPositiveButton(textoBotonOk, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                dialoginterface.dismiss();
                            }
                        });

                        Dialog alertDialog = builder.create     (           );
                        alertDialog.setCanceledOnTouchOutside   (   false   );
                        alertDialog.show                        (           );
                    }
                });

            }
            catch(Exception ex)
            {
                final String errMsg = ex.getMessage();
                Log.e("MainActivity", "msgBox \n" + errMsg);

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Toast.makeText(Main3Activity.this, errMsg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            try
            {
                KP_Invocador    kp_invocador    = new KP_Invocador(mposURL, Main3Activity.this);
                Trans_Results resultado       = kp_invocador.getResults(requestCode,resultCode,data);


                //------------------------------
                String tvr  = data.getStringExtra("TVR");
                String arqc = data.getStringExtra("ARQC");


                msgBox("RESULTADO", resultado.MensajeRespuesta, "OK");

                if  (
                        requestCode ==  KP_Invocador.PROCESSREQUESTSALE ||
                                resultCode  ==  Activity.RESULT_OK
                )
                {
                    txtToken.setText(  resultado.TokenID);
                }

            }
            catch(Exception ex)
            {
                msgBox("ATENCION", ex.getMessage(),"Continuar");
            }
        }

    @Nonnull
    @Override
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
        return null;
    }

    @Nonnull
    @Override
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
        return null;
    }
}