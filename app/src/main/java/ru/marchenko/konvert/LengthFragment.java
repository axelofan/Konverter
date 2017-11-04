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

public class LengthFragment extends Fragment {

    private EditText input;
    private Spinner lengthSp;
    private TextView output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_length, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = (EditText) view.findViewById(R.id.input);
        lengthSp = (Spinner) view.findViewById(R.id.length);
        output = (TextView) view.findViewById(R.id.output);

        lengthSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertLength();
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
                convertLength();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void convertLength() {
        double c = 0;
        String length = lengthSp.getSelectedItem().toString();
        try {
            c = Double.parseDouble(input.getText().toString());
            if ("Метр".equals(length)) c = c * 1;
            else if ("Ангстрем".equals(length)) c = c * 0.0000000001;
            else if ("Миллиметр".equals(length)) c = c * 0.001;
            else if ("Сантиметр".equals(length)) c = c * 0.01;
            else if ("Дециметр".equals(length)) c = c * 0.1;
            else if ("Километр".equals(length)) c = c * 1000;
            else if ("Дюйм".equals(length)) c = c * 0.0254;
            else if ("Фут".equals(length)) c = c * 0.3048;
            else if ("Кабельтов".equals(length)) c = c * 185.2;
            else if ("Миля".equals(length)) c = c * 1609.344;
            else if ("Морская миля".equals(length)) c = c * 1852;
            else if ("Лье".equals(length)) c = c * 5557;

            output.setText(round(c) + " Метр\n"
                    + round(c /0.0000000001) +" Ангстрем\n"
                    + round(c /0.001)+" Миллиметр\n"
                    + round(c /0.01)+" Сантиметр\n"
                    + round(c /0.1)+" Дециметр\n"
                    + round(c /1000 )+ "Километр\n"
                    + round(c /0.0254)+" Дюйм\n"
                    + round(c /0.3048)+" Фут\n"
                    + round(c /185.2)+" Кабельтов\n"
                    + round(c /1609.344)+" Миля\n"
                    + round(c /1852)+" Морская миля\n"
                    + round(c /5557 )+" Лье\n");
        } catch (NumberFormatException e) {
        }
    }
}
