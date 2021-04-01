package com.example.agenda;

import android.widget.EditText;

public class Contact<editTextNameItem, editTextAddressItem, editTextPhoneItem> {
    EditText editTextNameItem;
    EditText editTextAddressItem;
    EditText editTextPhoneItem;
    String phoneType;

    String itemName = editTextNameItem.getText().toString();
    String itemAddress = editTextAddressItem.getText().toString();
    String itemPhone = editTextPhoneItem.getText().toString();
    String result = itemName + "; " + itemAddress + "; " + itemPhone + "; " + phoneType;

    public Contact(String itemName, String itemAddress, String itemPhone) {
        this.itemName = itemName;
        this.itemAddress = itemAddress;
        this.itemPhone = itemPhone;
    }

    public String getResult(){
        return result;
    }
}
