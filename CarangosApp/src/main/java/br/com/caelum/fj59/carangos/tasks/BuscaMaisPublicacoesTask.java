package br.com.caelum.fj59.carangos.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.fj59.carangos.activity.MainActivity;
import br.com.caelum.fj59.carangos.app.CarangosApplication;
import br.com.caelum.fj59.carangos.converter.PublicacaoConverter;
import br.com.caelum.fj59.carangos.evento.EventosPublicacoesRecebidas;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.webservice.Pagina;
import br.com.caelum.fj59.carangos.webservice.WebClient;

/**
 * Created by erich on 7/16/13.
 */
public class BuscaMaisPublicacoesTask extends AsyncTask<Pagina, Void, List<Publicacao>> {

    private BuscaMaisPublicacoesDelegate delegate;
    private Exception erro;
//    private MainActivity activity;
    private CarangosApplication application;

    public BuscaMaisPublicacoesTask(CarangosApplication activity) {
        this.application = application;
        this.application.registra(this);
    }

    @Override
    protected List<Publicacao> doInBackground(Pagina... paginas) {
        try {
            Pagina paginaParaBuscar = paginas.length > 1? paginas[0] : new Pagina();

            String jsonDeResposta = new WebClient("post/list?" + paginaParaBuscar).get();

            List<Publicacao> publicacoesRecebidas = new PublicacaoConverter().converte(jsonDeResposta);

            return publicacoesRecebidas;
        } catch (Exception e) {
            this.erro = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Publicacao> retorno) {
        MyLog.i("RETORNO OBTIDO!" + retorno);

        if (retorno!=null) {
            EventosPublicacoesRecebidas.notifica(this.application, (Serializable) retorno, true);
        } else {
            EventosPublicacoesRecebidas.notifica(this.application, (Serializable) retorno, false);
        }

        this.application.desregistra(this);
    }

    public interface BuscaMaisPublicacoesDelegate {
        void lidaComRetorno(List<Publicacao> retorno);
        void lidaComErro(Exception e);

        CarangosApplication getCarangosApplication();
    }

}
