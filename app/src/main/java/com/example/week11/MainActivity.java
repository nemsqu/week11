package com.example.week11;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textBox, editableTextBox;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Changes changes;
    private String text, langCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (TextView) findViewById(R.id.textBox);
        editableTextBox = (TextView) findViewById(R.id.editableTextBox);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.draweropen, R.string.drawerclose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
     public boolean onNavigationItemSelected(MenuItem menuItem){
        System.out.println("WHY YOU NO WORK");
        if(menuItem.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
     }

     public void onStart() {
         super.onStart();
         changeLanguage(LanguageChanger.getInstance().getLangCode());
         Intent intent = getIntent();
         Bundle bundle = intent.getExtras();
         if(bundle != null) {
             changes = (Changes) bundle.getSerializable("changes");
             if(changes.isAllowChanges()) {
                 editableTextBox.setText(changes.getText());
                 if (changes.getTextSize() != 0) {
                     editableTextBox.setTextSize(changes.getTextSize());
                 }
                 if (changes.isBold()) {
                     editableTextBox.setTypeface(textBox.getTypeface(), Typeface.BOLD);
                 } else {
                     editableTextBox.setTypeface(textBox.getTypeface(), Typeface.NORMAL);
                 }
                 if (changes.getBoxWidth() != 0) {
                     editableTextBox.setWidth(changes.getBoxWidth());
                 }
                 if (changes.getBoxHeight() != 0) {
                     editableTextBox.setHeight(changes.getBoxHeight());
                 }
                 if (changes.getRows() != 0) {
                     editableTextBox.setLines(changes.getRows());
                 }
             }else{
                 textBox.setText(changes.getText());
                 editableTextBox.setText("");
                 if (changes.getTextSize() != 0) {
                     textBox.setTextSize(changes.getTextSize());
                 }
                 if (changes.isBold()) {
                     textBox.setTypeface(textBox.getTypeface(), Typeface.BOLD);
                 } else {
                     textBox.setTypeface(textBox.getTypeface(), Typeface.NORMAL);
                 }
                 if (changes.getBoxWidth() != 0) {
                     textBox.setWidth(changes.getBoxWidth());
                 }
                 if (changes.getBoxHeight() != 0) {
                     textBox.setHeight(changes.getBoxHeight());
                 }
                 if (changes.getRows() != 0) {
                     textBox.setLines(changes.getRows());
                 }
             }
         }
     }

     public void changeLanguage(String langCode) {
         Resources resources = getResources();
         DisplayMetrics dm = resources.getDisplayMetrics();
         Configuration configuration = resources.getConfiguration();
         configuration.setLocale(new Locale(langCode));
         resources.updateConfiguration(configuration, dm);
     }
}
