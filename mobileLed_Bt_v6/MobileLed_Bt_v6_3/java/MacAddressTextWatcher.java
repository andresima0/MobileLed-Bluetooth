/**
 * A TextWatcher implementation for formatting MAC addresses in an EditText field.
 * This class formats the input text to follow the standard MAC address format (XX:XX:XX:XX:XX:XX).
 * It removes any existing separators, converts letters to uppercase, limits the length to 12 characters,
 * and inserts separators every two characters.
 */
package br.com.local.mobileled_bt_v6_3;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MacAddressTextWatcher implements TextWatcher {

    private static final int MAC_ADDRESS_LENGTH = 12; // Total length of the MAC address with mask
    private static final int MAC_ADDRESS_BLOCK_SIZE = 2; // Size of each block in the MAC address
    private static final char MAC_ADDRESS_SEPARATOR = ':'; // MAC address separator
    private boolean isFormatting; // Control variable to avoid infinite loops

    private final EditText editText;

    /**
     * Constructs a new MacAddressTextWatcher with the specified EditText field.
     *
     * @param editText The EditText field to apply the MAC address formatting.
     */
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

        // Mark that we are formatting the text
        isFormatting = true;

        // Remove all current separators (colons)
        String originalText = s.toString().replaceAll(":", "");

        // Convert letters to uppercase
        String formattedText = originalText.toUpperCase();

        // Limit the length to 12 characters (without separators)
        if (formattedText.length() > MAC_ADDRESS_LENGTH) {
            formattedText = formattedText.substring(0, MAC_ADDRESS_LENGTH);
        }

        // Reinsert separators every two characters
        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < formattedText.length(); i++) {
            finalText.append(formattedText.charAt(i));
            if ((i + 1) % MAC_ADDRESS_BLOCK_SIZE == 0 && i < formattedText.length() - 1) {
                finalText.append(MAC_ADDRESS_SEPARATOR);
            }
        }

        // Set the formatted text back into the input field
        editText.removeTextChangedListener(this);
        editText.setText(finalText);
        editText.setSelection(finalText.length());
        editText.addTextChangedListener(this);

        // Restore the control variable
        isFormatting = false;
    }
}