package com.digifactbeta;

import com.facebook.react.ReactActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity                     ;
import android.app.AlertDialog                  ;
import android.app.Dialog                       ;
import android.content.DialogInterface          ;
import android.content.Intent                   ;
import android.graphics.Bitmap                  ;
import android.graphics.BitmapFactory           ;
// import android.support.v7.app.AppCompatActivity ;
import android.os.Bundle                        ;
import android.util.Log                         ;
import android.view.View                        ;
import android.widget.Button                    ;
import android.widget.EditText                  ;
import android.widget.Toast                     ;

import com.facebook.react.uimanager.ThemedReactContext;
import com.kinpos.kpinvocacion.KP_Invocador     ;
import com.kinpos.kpinvocacion.Trans_Results    ;
import com.kinpos.printer.A920Printer           ;
import com.kinpos.printer.ALINEAMIENTO          ;
import com.kinpos.printer.OperationResult       ;
import com.kinpos.printer.TAMANIO_LETRA         ;

import com.digifactbeta.Swch;
import com.digifactbeta.SwchManager;



public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "digifactBeta";
    }


}
