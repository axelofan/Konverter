package ru.marchenko.konvert;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static ru.marchenko.konvert.Round.round;

public class TemperatureFragment extends Fragment{

    private EditText input;
    private Spinner tempSp;
    private TextView output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = (EditText) view.findViewById(R.id.input);
        tempSp = (Spinner) view.findViewById(R.id.grad);
        output = (TextView) view.findViewById(R.id.output);

        tempSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertTemp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                convertTemp();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void convertTemp() {
        double c = 0;
        String grad = tempSp.getSelectedItem().toString();
        try {
            c = Double.parseDouble(input.getText().toString());
            if ("Кельвин".equals(grad)) c = c - 273.15;
            else if ("Ранкин".equals(grad)) c = c + 497.37;
            else if ("Реамюр".equals(grad)) c = c * 1.25;
            else if ("Фаренгейт".equals(grad)) c = (c - 32) / 1.8;

            output.setText(round(c) +"Цельсий\n"
                    + round(c+273.15) + " Кельвин\n"
                    + round(c-497.37) + " Ранкин\n"
                    + round(c/1.25) + " Реамюр\n"
                    + round(c*1.8+32)+ " Фаренгейт\n");
        } catch (NumberFormatException e) {
            }
    }
}