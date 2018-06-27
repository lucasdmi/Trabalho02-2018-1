package br.edu.iff.pooa20181.trabalho02_2018_1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa20181.trabalho02_2018_1.R;
import br.edu.iff.pooa20181.trabalho02_2018_1.model.Candidato;
import io.realm.Realm;

public class CandidatoDetalhe extends AppCompatActivity {

    EditText edtNome, edtPartido, edtCargo, edtNumeroVotos, edtNumeroUrna, edtEstado, edtMunicipio;
    Button btAdicionar, btAlterar, btExcluir;



    Candidato candidato;
    int id;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_detalhe);

        edtNome = (EditText) findViewById(R.id.edtNomeCandidato);
        edtPartido = (EditText)findViewById(R.id.edtPartido);
        edtCargo = (EditText) findViewById(R.id.edtCargo);
        edtNumeroVotos = (EditText) findViewById(R.id.edtNumeroVotos);
        edtNumeroUrna = (EditText) findViewById(R.id.edtNumeroUrna);
        edtEstado = (EditText) findViewById(R.id.edtEstado);
        edtMunicipio = (EditText) findViewById(R.id.edtMunicipio);

        btAdicionar = (Button) findViewById(R.id.btAdicionar);
        btAlterar = (Button) findViewById(R.id.btAlterar);
        btExcluir = (Button) findViewById(R.id.btExcluir);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if(id != 0)
        {
            btAdicionar.setEnabled(false);
            btAdicionar.setClickable(false);
            btAdicionar.setVisibility(View.INVISIBLE);

            candidato = realm.where(Candidato.class).equalTo("id", id).findFirst();

            edtNome.setText(candidato.getNome());
            edtPartido.setText(candidato.getPartido());
            edtNumeroVotos.setText(candidato.getNumeroVotos());
            edtCargo.setText(candidato.getCargo());
            edtNumeroUrna.setText(candidato.getNumeroUrna());
            edtEstado.setText(candidato.getEstado());
            edtMunicipio.setText(candidato.getMunicipio());
        }
        else{
            btAlterar.setEnabled(false);
            btAlterar.setClickable(false);
            btAlterar.setVisibility(View.INVISIBLE);
            btExcluir.setEnabled(false);
            btExcluir.setClickable(false);
            btExcluir.setVisibility(View.INVISIBLE);
        }

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
            }
        });

        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterar();
            }
        });

    }
        public void excluir()
        {
            realm.beginTransaction();
            candidato.deleteFromRealm();
            realm.commitTransaction();
            realm.close();
            Toast.makeText(this, "Candidato Excluido", Toast.LENGTH_LONG).show();
            this.finish();

        }

    public void salvar()
    {
        int proximoID = 1;
        if(realm.where(Candidato.class).max("id") != null)
        {
            proximoID = realm.where(Candidato.class).max("id").intValue()+1;
        }

        realm.beginTransaction();
        Candidato candidato = new Candidato();
        candidato.setId(proximoID);

        setEgrava(candidato);


        realm.copyToRealm(candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Candidato Cadastrado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar()
    {
        realm.beginTransaction();
        setEgrava(candidato);

        realm.copyFromRealm(candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Candidato Alterado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void setEgrava(Candidato candidato)
    {
        candidato.setNome(edtNome.getText().toString());
        candidato.setPartido(edtPartido.getText().toString());
        candidato.setCargo(edtCargo.getText().toString());
        candidato.setNumeroVotos(edtNumeroVotos.getText().toString());
        candidato.setNumeroUrna(edtNumeroUrna.getText().toString());
        candidato.setEstado(edtEstado.getText().toString());
        candidato.setMunicipio(edtMunicipio.getText().toString());
    }




}
