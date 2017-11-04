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


public class AreaFragment extends Fragment {

    private EditText input;
    private Spinner areaSp;
    private TextView output;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_area, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = (EditText) view.findViewById(R.id.input);
        areaSp = (Spinner) view.findViewById(R.id.area);
        output = (TextView) view.findViewById(R.id.output);

        areaSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertArea();
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
                convertArea();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void convertArea() {
        double c = 0;
        String area = areaSp.getSelectedItem().toString();
        try {
            c = Double.parseDouble(input.getText().toString());
            if ("Квадратный миллиметр".equals(area)) c = c * 0.01;
            else if ("Квадратный дециметр".equals(area)) c = c * 100;
            else if ("Квадратный метр".equals(area)) c = c * 10000;
            else if ("Ар (сотка)".equals(area)) c = c * 1000000;
            else if ("Квадратный километр".equals(area)) c = c * 10000000000l;
            else if ("Гектар".equals(area)) c = c * 10000000;

            output.setText(round(c) + " см²\n"
                    + round(c / 0.01) + " мм²\n"
                    + round(c / 100) + " дм²\n"
                    + round(c / 10000) + " м²\n"
                    + round(c / 1000000) + " Ар (сотка)\n"
                    + round(c / 10000000000l) + " км²\n"
                    + round(c / 10000000) + " Гектар\n");
        } catch (NumberFormatException e) {
        }
    }
}
