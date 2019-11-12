package com.example.myapplication;



import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyGridViewCalendar extends DialogFragment {

    private TextView tvCalendarTitle;
    private TextView tvSelectedDate,txt_sample,txt_weight,txt_food_b,txt_food_l,txt_food_d;
    private String str;
    private GridView gvCalendar;
    public FirebaseAuth mAuth;
    public String UserTable;
    String TAG;

    private ArrayList<DayInfo> arrayListDayInfo;
    Calendar mThisMonthCalendar;
    CalendarAdapter mCalendarAdapter;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    Date selectedDate;

    public void setSelectedDate(Date date){
        selectedDate = date;

        if(mCalendarAdapter != null) {
            mCalendarAdapter.selectedDate = date;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.grid_view_calendar, null, false);

        Button btnPreviousCalendar = dialog.findViewById(R.id.btn_previous_calendar);
        Button btnNextCalendar = dialog.findViewById(R.id.btn_next_calendar);

        tvCalendarTitle = dialog.findViewById(R.id.tv_calendar_title);
        tvSelectedDate = dialog.findViewById(R.id.tv_selected_date);
        txt_sample = dialog.findViewById(R.id.txt_sample);
        txt_weight =dialog.findViewById(R.id.txt_weight);
        gvCalendar = dialog.findViewById(R.id.gv_calendar);
        txt_food_b =dialog.findViewById(R.id.txt_food_b);
        txt_food_l = dialog.findViewById(R.id.txt_food_l);
        txt_food_d =dialog.findViewById(R.id.txt_food_d);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        txt_sample.setText(user.getDisplayName());



        btnPreviousCalendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mThisMonthCalendar.add(Calendar.MONTH, -1);

                getCalendar(mThisMonthCalendar.getTime());
            }
        });
        btnNextCalendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mThisMonthCalendar.add(Calendar.MONTH, +1);

                getCalendar(mThisMonthCalendar.getTime());
            }
        });

        gvCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSelectedDate(((DayInfo)view.getTag()).getDate());
                tvSelectedDate.setText(sdf.format(mCalendarAdapter.selectedDate));
                //txt_sample.setText(sdf.format(mCalendarAdapter.selectedDate));
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection(txt_sample.getText().toString()).document(tvSelectedDate.getText().toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                if(document.get("몸무게") !=null) {
                                    txt_weight.setText(document.get("몸무게").toString()+"kg");
                                }
                                else if(document.get("몸무게") ==null)  {
                                    txt_weight.setText("");
                                }

                                if(document.get("음식-아침-칼로리") !=null) {
                                    txt_food_b.setText(document.get("음식-아침-칼로리").toString()+"Cal");
                                }
                                else if(document.get("음식-아침-칼로리") ==null)  {
                                    txt_food_b.setText("");
                                }
                                if(document.get("음식-점심-칼로리") !=null) {
                                    txt_food_l.setText(document.get("음식-점심-칼로리").toString()+"Cal");
                                }
                                else if(document.get("음식-점심-칼로리") ==null)  {
                                    txt_food_l.setText("");
                                }
                                if(document.get("음식-저녁-칼로리")!=null) {
                                    txt_food_d.setText(document.get("음식-저녁-칼로리").toString()+"Cal");
                                }
                                else if(document.get("음식-저녁-칼로리") ==null)  {
                                    txt_food_d.setText("");
                                }

                            } else {
                                txt_weight.setText("");
                                txt_food_b.setText("");
                                txt_food_l.setText("");
                                txt_food_d.setText("");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
                mCalendarAdapter.notifyDataSetChanged();
            }
        });

        arrayListDayInfo = new ArrayList<>();

        builder.setView(dialog);
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();

        mThisMonthCalendar = Calendar.getInstance();
        getCalendar(mThisMonthCalendar.getTime());
    }

    private void getCalendar(Date dateForCurrentMonth){
        int dayOfWeek;
        int thisMonthLastDay;

        arrayListDayInfo.clear();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateForCurrentMonth);

        calendar.set(Calendar.DATE, 1);//1일로 변경
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);//1일의 요일 구하기
        Log.d("CalendarTest", "dayOfWeek = " + dayOfWeek+"");

        if(dayOfWeek == Calendar.SUNDAY){ //현재 달의 1일이 무슨 요일인지 검사
            dayOfWeek += 7;
        }

        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        setCalendarTitle();

        DayInfo day;

        calendar.add(Calendar.DATE, -1*(dayOfWeek-1)); //현재 달력화면에서 보이는 지난달의 시작일
        for(int i=0; i<dayOfWeek-1; i++){
            day = new DayInfo();
            day.setDate(calendar.getTime());
            day.setInMonth(false);
            arrayListDayInfo.add(day);

            calendar.add(Calendar.DATE, +1);
        }

        for(int i=1; i <= thisMonthLastDay; i++){
            day = new DayInfo();
            day.setDate(calendar.getTime());
            day.setInMonth(true);
            arrayListDayInfo.add(day);

            calendar.add(Calendar.DATE, +1);
        }

        for(int i=1; i<42-(thisMonthLastDay+dayOfWeek-1)+1; i++) {
            day = new DayInfo();
            day.setDate(calendar.getTime());
            day.setInMonth(false);
            arrayListDayInfo.add(day);

            calendar.add(Calendar.DATE, +1);
        }

        mCalendarAdapter = new CalendarAdapter(arrayListDayInfo, selectedDate);
        gvCalendar.setAdapter(mCalendarAdapter);

        tvSelectedDate.setText(sdf.format(selectedDate));
    }

    private void setCalendarTitle(){
        StringBuilder sb = new StringBuilder();

        sb.append(mThisMonthCalendar.get(Calendar.YEAR))
                .append("년 ")
                .append((mThisMonthCalendar.get(Calendar.MONTH) + 1))
                .append("월");
        tvCalendarTitle.setText(sb.toString());
    }
}
