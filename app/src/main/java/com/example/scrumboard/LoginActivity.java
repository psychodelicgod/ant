package com.example.scrumboard;

import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Member;
import com.example.scrumboard.server.GetData;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends Activity {

    private DataSource db;
	
    private EditText email;
    private EditText password;
    private Button loginBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_login);

	    db = new DataSource(getApplicationContext());
        GetData data =  new GetData();
        data.getAllMembers(); //TODO nie wiem, zapisuje sie lokalnie w GetData
	    data.addMember(new Member(5,"Adrzej","Nowak","nowak@gmail.com","nowak123")); //TODO dziala dodawanie ludzi

	    email = (EditText) findViewById(R.id.edittext_login_email);
	    password = (EditText) findViewById(R.id.edittext_login_password);
	    loginBtn = (Button) findViewById(R.id.button_login);
	    
        loginBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                
                handleLogin();
                
            }
        });
	}
	
    protected void handleLogin() {
        
        Member memberNew = new Member();
        memberNew.setPassword(password.getText().toString());
        memberNew.setMail(email.getText().toString());
        db.open();
        if(db.verifyMember(memberNew))
        	Toast.makeText(getApplicationContext(), "Login succesfull", Toast.LENGTH_LONG).show();
        else
        	Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
        db.close();
    }
       
        

}
