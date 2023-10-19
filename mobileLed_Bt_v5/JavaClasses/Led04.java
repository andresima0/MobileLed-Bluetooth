package br.com.local.mobileled_bt_v5;

public class Led04 {
    private final MainActivity mainActivity;
    private int ledState = 6;

    public Led04(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void toggleLed04() {
        if (mainActivity.bluetoothConnectionManager.isConnected) {
            ledState = (ledState == 6) ? 7 : 6;
            updateButtonColor();
            mainActivity.bluetoothConnectionManager.sendBluetoothData(Integer.toString(ledState));
        } else {
            mainActivity.showToast("Not connected to ESP32.");
        }
    }

    public void updateButtonColor() {
        int colorLed = (ledState == 7) ? mainActivity.getResources().getColor(R.color.green) :
                mainActivity.getResources().getColor(R.color.gray);
        mainActivity.buttonLed04.setBackgroundColor(colorLed);
    }
}