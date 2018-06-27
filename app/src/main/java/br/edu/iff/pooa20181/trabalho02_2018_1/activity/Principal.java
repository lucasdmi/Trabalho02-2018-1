package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;

public class Principal extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private String[] activities = {"ListaEleitor"};
    private String[] itemMenu = {"Eleitor"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemMenu);

        ListView listView = (ListView)findViewById(R.id.listaMenu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        Intent intent = null;

        try {
            Class obj =    Class.forName("br.edu.iff.pooa20181.trabalho02_2018_1.activity." + activities[position]);
            intent = new Intent(this, obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        intent = new Intent(this, ListaEleitor.class);
        startActivity(intent);
    }
}
