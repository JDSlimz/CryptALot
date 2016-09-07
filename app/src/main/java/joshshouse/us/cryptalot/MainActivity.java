package joshshouse.us.cryptalot;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scottyab.aescrypt.AESCrypt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define EditTexts
        final EditText encryptText = (EditText) findViewById(R.id.encryptText);
        final EditText keyText = (EditText) findViewById(R.id.keyText);
        final EditText decryptText = (EditText) findViewById(R.id.decryptText);

        //Define Character Count Views
        final TextView encryptCount = (TextView) findViewById(R.id.encryptCount);
        final TextView keyCount = (TextView) findViewById(R.id.keyCount);
        final TextView decryptCount = (TextView) findViewById(R.id.decryptCount);

        //Define Buttons
        final Button encryptBtn = (Button) findViewById(R.id.encryptBtn);
        final Button decryptBtn = (Button) findViewById(R.id.decryptBtn);

        //Update character count for text to encrypt
        encryptText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Integer encCount = encryptText.getText().length();
                encryptCount.setText(encCount + " Charachters");
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        //Update character count for key (has to be 16)
        keyText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Integer encCount = keyText.getText().length();
                keyCount.setText(encCount + " Charachters");

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        //Update character count for text to decrypt
        decryptText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Integer encCount = decryptText.getText().length();
                decryptCount.setText(encCount + " Charachters");

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        //Encrypt text from first text area on click.
        encryptBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String toEncrypt = encryptText.getText().toString();
                String key = keyText.getText().toString();

                if(toEncrypt.equals("")){
                    Toast.makeText(MainActivity.this, "Text to encrypt must not be empty!",
                            Toast.LENGTH_LONG).show();
                } else if(key.equals("")){
                    Toast.makeText(MainActivity.this, "Key must not be empty!",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        String encrypted = AESCrypt.encrypt(key, toEncrypt);
                        decryptText.setText(encrypted);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        //Decrypt text from third text area on click.
        decryptBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String toDecrypt = decryptText.getText().toString();
                String key = keyText.getText().toString();

                if(toDecrypt.equals("")){
                    Toast.makeText(MainActivity.this, "Text to decrypt must not be empty!",
                            Toast.LENGTH_LONG).show();
                } else if(key.equals("")){
                    Toast.makeText(MainActivity.this, "Key must not be empty!",
                            Toast.LENGTH_LONG).show();
                } else if(key.length() != 16) {
                    Toast.makeText(MainActivity.this, "Key must be exactly 16 characters!",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Log.v("To Decrypt", toDecrypt);
                        String decrypted = AESCrypt.decrypt(key, toDecrypt);
                        encryptText.setText(decrypted);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
}
