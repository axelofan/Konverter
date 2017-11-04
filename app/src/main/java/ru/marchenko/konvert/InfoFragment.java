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
import android.widget.Toast;

import static ru.marchenko.konvert.Round.round;


public class InfoFragment extends Fragment {

    private EditText input;
    private Spinner infoSp;
    private TextView output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_information,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = (EditText) view.findViewById(R.id.input);
        infoSp = (Spinner) view.findViewById(R.id.info);
        output = (TextView) view.findViewById(R.id.output);

        infoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convertInfo();
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
                convertInfo();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void convertInfo() {
        double c = 0;
        String info = infoSp.getSelectedItem().toString();
        try {
            c = Double.parseDouble(input.getText().toString());
            if ("Байт".equals(info)) c = c * 8;
            else if ("Килобайт".equals(info)) c = c * 8 * 1024;
            else if ("Мегабайт".equals(info)) c = c * 8 * 1024 * 1024;
            else if ("Гигабайт".equals(info)) c = c * 8 * 1024 * 1024 * 1024;
            else if ("Терабайт".equals(info)) c = c * 8 * 1024 * 1024 * 1024 * 1024;
            output.setText(round(c) + " бит\n"
                    + round(c /8)+" байт\n"
                    + round(c /8/1024)+" Кбайт\n"
                    + round(c /8/1024/1024)+" Мбайт\n"
                    + round(c /8/1024/1024/1024)+" Гбайт\n"
                    + round(c /8/1024/1024/1024/1024)+" Тбайт\n");
        } catch (NumberFormatException e) {
        }
    }
}
