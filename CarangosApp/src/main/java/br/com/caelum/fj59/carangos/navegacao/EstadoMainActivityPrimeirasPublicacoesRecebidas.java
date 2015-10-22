package br.com.caelum.fj59.carangos.navegacao;

import br.com.caelum.fj59.carangos.activity.MainActivity;
import br.com.caelum.fj59.carangos.fragments.ListaDePublicacoesFragment;

/**
 * Created by android5501 on 20/10/15.
 */
public class EstadoMainActivityPrimeirasPublicacoesRecebidas extends EstadoMainActivityBase {

    @Override
    public void executa(MainActivity activity) {
        ListaDePublicacoesFragment frag = new ListaDePublicacoesFragment();
        colocaFragmentNaTela(activity, frag);
    }
}