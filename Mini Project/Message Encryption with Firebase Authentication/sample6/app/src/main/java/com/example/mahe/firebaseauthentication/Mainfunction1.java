package com.example.mahe.firebaseauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;


import com.google.firebase.auth.FirebaseAuth;
import com.scottyab.aescrypt.AESCrypt;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class Mainfunction1 extends AppCompatActivity implements View.OnClickListener{

    private EditText plain;
    private Button encrypt;
    private Button decrypt;
    private Button logout;

    private EditText decrypted;
    private EditText md5;
    private EditText DES;
    private EditText RSA1;

    private final static String RSA = "RSA";

    public static PublicKey uk;

    public static PrivateKey rk;

    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfunction1);

        progressDialog=new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }

        //Processed Text will Displayed here.
        md5=(EditText)findViewById(R.id.editText8);
        DES=(EditText)findViewById(R.id.editText6);
        decrypted=(EditText)findViewById(R.id.editText7);
        //RSA-Edit Text for the RSA Algorithm.
        RSA1=(EditText)findViewById(R.id.editText9);

        plain=(EditText)findViewById(R.id.editText5);

        encrypt=(Button)findViewById(R.id.button5);
        decrypt=(Button)findViewById(R.id.button6);
        logout=( Button)findViewById(R.id.button4);

        logout.setOnClickListener(this);
        encrypt.setOnClickListener(this);
        decrypt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == logout){
            progressDialog.setMessage("Signing out...");
            progressDialog.show();
            firebaseAuth.signOut();
            progressDialog.dismiss();
            finish();
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        }
        if(view == encrypt){
            progressDialog.setMessage("Encrypting data...");
            progressDialog.show();
            AESencryptmessage();
            DESencryptmessage();
            //It is not working Properly
            //MD5encryptmessage();
            //rSA Encryption
            try {
                generateKey();
            } catch (Exception e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
        if(view == decrypt){
            progressDialog.setMessage("Decrypting data...");
            progressDialog.show();
            AESdecryptmessage();
            DESdecryptmessage();
            progressDialog.dismiss();
            MD5decryptmessage();
            //progressDialog.dismiss();
        }
    }

    private void MD5decryptmessage() {
        String cryptoPass="and3rS3fh";
        String message=plain.getText().toString().trim();
        try {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("MD5");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] encrypedPwdBytes = Base64.decode(message, Base64.DEFAULT);
            // cipher is not thread safe
            Cipher cipher = Cipher.getInstance("MD5");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));
            //decrypted Text value
            String decrypedValue = new String(decrypedValueBytes);

            md5.setText(""+decrypedValue);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private void MD5encryptmessage() {
        String message=plain.getText().toString().trim();
        String encryptedMsg = null;
        String cryptoPass="and3rS3fh";
        try {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("MD5");
            //here it will generate the Secret Key By using the cryptopassword
            SecretKey key = keyFactory.generateSecret(keySpec);
            //Here message or plain text will be Converted to respective UTF-8 characters.
            byte[] clearText = message.getBytes("UTF8");
            // Creater ciper with Algorithm specified in the initilization
            Cipher cipher = Cipher.getInstance("MD5");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
            md5.setText(""+encrypedValue);
        } catch (InvalidKeyException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (InvalidKeySpecException e) {
        e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (BadPaddingException e) {
        e.printStackTrace();
    } catch (NoSuchPaddingException e) {
        e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
        e.printStackTrace();
    }

    }

    private void AESencryptmessage() {
        String message=plain.getText().toString().trim();
        String password="ef090 tyuio qaki8 ed4rt tyu89 ftn90 2451i anand chintam";

        String encryptedMsg = null;
        try {
            encryptedMsg = AESCrypt.encrypt(password, message);
        }catch (GeneralSecurityException e){
            Toast.makeText(getApplicationContext(),"Error in Encryption "+e,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        progressDialog.dismiss();
        decrypted.setText(""+encryptedMsg);
    }

    private void AESdecryptmessage() {
        String message=plain.getText().toString().trim();
        String password="ef090 tyuio qaki8 ed4rt tyu89 ftn90 2451i anand chintam";
        String messageAfterDecrypt = null;
        try {
            messageAfterDecrypt = AESCrypt.decrypt(password, message);
        }
        catch (GeneralSecurityException e){
            fail("error occurred during Decrypt");
            e.printStackTrace();
        }
        decrypted.setText(""+messageAfterDecrypt);
    }



    private void DESencryptmessage(){
        String message=plain.getText().toString().trim();
        //String password="ef090 tyuio qaki8 ed4rt tyu89 ftn90 2451i anand chintam";
        //String encryptedMsg = null;

        try{
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();
            Cipher desCipher;
            // Create the cipher
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

            //sensitive information
            byte[] text = message.getBytes();
            // Encrypt the text
            byte[] textEncrypted = desCipher.doFinal(text);
            DES.setText(textEncrypted.toString());
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(NoSuchPaddingException e){
            e.printStackTrace();
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }catch(IllegalBlockSizeException e){
            e.printStackTrace();
        }catch(BadPaddingException e){
            e.printStackTrace();
        }
    }
    private void DESdecryptmessage() {
        String message=plain.getText().toString().trim();
        try{
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = keygenerator.generateKey();
         Cipher desCipher ;
        // Create the cipher
        desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // Initialize the same cipher for decryption\
         desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        byte[] text = message.getBytes();
        // Decrypt the text
        byte[] textDecrypted = desCipher.doFinal(text);
        DES.setText(textDecrypted.toString());
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(NoSuchPaddingException e){
            e.printStackTrace();
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }catch(IllegalBlockSizeException e){
            e.printStackTrace();
        }catch(BadPaddingException e){
            e.printStackTrace();
        }
    }

    public void generateKey() throws Exception {
        String text=RSA1.getText().toString();
        KeyPairGenerator gen = KeyPairGenerator.getInstance(RSA);
        gen.initialize(512, new SecureRandom());
        //keys are Generated using keyPair Generator
        KeyPair keyPair = gen.generateKeyPair();
        //public key -->uk
        uk = keyPair.getPublic();
        //private Key will be stored in rk
        rk = keyPair.getPrivate();
        RSAencrypt(text,uk);
    }

    private void RSAencrypt(String text, PublicKey pubRSA) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubRSA);
        byte[] textEncrypted =cipher.doFinal(text.getBytes());
        RSA1.setText(textEncrypted.toString());

    }
}
