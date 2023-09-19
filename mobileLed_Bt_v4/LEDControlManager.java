package br.com.local.mobileled_bt_v4;

public class LEDControlManager {

    private final MainActivity mainActivity;
    private boolean isLedOn = false;

    public LEDControlManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void toggleLED() {
        if (mainActivity.bluetoothConnectionManager.isConnected) {
            mainActivity.bluetoothConnectionManager.sendBluetoothData(isLedOn ? "0" : "1");
            isLedOn = !isLedOn;
            updateButtonColor();
        } else {
            mainActivity.showToast("Not connected to ESP32.");
        }
    }

    public void updateButtonColor() {
        int color = isLedOn ? mainActivity.getResources().getColor(R.color.green) :
                mainActivity.getResources().getColor(R.color.gray);
        mainActivity.buttonLed01.setBackgroundColor(color);
    }
}