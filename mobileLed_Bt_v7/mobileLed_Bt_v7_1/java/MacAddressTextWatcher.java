package br.com.local.mobileled_v7_1;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MacAddressTextWatcher implements TextWatcher {

    private static final int MAC_ADDRESS_LENGTH = 12;
    private static final int MAC_ADDRESS_BLOCK_SIZE = 2;
    private static final char MAC_ADDRESS_SEPARATOR = ':';
    private boolean isFormatting;

    private final EditText editText;

    public MacAddressTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // No action needed before text changed
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // No action needed during text changed
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Check if we are already formatting the text to avoid infinite loops
        if (isFormatting) {
            return;
        }

        isFormatting = true;

        String originalText = s.toString().replaceAll(":", "");

        String formattedText = originalText.toUpperCase();

        if (formattedText.length() > MAC_ADDRESS_LENGTH) {
            formattedText = formattedText.substring(0, MAC_ADDRESS_LENGTH);
        }

        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < formattedText.length(); i++) {
            finalText.append(formattedText.charAt(i));
            if ((i + 1) % MAC_ADDRESS_BLOCK_SIZE == 0 && i < formattedText.length() - 1) {
                finalText.append(MAC_ADDRESS_SEPARATOR);
            }
        }

        editText.removeTextChangedListener(this);
        editText.setText(finalText);
        editText.setSelection(finalText.length());
        editText.addTextChangedListener(this);

        isFormatting = false;
    }
}