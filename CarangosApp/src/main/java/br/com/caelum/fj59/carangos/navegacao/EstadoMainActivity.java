package br.com.caelum.fj59.carangos.navegacao;

import android.app.FragmentTransaction;
import android.app.Fragment;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.MainActivity;

/**
 * Created by android5501 on 20/10/15.
 */
public enum EstadoMainActivity {
    INICIO, AGUARDANDO_PUBLICACOES, PRIMEIRAS_PUBLICACOES_RECEBIDAS;

    void colocaFragmentNaTela(MainActivity activity, Fragment fragment) {
        FragmentTransaction tx = activity.getFragmentManager().beginTransaction();

        tx.replace(R.id.fragment_principal, fragment);
        tx.commit();
    }

    public abstract void executa(MainActivity activity);

    INICIO{
        @Override
        public void executa(MainActivity activity) {
            activity.alteraEstadoEExecuta(EstadoMainActivity.AGUARDANDO_PUBLICACOES);
        };
    },

    AGUARDANDO_PUBLICACOES,

    PRIMEIRAS_PUBLICACOES_RECEBIDAS;
}
