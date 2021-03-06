package br.com.caelum.fj59.carangos.app;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.modelo.Publicacao;

/**
 * Created by android5501 on 20/10/15.
 */
public class CarangosApplication extends Application {
    private List<AsyncTask<?,?,?>> tasks = new ArrayList<AsyncTask<?,?,?>>();
    private List<Publicacao> publicacoes = new ArrayList<Publicacao>();

    @Override
    public void onTerminate() {
        super.onTerminate();

        for (AsyncTask task:this.tasks){
            task.cancel(true);
        }
    }

    public void registra(AsyncTask<?,?,?> task){
        tasks.add(task);
    }

    public void desregistra(AsyncTask<?,?,?> task){
        tasks.remove(task);
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }
}
