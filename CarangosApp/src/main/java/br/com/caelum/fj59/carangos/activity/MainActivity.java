package br.com.caelum.fj59.carangos.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.adapter.PublicacaoAdapter;
import br.com.caelum.fj59.carangos.app.CarangosApplication;
import br.com.caelum.fj59.carangos.fragments.ListaDePublicacoesFragment;
import br.com.caelum.fj59.carangos.fragments.ProgressFragment;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.tasks.BuscaMaisPublicacoesTask;

public class MainActivity extends ActionBarActivity implements BuscaMaisPublicacoesTask.BuscaMaisPublicacoesDelegate {
    private ListView listView;
    private List<Publicacao> publicacoes;
    private PublicacaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.listView = (ListView) findViewById(R.id.publicacoes_list);
        this.publicacoes = new ArrayList<Publicacao>();
        this.adapter = new PublicacaoAdapter(this, this.publicacoes);

        this.listView.setAdapter(adapter);

        new BuscaMaisPublicacoesTask(this).execute();
    }


    @Override
    public void lidaComRetorno(List<Publicacao> retorno) {
        this.publicacoes.clear();
        this.publicacoes.addAll(retorno);
//        this.adapter.notifyDataSetChanged();

        ListaDePublicacoesFragment listaFragment = new ListaDePublicacoesFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_principal, listaFragment);
        ft.commit();
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

    public List<Publicacao> getPublicacoes() {
        return this.publicacoes;
    }
}
