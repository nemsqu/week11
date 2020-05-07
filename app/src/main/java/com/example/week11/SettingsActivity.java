package com.example.week11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements MyAdapter.OnEventListener {

    private TextView title, titleInput, titleTextSize, titleTextBold, titleBoxHeight, titleBoxWidth, titleRows, titleAllow, titleLanguage;
    private EditText editText, editTextSize, editBoxWidth, editBoxHeight, editRows;
    private CheckBox checkBold;
    private Button applyChanges;
    private ToggleButton allowChanges;
    private String text, chosenLanguage;
    private int textSize, boxHeight, boxWidth, rows;
    private boolean bold, allow;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> languages, languageCodes;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        title = (TextView) findViewById(R.id.title);
        titleInput = (TextView) findViewById(R.id.titleInput);
        titleTextSize = (TextView) findViewById(R.id.titleTextSize);
        titleTextBold = (TextView) findViewById(R.id.titleBold);
        titleBoxHeight = (TextView) findViewById(R.id.titleBoxHeight);
        titleBoxWidth = (TextView) findViewById(R.id.titleBoxWidth);
        titleRows = (TextView) findViewById(R.id.titleRows);
        titleAllow = (TextView) findViewById(R.id.titleAllowChanges);
        titleLanguage = (TextView) findViewById(R.id.titleLanguage);
        editText = (EditText) findViewById(R.id.editText);
        editTextSize = (EditText) findViewById(R.id.editTextSize);
        editBoxWidth = (EditText) findViewById(R.id.editBoxWidth);
        editBoxHeight = (EditText) findViewById(R.id.editBoxHeight);
        editRows = (EditText) findViewById(R.id.editRowNumber);
        checkBold = (CheckBox) findViewById(R.id.checkBox);
        applyChanges = (Button) findViewById(R.id.buttonApply);
        allowChanges = (ToggleButton) findViewById(R.id.toggleButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        languages = new ArrayList<>();
        languages.add(getResources().getString(R.string.english));
        languages.add(getResources().getString(R.string.finnish));
        languages.add(getResources().getString(R.string.swedish));
        adapter = new MyAdapter(languages, this);
        recyclerView.setAdapter(adapter);
        languageCodes = new ArrayList<>();
        languageCodes.add("en");
        languageCodes.add("fi");
        languageCodes.add("swe");
    }

    public void apply(View v){
        text = editText.getText().toString();
        if(!editTextSize.getText().toString().equals("")) {
            textSize = Integer.parseInt(editTextSize.getText().toString());
        }else{
            textSize = 0;
        }
        if(checkBold.isChecked()){
            bold = true;
        }else{
            bold = false;
        }
        if(!editBoxWidth.getText().toString().equals("")) {
            boxWidth = Integer.parseInt(editBoxWidth.getText().toString());
        }else{
            boxWidth = 0;
        }
        if(!editBoxHeight.getText().toString().equals("")) {
            boxHeight = Integer.parseInt(editBoxHeight.getText().toString());
        }else{
            boxHeight = 0;
        }
        if(!editRows.getText().toString().equals("")) {
            rows = Integer.parseInt(editRows.getText().toString());
        }else{
            rows = 0;
        }
        if(allowChanges.isChecked()){
            allow = true;
        }else{
            allow = false;
        }
        Changes changes = new Changes(text, textSize, bold, boxWidth, boxHeight, rows, allow);
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("changes", changes);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onEventClick(int position) {
        chosenLanguage = languageCodes.get(position);
        LanguageChanger.getInstance().setLangCode(chosenLanguage);
        changeLanguage(chosenLanguage);
    }

    public void changeLanguage(String langCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(langCode));
        resources.updateConfiguration(configuration, dm);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void onStart() {
        super.onStart();
        //changeLanguage(LanguageChanger.getInstance().getLangCode());
    }
}
