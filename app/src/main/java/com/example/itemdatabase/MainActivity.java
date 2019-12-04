package com.example.itemdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editBarcode,editId,editName,editPrice,editBrand;
    Button btnAddData,btnDeleteData,btnUpdateData,btnViewData,btnShowAll;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        editBarcode=(EditText) findViewById(R.id.editText_barcode);
        editId=(EditText) findViewById(R.id.editText_id);
        editName=(EditText) findViewById(R.id.editText_name);
        editPrice=(EditText) findViewById(R.id.editText_price);
        editBrand=(EditText) findViewById(R.id.editText_brand);
        btnAddData=(Button) findViewById(R.id.button_add);
        btnDeleteData=(Button) findViewById(R.id.button_delete);
        btnUpdateData=(Button) findViewById(R.id.button_update);
        btnViewData=(Button) findViewById(R.id.button_view);
        btnShowAll=(Button) findViewById(R.id.button_showAll);
        AddData();
        DeleteData();
        UpdateData();
        viewAll();


    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted=mydb.insertData(editBarcode.getText().toString(),editId.getText().toString(),editName.getText().toString(),editPrice.getText().toString(),editBrand.getText().toString());
                        if (isInserted==true)
                            Toast.makeText(MainActivity.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"DATA not INSERTED",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    public void viewAll(){
        btnViewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=mydb.getAllData();
                        if (res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("barcode: "+res.getString(0)+"\n");
                            buffer.append("ID: "+res.getString(1)+"\n");
                            buffer.append("Name: "+res.getString(2)+"\n");
                            buffer.append("Price: "+res.getString(3)+"\n");
                            buffer.append("Brand: "+res.getString(4)+"\n\n");

                        }
                        showMessage("data found",buffer.toString());
                    }
                }
        );

    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void showAll(View view){
        Intent intent=new Intent(getApplicationContext(),DataListView.class);
        startActivity(intent);
    }
    public void DeleteData(){
        btnDeleteData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows=mydb.deleteData(editId.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(MainActivity.this,"DATA Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"DATA not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void showData(){
        btnShowAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }
    public void UpdateData(){
        btnUpdateData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated=mydb.updateAllData(editBarcode.getText().toString(),editId.getText().toString(),editName.getText().toString(),editPrice.getText().toString(),editBrand.getText().toString());

                        if (isUpdated==true)
                            Toast.makeText(MainActivity.this,"DATA Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"DATA not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
