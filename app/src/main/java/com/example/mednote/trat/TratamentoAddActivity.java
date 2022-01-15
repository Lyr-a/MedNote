package com.example.mednote.trat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mednote.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TratamentoAddActivity extends AppCompatActivity {

    static int RESULT_TAKE_PICTURE = 1;
    static int RESULT_REQUEST_PERMISSION = 2;
    List<String> photos = new ArrayList<>();
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamento_add);

        List<String> permissions = new ArrayList<>();

        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        checkForPermissions(permissions);


        //region VIEWS

        FloatingActionButton FbtnTratamentoAdd = findViewById(R.id.FbtnTratamentoAdd);
        FloatingActionButton FbtnPhotoAdd = findViewById(R.id.FbtnPhotoAdd);


        //endregion

        //region BUTTONS

        FbtnPhotoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(photos.size()<5){
                    dispatchTakePictureIntent();
                }
                else{
                    Toast.makeText(TratamentoAddActivity.this, "Você atingiu o limite de fotos por tratamento", Toast.LENGTH_LONG).show();
                }
            }
        });

        FbtnTratamentoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText EtTratamentoAddDesc = findViewById(R.id.EtTratamentoAddDesc);
                EditText EtTratamentoAddTitle = findViewById(R.id.EtTratamentoAddTitle);
                String Hora = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                String Dia = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                String TraTitulo = EtTratamentoAddTitle.getText().toString();
                if (TraTitulo.isEmpty()) {
                    Toast.makeText(TratamentoAddActivity.this, "Você Precisa inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TraTitulo.length() > 20){
                    Toast.makeText(TratamentoAddActivity.this, "O título pode ter 20 letras no máximo", Toast.LENGTH_SHORT).show();
                    return;
                }

                String TraDesc = EtTratamentoAddDesc.getText().toString();
                if (TraDesc.isEmpty()) {
                    Toast.makeText(TratamentoAddActivity.this, "Você Precisa inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("TraTitle", TraTitulo);
                intent.putExtra("TraDesc", TraDesc);
                intent.putExtra("TraHora", Hora);
                intent.putExtra("TraDia", Dia);
                intent.putStringArrayListExtra("TraPhotos", (ArrayList<String>) photos);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

        //region FOTO

        public void dispatchTakePictureIntent(){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            File f = null;

            try {
                f = createFileImage();
            } catch (IOException e) {
                Toast.makeText(TratamentoAddActivity.this, "Não foi possível criar um arquivo", Toast.LENGTH_SHORT).show();
                return;
            }

            currentPhotoPath = f.getAbsolutePath();

            if(f != null){
                Uri fURi = FileProvider.getUriForFile(TratamentoAddActivity.this, "com.example.mednote.fileprovider", f);
                i.putExtra(MediaStore.EXTRA_OUTPUT, fURi);
                startActivityForResult(i, RESULT_TAKE_PICTURE);
            }

        }

        private File createFileImage() throws IOException {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageName = "JPEG_" + timeStamp;
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File f = File.createTempFile(imageName, ".jpg", storageDir);
            return f;
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RESULT_TAKE_PICTURE){
                if (resultCode == Activity.RESULT_OK){
                    photos.add(currentPhotoPath);
                }
                else{
                    File f = new File(currentPhotoPath);
                    f.delete();
                }
            }
        }

        private void checkForPermissions(List<String> permissions){

            List<String> permissionsNotGranted = new ArrayList<>();

            for (String permission : permissions){

                if(!hasPermission(permission)){
                    permissionsNotGranted.add(permission);
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(permissionsNotGranted.size() > 0){
                    requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]), RESULT_REQUEST_PERMISSION);
                }
            }

        }

        private boolean hasPermission(String permission){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                return ActivityCompat.checkSelfPermission(TratamentoAddActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
            }
            return false;
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            List<String> permissionsRejected = new ArrayList<>();
            if (requestCode == RESULT_REQUEST_PERMISSION){
                for (String permission : permissions){
                    if (!hasPermission(permission)){
                        permissionsRejected.add(permission);
                    }
                }
            }

            if (permissionsRejected.size() > 0){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(shouldShowRequestPermissionRationale(permissionsRejected.get(0))){
                        new AlertDialog.Builder(TratamentoAddActivity.this).
                                setMessage("Para usar esse app é necessário conceder essas permissões").
                                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), RESULT_REQUEST_PERMISSION);
                                    }
                                }).create().show();
                    }
                }
            }

        }

        //endregion

    }