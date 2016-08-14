package com.photoshotlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.photoshotlist.dal.PSLDatabaseHelper;
import com.photoshotlist.dal.ShotListDAO;

public class NewShotListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shot_list);
    }

    public void onClickNewShotlist(View view){
        EditText shotListName = null;
        EditText shotListDescription = null;
        TextView errorMessage = null;
        Cursor cursor = null;
        SQLiteDatabase db = null;
        ShotListDAO dao = null;

        try
        {
            shotListName = (EditText)findViewById(R.id.editTextShotListName);
            shotListDescription = (EditText)findViewById(R.id.editTextShotListDescription);
            errorMessage = (TextView)findViewById(R.id.textViewErrorMessage);

            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this);
            dao = dbHelper.InsertShotlist(shotListName.getText().toString(), shotListDescription.getText().toString());

            if(dao == null)
                errorMessage.setText(String.format("An error occurred: %s", "TODO: add a user friendly error message."));
        }
        catch(Exception e){
            errorMessage.setText(String.format("An error occurred: %s", e.getMessage()));
        }
        finally {
            if(cursor != null)
                cursor.close();

            if(db != null)
                db.close();
        }
    }
}
