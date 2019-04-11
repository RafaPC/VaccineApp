package com.example.vaccineapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class TimeLineActivity extends AppCompatActivity {

    private ListView listTimeline;
    private ArrayList<TimelineStage> arrayTimeline;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        this.listTimeline = findViewById(R.id.listTimeline);
        this.arrayTimeline = new ArrayList<>();

        TimelineStage stage1 = new TimelineStage();
        stage1.addInfoElement(new InfoElement("At Birth", 1));
        stage1.addInfoElement(new InfoElement("HepB", 1));
        this.arrayTimeline.add(stage1);

        TimelineStage stage2 = new TimelineStage();
        stage2.addInfoElement(new InfoElement("1-2 Months", 1));
        stage2.addInfoElement(new InfoElement("HelpB", 1));
        this.arrayTimeline.add(stage2);

        TimelineStage stage3 = new TimelineStage();
        stage3.addInfoElement(new InfoElement("2 Months", -1));
        stage3.addInfoElement(new InfoElement("DtaP: Diphtheria, Tetanus, acellualr pertussis", 1));
        stage3.addInfoElement(new InfoElement("Hib: Haemophilus influenza type B", 1));
        stage3.addInfoElement(new InfoElement("IPV: inactivated pollovirus", -2));
        stage3.addInfoElement(new InfoElement("PCV: Pneumoccal conjugate", -2));
        stage3.addInfoElement(new InfoElement("RV: Rotavirus", -2));
        this.arrayTimeline.add(stage3);

        TimelineStage stage4 = new TimelineStage();
        stage4.addInfoElement(new InfoElement("4 Months", -1));
        stage4.addInfoElement(new InfoElement("HepB", -1));
        this.arrayTimeline.add(stage4);


        this.listTimeline.setAdapter(new TimeLineActivity.TimelineAdapter(this.arrayTimeline));
        Log.d("TIMELINE", "Aqui llega");
    }


    private class TimelineAdapter extends BaseAdapter {

        private ArrayList<TimelineStage> arrayTimeline;

        @Override
        public int getCount() {
            return this.arrayTimeline.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public TimelineAdapter(ArrayList<TimelineStage> arrayTimeline) {
            this.arrayTimeline = arrayTimeline;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.listed_timeline, container, false);
            }
            LinearLayout stageInfoView = (LinearLayout) convertView;
            TimelineStage stage = this.arrayTimeline.get(position);
            ((TextView) stageInfoView.findViewById(R.id.textStage)).setText(stage.getInfoElement(0).getInformation());
            ((ImageView) stageInfoView.findViewById(R.id.imgTitleTimeline)).setImageResource(stage.getInfoElement(0).getAlertResource());
            LinearLayout infoLayout = stageInfoView.findViewById(R.id.layout_textTimeline);
            infoLayout.removeAllViews();
            for (int i = 1; i < stage.getSize(); i++) {
                InfoElement actualElement = stage.getInfoElement(i);
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.timeline_information, infoLayout, false);
                ((TextView) linearLayout.findViewById(R.id.textInformation)).setText(actualElement.getInformation());
                ((ImageView) linearLayout.findViewById(R.id.imgAlert)).setImageResource(actualElement.getAlertResource());
                infoLayout.addView(linearLayout);
            }
            //((TextView) profileInfoView.findViewById(R.id.profile_name)).setText(profile.getName());
            //((TextView)profileInfoView.findViewById(R.id.profile_age)).setText(profile.getBirthdate());
            //profileInfoView.setTag(position);

            /*profileInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Functions.showToast(getApplicationContext(), "Now it shows the profile info of " + ProfilesListActivity.profiles.get((int) v.getTag()).getName());
                }
            });*/

            return stageInfoView;
        }
    }
}
