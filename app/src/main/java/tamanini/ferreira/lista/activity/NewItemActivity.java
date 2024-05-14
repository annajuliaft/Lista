package tamanini.ferreira.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import tamanini.ferreira.lista.R;
import tamanini.ferreira.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    //Uri e um endereco para um dado nao localizado o dentro do espaco reservado a app, mas sim no espaco de outras apps
    //Guardando o endereço pelo seletor de documentos android

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        if(selectPhotoLocation != null) {
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
            imvPhotoPreview.setImageURI(selectPhotoLocation);
        }

        //obtendo botao e definindo o ouvidor de cliques
        ImageButton imgCI = findViewById(R.id.imbCI);
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override

            //executando a abertura da galeria
            public void onClick(View v) {
                //intent implicido
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //estamos interessados apenas em documentos com mimetype “image/*”
                photoPickerIntent.setType("image/*");
                //executando intent
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST);
            }

        });
        //obtendo botao
        Button btnAddItem = findViewById(R.id.btnAddItem);
        //setando ouvidor de cliques
        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            //verificando se os campos foram preenchidos pelo usuario
            public  void onClick(View v){

                NewItemActivityViewModel vm = new ViewModelProvider(NewItemActivity.this).get(NewItemActivityViewModel.class);
                Uri photoSelected = vm.getSelectPhotoLocation();

                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!" , Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();

                //caso os campos vazio, exibe a mensagem de erro
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                //mostra como a activity pode retornar dados para a activity que a chamou
                Intent i = new Intent(); //serve unicamente para guardar os dados a serem retornados
                i.setData(photoSelected); //setando o Uri da imagem escolhida dentro do intent
                //setando titulo e descricao
                i.putExtra("title",title);
                i.putExtra("description",description);
                //indicando resultado
                setResult(Activity.RESULT_OK, i);//RESULT_OK indica que há dados de retorno
                finish();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //referente ao fornecido na chamada com id
        if(requestCode == PHOTO_PICKER_REQUEST) {
            //codigo de sucesso
            if(resultCode == Activity.RESULT_OK) {
                Uri photoSelected = data.getData();
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);

                imvPhotoPreview.setImageURI(photoSelected);

                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                vm.setSelectPhotoLocation(photoSelected);

            }
        }
    }
}