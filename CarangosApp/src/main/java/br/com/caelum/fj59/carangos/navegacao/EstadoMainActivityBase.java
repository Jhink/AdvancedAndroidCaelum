package br.com.caelum.fj59.carangos.navegacao;

import android.app.Fragment;
import android.app.FragmentTransaction;

import java.io.Serializable;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.MainActivity;

/**
 * Created by android5501 on 20/10/15.
 */
public abstract class EstadoMainActivityBase implements Serializable {
    public abstract void executa(MainActivity activity);

    protected void colocaFragmentNaTela(MainActivity activity, Fragment fragment){
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_principal, fragment);
        ft.commit();
    }
}
