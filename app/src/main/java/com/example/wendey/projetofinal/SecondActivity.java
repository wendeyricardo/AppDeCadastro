package com.example.wendey.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private DBHelper dh;
    Button btvoltar, btInserir, btListar;
    EditText etNome, etCpf, etTelefone, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.dh = new DBHelper(this);

        etNome= (EditText) findViewById(R.id.etnome);
        etCpf= (EditText) findViewById(R.id.etcpf);
        etTelefone=(EditText) findViewById(R.id.ettelefone);
        etEmail=(EditText) findViewById(R.id.etemail);

        btInserir =(Button)findViewById(R.id.btinserir) ;
        btListar= (Button)findViewById(R.id.btlistar);

        btvoltar = (Button) findViewById(R.id.btvoltar);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNome.getText().length()>0 && etCpf.getText().length()>0 && etTelefone.getText().length()>0 && etEmail.getText().length()>0){
                  dh.insert(etNome.getText().toString(), etCpf.getText().toString(),etTelefone.getText().toString(), etEmail.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro Realizado");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
                else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Os campos não podem ficar em branco");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Pessoa> pessoas = dh.queryGetAll();
                if(pessoas == null){
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há registros cadastrados");
                    adb.show();
                    return;

                }
                for (int i=0; i<pessoas.size();i++){
                    Pessoa pessoa = (Pessoa) pessoas.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Registro "+i);
                    adb.setMessage("Nome: "+pessoa.getNome()+"\nCPF: "+pessoa.getCpf()+"\nTelefone: "+pessoa.getTelefone()+"\nEmail: "+pessoa.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();

                }
            }
        });

        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarParaPrimeiraTela();
            }
        });
    }
    void  voltarParaPrimeiraTela(){
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
