package com.lqg.Image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LabelActivity extends AppCompatActivity {
    private List<String> labelData = new ArrayList(Arrays.asList("十年爱情长跑", "羡慕", "嫉妒", "柠檬酸", "祝福", "长长久久", "早生贵子", "幸福美满"));
    private List<String> selectLabelData = new ArrayList();
    private FlexboxLayout unSelectedFlexLayout ;
    private FlexboxLayout selectedFlexLayout;
    private EditText editLabelET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        initView();
        initLabelData();
        initselectLabelData();
    }

    private void initView() {
        selectedFlexLayout = findViewById(R.id.selected_flex_layout);
        unSelectedFlexLayout = findViewById(R.id.unselected_flex_layout);
    }

    private void initLabelData() {
        unSelectedFlexLayout.removeAllViews();
        for (int i = 0; i < labelData.size(); i++) {
            TextView textView = ScreenUtils.createFlexItemView(LabelActivity.this,  labelData.get(i), false);
            textView.setLayoutParams(ScreenUtils.createDefaultLayoutParams());
            unSelectedFlexLayout.addView(textView);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectLabelData.add(labelData.get(finalI));
                    labelData.remove(labelData.get(finalI));
                    initselectLabelData();
                    initLabelData();
                }
            });
        }
    }

    private void initselectLabelData() {
        selectedFlexLayout.removeAllViews();
        for (int i = 0; i < selectLabelData.size(); i++) {
            TextView textView = ScreenUtils.createFlexItemView(LabelActivity.this,  selectLabelData.get(i), true);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String content = selectLabelData.get(finalI);
                    labelData.add(content);
                    selectLabelData.remove(content);
                    initselectLabelData();
                    initLabelData();
                }
            });
            textView.setLayoutParams(ScreenUtils.createDefaultLayoutParams());
            selectedFlexLayout.addView(textView);
        }
        addEditLabel();
    }

    private void addEditLabel() {
        View view = LayoutInflater.from(LabelActivity.this).inflate(R.layout.label_edt_item, null);
        editLabelET = view.findViewById(R.id.lable_tip_et);
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, SizeUtils.dp2px(8), SizeUtils.dp2px(8), 0);
        editLabelET.setLayoutParams(lp);
        selectedFlexLayout.addView(editLabelET);
        editLabelET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String content = editLabelET.getText().toString().trim().replace(" ", "");
                    if (!isRepaetContent(content)) {
                        selectLabelData.add(content);
                        initselectLabelData();
                    }
                }
                return false;
            }
        });
    }

    private boolean isRepaetContent(String content) {
        for (int i = 0; i < selectLabelData.size(); i++) {
            if (selectLabelData.get(i).equals(content)) {
                return true;
            }
        }
        return false;
    }
}
