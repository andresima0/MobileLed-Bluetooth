package br.com.local.mobileled_bt_v5;

public class Led02 {
    private final MainActivity mainActivity;
    private int ledState = 2;

    public Led02(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void toggleLed02() {
        if (mainActivity.bluetoothConnectionManager.isConnected) {
            ledState = (ledState == 2) ? 3 : 2;
            updateButtonColor();
            mainActivity.bluetoothConnectionManager.sendBluetoothData(Integer.toString(ledState));
        } else {
            mainActivity.showToast("Not connected to ESP32.");
        }
    }

    public void updateButtonColor() {
        int colorLed = (ledState == 3) ? mainActivity.getResources().getColor(R.color.green) :
                mainActivity.getResources().getColor(R.color.gray);
        mainActivity.buttonLed02.setBackgroundColor(colorLed);
    }
}