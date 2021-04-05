package com.example.teamproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckinOutActivity extends AppCompatActivity {
    static Calendar checkin = Calendar.getInstance();
    static Calendar checkout = Calendar.getInstance();
    Format formatter = new SimpleDateFormat("yyyy-MM-dd");

    private TextView checkintext;
    private TextView checkouttext;
    private enum check{in, out};
    private check type;
    private ImageButton pscBack;

    static MenuItem menuItem;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    private static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pscBack = (ImageButton) findViewById(R.id.pscBack);
        checkintext = (TextView) findViewById(R.id.checkindate);
        checkouttext = (TextView) findViewById(R.id.checkoutdate);
        if (!flag){
            checkout.add(Calendar.DATE,1);
            flag = true;
        }
        checkintext.setText(formatter.format(checkin.getTime()));
        checkouttext.setText(formatter.format(checkout.getTime()));
        type = check.in;
        Button psc_select  = (Button)findViewById(R.id.psc_select);
        Button psc_checkin  = (Button)findViewById(R.id.psc_checkin);
        Button psc_checkout = (Button) findViewById(R.id.psc_checkout) ;
        psc_checkin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = check.in;
                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), listener, checkin.get(checkin.YEAR), checkin.get(checkin.MONTH), checkin.get(checkin.DATE));
                dialog.show();
            }
        });
        psc_checkout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = check.out;
                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), listener, checkout.get(checkin.YEAR), checkout.get(checkin.MONTH), checkout.get(checkin.DATE));
                dialog.show();
            }
        });
        //checkout.add(Calendar.DATE,-1);

        psc_select.setOnClickListener(view -> {
            HotelGuestActivity.setDate(checkintext.getText().toString(), checkouttext.getText().toString());
            RoomDetailsActivity.setDate(checkintext.getText().toString(), checkouttext.getText().toString());


            finish();
        });

        pscBack.setOnClickListener(v -> {
            finish();
        });
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //수정 대상(기본:체크인)
            TextView moditxt    = checkintext;
            Calendar modidate   = checkin;
            //반대 대상(기본:체크아웃)
            TextView countxt    = checkouttext;
            Calendar countdate  = checkout;

            //체크 아웃 날짜를 설정할 경우 반대로
            if(type == check.out){
                moditxt = checkouttext;
                modidate = checkout;
                countxt = checkintext;
                countdate = checkin;
            }
            modidate.set(year, monthOfYear, dayOfMonth);
            moditxt.setText(formatter.format(modidate.getTime()));
            //체크아웃이 체크인 이전 일 경우
            if (checkin.compareTo(checkout) >= 0) {
                Toast.makeText(getApplicationContext(), "체크아웃 날짜는 체크인 이후여야 합니다.", Toast.LENGTH_SHORT).show();
                countdate.set(modidate.get(Calendar.YEAR), modidate.get(Calendar.MONTH), modidate.get(Calendar.DATE));
                countdate.add(Calendar.DATE, type==check.in ? 1:-1);
                countxt.setText(formatter.format(countdate.getTime()));
            }
        }
    };
}
