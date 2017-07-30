package quetzalsoft.berserkr.qm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.bitcoinj.core.NetworkParameters;

public class UserInformationActivity extends AppCompatActivity {

    private QMKeyManager manager;
    private TextView mEmailView;
    private TextView mAddressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String email = getIntent().getExtras().getString("email");

        // Set up the login form.
        mEmailView = (TextView) findViewById(R.id.emailString);
        mAddressView = (TextView) findViewById(R.id.addressString);

        // stranger things
        manager = QMKeyManager.getInstance();

        mEmailView.setText(email);
        mAddressView.setText(manager.getPublicKeyAsHex(manager.getPrivateKey()));
    }

}
