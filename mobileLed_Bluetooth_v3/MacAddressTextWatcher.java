package br.com.local.mobileled_bt_v3;

import android.text.Editable;
import android.text.TextWatcher;

class MacAddressTextWatcher implements TextWatcher {

    private static final int MAC_ADDRESS_LENGTH = 17; // Comprimento total do endereço MAC com máscara
    private static final int MAC_ADDRESS_BLOCK_SIZE = 2; // Tamanho de cada bloco do endereço MAC
    private static final char MAC_ADDRESS_SEPARATOR = ':'; // Separador do endereço MAC

    private boolean isFormatting; // Variável de controle para evitar loops infinitos

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Verifique se já estamos formatando o texto para evitar loops infinitos
        if (isFormatting) {
            return;
        }

        // Marque que estamos formatando o texto
        isFormatting = true;

        // Remova todos os separadores atuais (dois pontos)
        String originalText = s.toString();
        String formattedText = originalText.replaceAll(":", "");

        // Reinsira os separadores a cada dois caracteres
        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < formattedText.length(); i++) {
            finalText.append(formattedText.charAt(i));
            if ((i + 1) % MAC_ADDRESS_BLOCK_SIZE == 0 && i < formattedText.length() - 1) {
                finalText.append(MAC_ADDRESS_SEPARATOR);
            }
        }

        // Defina o texto formatado de volta no campo de entrada
        s.replace(0, s.length(), finalText);

        // Restaure a variável de controle
        isFormatting = false;
    }
}