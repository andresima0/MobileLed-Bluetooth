package br.com.local.mobileled_bt_v4;

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
    public LEDControlManager ledControlManager;
    public Button buttonConnect;
    public Button buttonLed01;
    private EditText macAddressEditText;

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        macAddressEditText = findViewById(R.id.macAddressEditText);
        macAddressEditText.addTextChangedListener(new MacAddressTextWatcher(macAddressEditText));

        bluetoothConnectionManager = new BluetoothConnectionManager(this, macAddressEditText);
        ledControlManager = new LEDControlManager(this);

        buttonConnect = findViewById(R.id.buttonConnect);
        buttonLed01 = findViewById(R.id.buttonLed01);

        buttonConnect.setOnClickListener(v -> bluetoothConnectionManager.toggleBluetoothConnection());

        buttonLed01.setOnClickListener(v -> ledControlManager.toggleLED());

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
