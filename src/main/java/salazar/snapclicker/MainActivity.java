package salazar.snapclicker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.net.UnknownHostException;

import static salazar.snapclicker.SaveToDb.*;


public class MainActivity extends ActionBarActivity {
    EditText editText_last_name;
    EditText editText_phone;
    EditText editText_email;
    EditText editText_fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_fname = (EditText) findViewById(R.id.editText_fname);
        editText_last_name = (EditText) findViewById(R.id.editText_last_name);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_phone = (EditText) findViewById(R.id.editText_phone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void saveContact(View v) throws UnknownHostException {

        Students contact = new Students();
        contact.first_name = editText_fname.getText().toString();
        contact.last_name = editText_last_name.getText().toString();
        contact.email = editText_email.getText().toString();
        contact.phone = editText_phone.getText().toString();



        SaveToDb tsk = new SaveToDb();
        tsk.execute(contact);

    }
}
