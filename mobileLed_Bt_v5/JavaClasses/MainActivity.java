package br.com.local.mobileled_bt_v5;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public BluetoothConnectionManager bluetoothConnectionManager;
    public Led01 led01;
    public Led02 led02;
    public Led03 led03;
    public Led04 led04;
    public Led05 led05;

    public Button buttonConnect;
    public Button buttonLed01;
    public Button buttonLed02;
    public Button buttonLed03;
    public Button buttonLed04;
    public Button buttonLed05;

    private EditText macAddressEditText;

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        macAddressEditText = findViewById(R.id.macAddressEditText);
        macAddressEditText.addTextChangedListener(new MacAddressTextWatcher(macAddressEditText));

        bluetoothConnectionManager = new BluetoothConnectionManager(this, macAddressEditText);
        led01 = new Led01(this);
        led02 = new Led02(this);
        led03 = new Led03(this);
        led04 = new Led04(this);
        led05 = new Led05(this);

        buttonConnect = findViewById(R.id.buttonConnect);
        buttonLed01 = findViewById(R.id.buttonLed01);
        buttonLed02 = findViewById(R.id.buttonLed02);
        buttonLed03 = findViewById(R.id.buttonLed03);
        buttonLed04 = findViewById(R.id.buttonLed04);
        buttonLed05 = findViewById(R.id.buttonLed05);

        buttonConnect.setOnClickListener(v -> bluetoothConnectionManager.toggleBluetoothConnection());

        buttonLed01.setOnClickListener(v -> led01.toggleLed01());
        buttonLed02.setOnClickListener(v -> led02.toggleLed02());
        buttonLed03.setOnClickListener(v -> led03.toggleLed03());
        buttonLed04.setOnClickListener(v -> led04.toggleLed04());
        buttonLed05.setOnClickListener(v -> led05.toggleLed05());

        // Check and request Bluetooth permission if needed
        checkAndRequestBluetoothPermission();
    }

    private void checkAndRequestBluetoothPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    REQUEST_BLUETOOTH_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, continue with Bluetooth initialization
                bluetoothConnectionManager.initializeBluetooth();
            } else {
                // Permission denied by the user
                showToast("Bluetooth permission is required to use the app.");
                finish(); // Close the app if permission is denied
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothConnectionManager.disconnectBluetooth();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}