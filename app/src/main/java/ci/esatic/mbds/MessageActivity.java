package ci.esatic.mbds;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MessageActivity extends AppCompatActivity {

    private EditText phone;
    private EditText message;
    private Button envoi;
    private ScrollView scrollView;
    private TextView messageTextView;
    private String lastPhoneNumber;

    static int PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        accordPermission();
        initActivity();
    }

    /**
     * Accorder les permissions
     */
    private void accordPermission(){
        if (ContextCompat.checkSelfPermission(MessageActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MessageActivity.this,new String[]{Manifest.permission.SEND_SMS},PERMISSION_CODE);
        }
    }
    /**
     * Initialisation au des variable au lieu d'utiliser 'this' dans la methode onCreate
     */
    private void initActivity(){
        phone = (EditText) findViewById(R.id.txtPhone);
        message = (EditText) findViewById(R.id.txtMessage);
        envoi = (Button) findViewById(R.id.btnEnvoi);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        messageTextView = (TextView) findViewById(R.id.messagesTextView);

        //gestion des évènement d'envoi de message
        createOnClickEnvoyButton();
    }
    /**
     *Clic sur le bouton envoi, envoi de sms
     */
    private void createOnClickEnvoyButton(){
        envoi.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String phoneNumber = phone.getText().toString();
                String messageText = message.getText().toString();
                if (phoneNumber.isEmpty() || messageText.isEmpty()) {
                    Toast.makeText(MessageActivity.this, "Veuillez saisir quelque chose dans les champs", Toast.LENGTH_SHORT).show();
                } else {
                    SmsManager.getDefault().sendTextMessage(
                            phoneNumber,
                            null,
                            messageText,
                            null,
                            null
                    );
                    lastPhoneNumber = phoneNumber;
                    addMessageToScrollView("Moi: " + messageText);
                    phone.setText("");
                    message.setText("");
                }
            }
        });
    }
    /**
     * Ajoute un message à la liste des messages dans le ScrollView
     */
    private void addMessageToScrollView(String message){
        //messageTextView.append("\n " + message);
        //messageTextView.append("\n " + " envoyé au "+ lastPhoneNumber);
        String fullMessage = "<br/>" + message + "<br/><small>envoyé au " + lastPhoneNumber + "</small>";
        messageTextView.append(Html.fromHtml(fullMessage));
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
}