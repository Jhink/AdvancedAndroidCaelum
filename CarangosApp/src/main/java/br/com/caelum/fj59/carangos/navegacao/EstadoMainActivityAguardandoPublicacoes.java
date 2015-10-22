package br.com.caelum.fj59.carangos.navegacao;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.MainActivity;
import br.com.caelum.fj59.carangos.fragments.ProgressFragment;

/**
 * Created by android5501 on 20/10/15.
 */
public class EstadoMainActivityAguardandoPublicacoes extends EstadoMainActivityBase {
    @Override
    public void executa(MainActivity activity) {
        ProgressFragment progress = ProgressFragment.comMensagem(R.string.carregando);
        colocaFragmentNaTela(activity, progress);
    }
}
