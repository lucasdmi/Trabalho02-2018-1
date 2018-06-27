package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Eleitor;
import io.realm.Realm;

public class EleitorDetalhe extends AppCompatActivity {

    EditText edtNome, edtNomeMae, edtData, edtNumeroTitulo, edtZona, edtSecao, edtMunicipio;

    Button btnAdicionar, btnAlterar, btnExcluir;

    int id;
    Eleitor eleitor;
    private Realm realm;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor_detalhe);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtNomeMae = (EditText) findViewById(R.id.edtNomeMae);
        edtData = (EditText) findViewById(R.id.edtData);
        edtNumeroTitulo = (EditText) findViewById(R.id.edtNumeroTitulo);
        edtSecao = (EditText) findViewById(R.id.edtSecao);
        edtZona = (EditText) findViewById(R.id.edtZona);
        edtMunicipio = (EditText) findViewById(R.id.edtMunicipio);

        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if(id != 0)
        {
            btnAdicionar.setEnabled(false);
            btnAdicionar.setClickable(false);
            btnAdicionar.setVisibility(View.INVISIBLE);

            eleitor = realm.where(Eleitor.class).equalTo("id", id).findFirst();

            edtNome.setText(eleitor.getNome());
            edtNomeMae.setText(eleitor.getNomeMae());

            edtData.setText(formato.format((Date) eleitor.getDataNascimento()));

            edtNumeroTitulo.setText(eleitor.getNumeroTitulo());
            edtZona.setText(eleitor.getZona());
            edtSecao.setText(eleitor.getSecao());
            edtMunicipio.setText(eleitor.getMunicipio());

        }
        else{
            btnAlterar.setEnabled(false);
            btnAlterar.setClickable(false);
            btnAlterar.setVisibility(View.INVISIBLE);
            btnExcluir.setEnabled(false);
            btnExcluir.setClickable(false);
            btnExcluir.setVisibility(View.INVISIBLE);
        }


        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterar();
            }
        });




    }

    public void excluir()
    {
        realm.beginTransaction();
        eleitor.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        Toast.makeText(this, "Eleitor Excluido", Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar()
    {
        int proximoID = 1;
        if(realm.where(Eleitor.class).max("id") != null)
        {
            proximoID = realm.where(Eleitor.class).max("id").intValue()+1;
        }

        realm.beginTransaction();
        Eleitor eleitor = new Eleitor();
        eleitor.setId(proximoID);

        setEgrava(eleitor);


        realm.copyToRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Eleitor Cadastrado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar()
    {
        realm.beginTransaction();
        setEgrava(eleitor);

        realm.copyFromRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Eleitor Alterado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void setEgrava(Eleitor eleitor)
    {
        eleitor.setNome(edtNome.getText().toString());
        eleitor.setNomeMae(edtNomeMae.getText().toString());

        try {
            eleitor.setDataNascimento((Date) formato.parse(edtData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        eleitor.setNumeroTitulo(edtNumeroTitulo.getText().toString());
        eleitor.setZona(edtZona.getText().toString());
        eleitor.setSecao(edtSecao.getText().toString());
        eleitor.setMunicipio(edtMunicipio.getText().toString());
    }

}
