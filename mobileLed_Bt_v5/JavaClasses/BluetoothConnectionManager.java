package br.com.local.mobileled_bt_v5;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothConnectionManager {

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;
    private final MainActivity mainActivity;
    private final BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    public boolean isConnected = false;
    private final EditText macAddressEditText;
    private static final UUID UUID_BT = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public BluetoothConnectionManager(MainActivity mainActivity, EditText macAddressEditText) {
        this.mainActivity = mainActivity;
        this.macAddressEditText = macAddressEditText;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            showToast("Bluetooth not supported on this device.");
            mainActivity.finish();
        }
        macAddressEditText.addTextChangedListener(new MacAddressTextWatcher(macAddressEditText));
    }

    public void toggleBluetoothConnection() {
        if (!isConnected) {
            connectToBluetooth();
        } else {
            disconnectBluetooth();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, continue with Bluetooth initialization
                initializeBluetooth();
            } else {
                // Permission denied by the user
                showToast("Bluetooth permission is required to use the app.");
            }
        }
    }

    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() -> mainActivity.showToast(message));
    }

    public void initializeBluetooth() {
        if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.BLUETOOTH_CONNECT)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(mainActivity,
                        new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                        REQUEST_BLUETOOTH_PERMISSION);
            }
        } else {
            connectToBluetooth();
        }
    }

    private void connectToBluetooth() {
        String macAddress = macAddressEditText.getText().toString();
        if (macAddress.isEmpty()) {
            showToast("Please enter a MAC address.");
            return;
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);

        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID_BT);
            bluetoothSocket.connect();
            outputStream = bluetoothSocket.getOutputStream();
            isConnected = true;

            showToast("Connected to ESP32 via Bluetooth.");
            mainActivity.runOnUiThread(() -> mainActivity.buttonConnect.setText("Disconnect"));
            mainActivity.buttonLed01.setEnabled(true);
            mainActivity.buttonLed02.setEnabled(true);
            mainActivity.buttonLed03.setEnabled(true);
            mainActivity.buttonLed04.setEnabled(true);
            mainActivity.buttonLed05.setEnabled(true);
            mainActivity.led01.updateButtonColor();
            mainActivity.led02.updateButtonColor();
            mainActivity.led03.updateButtonColor();
            mainActivity.led04.updateButtonColor();
            mainActivity.led05.updateButtonColor();
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Failed to connect to ESP32 via Bluetooth.");
        } catch (SecurityException e) {
            e.printStackTrace();
            showToast("Bluetooth permission not granted.");
        }
    }

    public void disconnectBluetooth() {
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket.close();
                isConnected = false;
            }

            showToast("Disconnected from ESP32.");
            mainActivity.runOnUiThread(() -> mainActivity.buttonConnect.setText("Connect to ESP32"));
            mainActivity.buttonLed01.setEnabled(false);
            mainActivity.buttonLed02.setEnabled(false);
            mainActivity.buttonLed03.setEnabled(false);
            mainActivity.buttonLed04.setEnabled(false);
            mainActivity.buttonLed05.setEnabled(false);
            mainActivity.led01.updateButtonColor();
            mainActivity.led02.updateButtonColor();
            mainActivity.led03.updateButtonColor();
            mainActivity.led04.updateButtonColor();
            mainActivity.led05.updateButtonColor();
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Error disconnecting from ESP32.");
        }
    }

    public void sendBluetoothData(String data) {
        if (isConnected) {
            try {
                outputStream.write(data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                showToast("Error sending data to ESP32.");
            }
        } else {
            showToast("Not connected to ESP32.");
        }
    }
}