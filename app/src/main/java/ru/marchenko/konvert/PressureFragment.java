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

public class PressureFragment extends Fragment{

    private EditText input;
    private Spinner pressSp;
    private TextView output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pressure, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = (EditText) view.findViewById(R.id.input);
        pressSp = (Spinner) view.findViewById(R.id.press);
        output = (TextView) view.findViewById(R.id.output);
        pressSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertPressure();
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
            convertPressure();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void convertPressure() {
        double c = 0;
        String pressure = pressSp.getSelectedItem().toString();
        try {
            c = Double.parseDouble(input.getText().toString());
            if ("Паскаль (Pa, Па)".equals(pressure)) c = c * 1;
            else if ("Гектопаскаль (hPa, гПа)".equals(pressure)) c = c * 100;
            else if ("Килопаскаль (kPa, кПа)".equals(pressure)) c = c * 1000;
            else if ("Бар".equals(pressure)) c = c * 100000;
            else if ("Мегапаскаль (MPa, МПа)".equals(pressure)) c = c * 1000000;
            else if ("Миллибар".equals(pressure)) c = c * 0.01;
            else if ("Килограмм силы на квадратный сантиметр (kgf/cm2)".equals(pressure)) c = c * 9.80664999999998;
            else if ("Килограмм силы на квадратный метр (kgf/m2)".equals(pressure)) c = c * 0.101971621297793;

            output.setText(round(c) + " Па\n"
                    + round(c /100)+" гПа\n"
                    + round(c /1000)+" кПа\n"
                    + round(c /100000)+" Бар\n"
                    + round(c /1000000)+" МПа\n"
                    + round(c /0.01)+ " мБар\n"
                    + round(c /9.80664999999998)+" кг*Н/см²\n"
                    + round(c /0.101971621297793)+" кг*Н/м²\n");
        }
        catch (NumberFormatException e) {
        }
    }
}
