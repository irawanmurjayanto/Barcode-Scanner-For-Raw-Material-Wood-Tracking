package com.example.barcodsawmillnew_scanzebra;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatBarcodeTia_DelGroup_Sub extends AppCompatActivity {
    TextView GroupDel,LinkNoDel;
    Button butDelGroup;
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;
    private static final String DATABASE_NAME = "master.db";
    SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barcode_tia__del_group__sub);

        GroupDel=findViewById(R.id.textGroup_del);
        LinkNoDel=findViewById(R.id.textLink_del);
        butDelGroup=findViewById(R.id.butDelGroup);

        adb_master=new ADB_Master(getBaseContext());
        adb_trans = new ADB_Trans(getBaseContext());
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        Intent a=getIntent();

        GroupDel.setText(a.getStringExtra("group_del"));
        LinkNoDel.setText(String.valueOf(a.getStringExtra("idnolink_del")));

        butDelGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = adb_master.getWritableDatabase();
                String del1 = "delete from tbl_newbarcode where idno_link2='"+LinkNoDel.getText().toString()+"'";
                db.execSQL(del1);

                String del2 = "delete from tbl_headerbarcode where idno_link2='"+LinkNoDel.getText().toString()+"'";
                db.execSQL(del2);

                finish();
            }
        });


    }
}