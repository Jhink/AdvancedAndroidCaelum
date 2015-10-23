package br.com.caelum.fj59.carangos.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.adapter.PublicacaoAdapter;
import br.com.caelum.fj59.carangos.app.CarangosApplication;
import br.com.caelum.fj59.carangos.evento.EventosPublicacoesRecebidas;
import br.com.caelum.fj59.carangos.fragments.ListaDePublicacoesFragment;
import br.com.caelum.fj59.carangos.fragments.ProgressFragment;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.navegacao.EstadoMainActivityBase;
import br.com.caelum.fj59.carangos.navegacao.EstadoMainActivityInicio;
import br.com.caelum.fj59.carangos.navegacao.EstadoMainActivityPrimeirasPublicacoesRecebidas;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesTask;

public class MainActivity extends ActionBarActivity implements BuscaMaisPublicacoesTask.BuscaMaisPublicacoesDelegate {
    private ListView listView;
    private List<Publicacao> publicacoes;
    private PublicacaoAdapter adapter;

    private EstadoMainActivityBase estado;
    private static final String ESTADO_ATUAL = "ESTADO_ATUAL";
//    private EventoPublicacoesRecebidas receiver;

//    Guardando o evento como atributo
    private EventosPublicacoesRecebidas evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.estado = new EstadoMainActivityInicio();

//        Primeiro registrando a Activity como observador
        this.evento = EventosPublicacoesRecebidas.registraObservador(this);

        this.listView = (ListView) findViewById(R.id.publicacoes_list);
        this.publicacoes = new ArrayList<Publicacao>();
        this.adapter = new PublicacaoAdapter(this, this.publicacoes);

        this.listView.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MyLog.i("SALVANDO ESTADO!");

        outState.putSerializable(ESTADO_ATUAL, this.estado);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        MyLog.i("EXECUTANDO ESTADO: " + this.estado);
        this.estado.executa(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyLog.i("EXECUTANDO ESTAdO: " + this.estado);
        this.estado.executa(this);
    }

    @Override
    public void lidaComRetorno(List<Publicacao> retorno) {
        this.publicacoes.clear();
        this.publicacoes.addAll(retorno);
//        this.adapter.notifyDataSetChanged();
        this.estado = new EstadoMainActivityPrimeirasPublicacoesRecebidas();
        this.estado.executa(this);
    }

    @Override
    public void lidaComErro(Exception e) {
        e.printStackTrace();
        Toast.makeText(this, "Erro ao buscar dados", Toast.LENGTH_LONG).show();
    }

    @Override
    public CarangosApplication getCarangosApplication() {
        return (CarangosApplication) getApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.evento.desregistra(getCarangosApplication());
    }

    public void alteraEstadoEExecuta(EstadoMainActivityBase estado){
        this.estado = estado;
        this.estado.executa(this);
    }

    public void buscaPublicacoes(){
        new BuscaMaisPublicacoesTask(getCarangosApplication()).execute();
    }

    public List<Publicacao> getPublicacoes() {
        return this.publicacoes;
    }
}
