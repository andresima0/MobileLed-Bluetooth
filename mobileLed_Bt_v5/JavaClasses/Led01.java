package br.com.local.mobileled_bt_v5;

public class Led01 {
    private final MainActivity mainActivity;
    private int ledState = 0;

    public Led01(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void toggleLed01() {
        if (mainActivity.bluetoothConnectionManager.isConnected) {
            ledState = (ledState == 0) ? 1 : 0;
            updateButtonColor();
            mainActivity.bluetoothConnectionManager.sendBluetoothData(Integer.toString(ledState));
        } else {
            mainActivity.showToast("Not connected to ESP32.");
        }
    }

    public void updateButtonColor() {
        int colorLed = (ledState == 1) ? mainActivity.getResources().getColor(R.color.green) :
                mainActivity.getResources().getColor(R.color.gray);
        mainActivity.buttonLed01.setBackgroundColor(colorLed);
    }
}