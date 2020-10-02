package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button button;
    //make Sure That email and Password empty and Null

    private FirebaseAuth mAuth;

    //If The USer Has Its Account Already
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this,"Already SignIn",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }


    public void onRegister(View view)
    {
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();


        mAuth.createUserWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("Tag", "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Tag", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void onLogin(View view)
    {
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();

        mAuth.signInWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"Auth Success",Toast.LENGTH_SHORT).show();
                            Log.i("User","User: "+ user.toString());
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this,"Auth Failed",Toast.LENGTH_SHORT).show();
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void Logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this,"LOGOUT",Toast.LENGTH_SHORT).show();
    }

}
