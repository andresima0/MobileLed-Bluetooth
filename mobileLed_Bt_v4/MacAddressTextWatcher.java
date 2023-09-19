package br.com.local.mobileled_bt_v4;

import android.text.Editable;
import android.text.TextWatcher;

class MacAddressTextWatcher implements TextWatcher {

    private static final int MAC_ADDRESS_BLOCK_SIZE = 2;
    private static final char MAC_ADDRESS_SEPARATOR = ':';

    private boolean isFormatting;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isFormatting) {
            return;
        }

        isFormatting = true;

        String originalText = s.toString();
        String formattedText = originalText.replaceAll(":", "");

        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < formattedText.length(); i++) {
            finalText.append(formattedText.charAt(i));
            if ((i + 1) % MAC_ADDRESS_BLOCK_SIZE == 0 && i < formattedText.length() - 1) {
                finalText.append(MAC_ADDRESS_SEPARATOR);
            }
        }

        s.replace(0, s.length(), finalText);

        isFormatting = false;
    }
}