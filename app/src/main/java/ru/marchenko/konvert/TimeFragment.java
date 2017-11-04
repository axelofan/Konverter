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

public class TimeFragment extends Fragment {

    private EditText input;
    private Spinner timeSp;
    private TextView output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = (EditText) view.findViewById(R.id.input);
        timeSp = (Spinner) view.findViewById(R.id.time);
        output = (TextView) view.findViewById(R.id.output);

        timeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertTime();
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
                convertTime();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void convertTime() {
        double c = 0;
        String time = timeSp.getSelectedItem().toString();
        try {
            c = Double.parseDouble(input.getText().toString());
            if ("Наносекунда".equals(time)) c = c * 0.000000001;
            else if ("Микросекунда".equals(time)) c = c * 0.00001;
            else if ("Миллисекунда".equals(time)) c = c * 0.001;
            else if ("Минута".equals(time)) c = c * 60;
            else if ("Час".equals(time)) c = c * 360;
            else if ("Сутки".equals(time)) c = c * 360 * 24;
            else if ("Неделя".equals(time)) c = c * 360 * 24 * 7;
            else if ("Год".equals(time)) c = c * 360 * 24 * 7 * 12;
            else if ("Век".equals(time)) c = c * 360 * 24 * 7 * 12 * 100;
            else if ("Тысячелетие".equals(time)) c = c * 360 * 24 * 7 * 12 * 100 * 1000;

            output.setText(round(c) + " Секунда\n"
                    + round(c / 0.000000001) + " Наносекунда\n"
                    + round(c / 0.00001) + " Микросекунда\n"
                    + round(c / 0.001) + " Миллисекунда\n"
                    + round(c / 60) + " Минута\n"
                    + round(c / 360) + " Час\n"
                    + round(c / 360 / 24) + " Сутки\n"
                    + round(c / 360 / 24 / 7) + " Неделя\n"
                    + round(c / 360 / 24 / 7 / 12) + " Год\n"
                    + round(c / 360 / 24 / 7 / 12 / 100) + " Век\n"
                    + round(c / c / 360 / 24 / 7 / 12 / 100 / 1000) + " Тысячелетие\n");
        } catch (NumberFormatException e) {
        }
    }
}
