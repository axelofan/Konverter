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


public class MassFragment extends Fragment {

    private EditText input;
    private Spinner massSp;
    private TextView output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mass, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = (EditText) view.findViewById(R.id.input);
        massSp = (Spinner) view.findViewById(R.id.mass);
        output = (TextView) view.findViewById(R.id.output);

        massSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setMassSp();
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
                setMassSp();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void setMassSp() {
        double c = 0;
        String mass = massSp.getSelectedItem().toString();
        try {
            c = Double.parseDouble(input.getText().toString());
            if ("Миллиграмм".equals(mass)) c = c * 0.000001;
            else if ("Карат".equals(mass)) c = c * 0.0002;
            else if ("Грамм".equals(mass)) c = c * 0.001;
            else if ("Центнер".equals(mass)) c = c * 100;
            else if ("Тонна".equals(mass)) c = c * 1000;
            else if ("Русский фунт".equals(mass)) c = c * 2.44;
            output.setText(round(c)+" Килограмм\n"
                    + round(c/ 0.000001)+" Миллиграмм\n"
                    + round(c/ 0.0002)+" Карат\n"
                    + round(c/ 0.001)+" Грамм\n"
                    + round(c/ 100)+" Центнер\n"
                    + round(c/ 1000)+" Тонна\n"
                    + round(c/ 2.44)+" Русский фунт\n");
        } catch (NumberFormatException e) {
        }
    }
}
