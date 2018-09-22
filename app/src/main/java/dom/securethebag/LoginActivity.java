package dom.securethebag;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.login_activity);
        final EditText usernameinput = (EditText) findViewById(R.id.username_input);
        final EditText user_password = (EditText) findViewById(R.id.password_input);

        Button btn = (Button) this.findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameinput.getText().toString().equals("dominik") && user_password.getText().toString().equals("poopybutt")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button reg = (Button)  findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //Set login button disalbed if length requirements not met

        usernameinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Button loginbtn = (Button) findViewById(R.id.login);
                Resources resources = LoginActivity.this.getResources();
                EditText userpasswordinput = (EditText) findViewById(R.id.password_input);

                if (s.length() > 4 && userpasswordinput.getText().length() > 8){
                    Drawable drawable = resources.getDrawable(R.color.colorPrimary);
                    loginbtn.setBackground(drawable);
                    loginbtn.setEnabled(true);
                }else{
                    Drawable drawable = resources.getDrawable(R.color.disabled);
                    loginbtn.setBackground(drawable);
                    loginbtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        user_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Button loginbtn = (Button) findViewById(R.id.login);
                Resources resources = LoginActivity.this.getResources();
                EditText usernameinput = (EditText) findViewById(R.id.username_input);

                if (s.length() > 8 && usernameinput.getText().length() > 4){
                    Drawable drawable = resources.getDrawable(R.color.colorPrimary);
                    loginbtn.setBackground(drawable);
                    loginbtn.setEnabled(true);
                }else{
                    Drawable drawable = resources.getDrawable(R.color.disabled);
                    loginbtn.setBackground(drawable);
                    loginbtn.setEnabled(false);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    private boolean userValidated() {


        return true;
    }


}