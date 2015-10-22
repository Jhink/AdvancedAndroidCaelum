package br.com.caelum.fj59.carangos.navegacao;

import android.support.v4.app.FragmentManager;

import br.com.caelum.fj59.carangos.activity.MainActivity;

/**
 * Created by android5501 on 20/10/15.
 */
public class EstadoMainActivityInicio extends EstadoMainActivityBase{

    @Override
    public void executa(MainActivity activity) {
        activity.buscaPublicacoes();
        activity.alteraEstadoEExecuta(new EstadoMainActivityAguardandoPublicacoes());
    }
}
