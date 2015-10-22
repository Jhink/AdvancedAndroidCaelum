package br.com.caelum.fj59.carangos.evento;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesTask;

/**
 * Created by android5501 on 22/10/15.
 */
public class EventosPublicacoesRecebidas extends BroadcastReceiver {
    private static final String RETORNO = "retorno";
    private static final String SUCESSO = "sucesso";
    private static final String PUBLICACOES_RECEBIDAS = "publicacoes recebidas";

    private BuscaMaisPublicacoesTask.BuscaMaisPublicacoesDelegate delegate;

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean deuCerto = intent.getBooleanExtra(SUCESSO, false);
        MyLog.i("RECEBI O EVENTO!!! DEU CERTO?");

        if ( deuCerto ) {

            delegate.lidaComRetorno((List<Publicacao>)
                    intent.getSerializableExtra(RETORNO));

        } else {

            delegate.lidaComErro((Exception)
                    intent.getSerializableExtra(RETORNO));

        }
    }

    public static EventosPublicacoesRecebidas registraObservador(BuscaMaisPublicacoesTask.BuscaMaisPublicacoesDelegate delegate){
        EventosPublicacoesRecebidas receiver = new EventosPublicacoesRecebidas();
        receiver.delegate = delegate;

        LocalBroadcastManager
                .getInstance(delegate.getCarangosApplication())
                .registerReceiver(receiver, new IntentFilter(PUBLICACOES_RECEBIDAS));

        return receiver;
    }

    public static void notifica(Context context, Serializable resultado, boolean sucesso){
        Serializable resultado, boolean sucesso;
    }
}
