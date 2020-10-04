package com.example.trfcybersecuritydifficult;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Challenge {
    private static final String TAG = "CyberTruckChallenge";
    private String string = "";
    public Challenge() {
        string=generateKey();
    }

    protected byte[] generateDynamicKey(byte[] paramArrayOfbyte) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] arrayOfByte = "s3cr3t$_n3veR_mUst_bE_h4rdc0d3d_m4t3!".getBytes();
        SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(arrayOfByte));
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, secretKey);
        return cipher.doFinal(paramArrayOfbyte);
    }

    protected String generateKey() {
        byte[] arrayOfByte = "RoBo3oft_CTF_l3v3l_2".getBytes();
        String s = "";
        try {
            byte[] a = generateDynamicKey(arrayOfByte);
            s= Base64.encodeToString(a,Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        } catch (InvalidKeyException invalidKeyException) {

        } catch (InvalidKeySpecException invalidKeySpecException) {

        } catch (NoSuchPaddingException noSuchPaddingException) {

        } catch (BadPaddingException badPaddingException) {

        } catch (IllegalBlockSizeException illegalBlockSizeException) {}
        return s;
    }
    protected String getS(){
        return string;
    }
}
