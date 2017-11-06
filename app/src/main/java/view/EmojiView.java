package view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.Touch;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.groupfive.www.travel.R;

import java.util.List;

import adapter.EmojiGridViewAdapter;
import tool.EmojiHashMapUtil;
import tool.EmojiSpannableStringUtil;

/**
 * Created by Administrator on 2017/11/6.
 */

public class EmojiView implements AdapterView.OnItemClickListener,TextWatcher,View.OnClickListener {


    private EditText editText;
    private GridView gridView;
    private EmojiGridViewAdapter mEmojiGridViewAdapter;
    private Activity activity;
    private Handler handler;
    private Button button;

    private List<EmojiHashMapUtil.Emoji> list;

    public EmojiView(Activity activity, Handler Handler){
        this.handler = Handler;
        this.activity = activity;
    //    this.rootView = inflater.inflate(R.layout.emoji_view_layout,null);
        this.editText = (EditText) activity.findViewById(R.id.et);
        this.gridView = (GridView) activity.findViewById(R.id.gv);
        this.button = (Button) activity.findViewById(R.id.emoji);
        init();
        initData();
    }

    private void init() {
        this.gridView.setOnItemClickListener(this);
      //  this.editText.setOnClickListener(this);
       //this.editText.dispatchKeyEvent(new KeyEvent(){});
        this.editText.addTextChangedListener(this); this.editText.setOnClickListener(this);
       // this.editText.onf
      //  this.editText.setText(new SpannableString(""));
    }

    private void initData() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(mEmojiGridViewAdapter==null) {
                    list = EmojiHashMapUtil.getEmojiHashMapUtil().getEmojiList();
                    mEmojiGridViewAdapter = new EmojiGridViewAdapter(activity.getLayoutInflater(),list );
                }
                gridView.setAdapter(mEmojiGridViewAdapter);
                Log.d("TAG","dad");
            }
        });
        this.gridView.setVisibility(View.GONE);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gridView.getVisibility() == View.VISIBLE) {//如果表情框可见
                    gridView.setVisibility(View.GONE);//隐藏
                    //设置软键盘可以顶出布局
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                } else {//如果表情框隐藏
                    gridView.setVisibility(View.VISIBLE);//可见
                    //隐藏软键盘，同时社会软键盘为遮挡模式
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                }
            }

        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                int currentPos = editText.getSelectionStart();
                String lastStr = editText.getText().toString().substring(currentPos);
                String preStr = editText.getText().toString().substring(0,currentPos);
                Log.d("TAG",currentPos+" "+editText.getText().toString()+" "+lastStr);
                Log.d("TAG",currentPos+" "+editText.getText().toString()+" "+preStr);
                editText.setText(EmojiSpannableStringUtil.getEmotionContent(activity, null, editText,preStr+list.get(position).getKey()+lastStr));
                editText.setSelection(editText.getText().length());
            }
        });

    }


    private StringBuffer preStr;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        preStr = new StringBuffer(s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(final Editable s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(preStr.length()>s.length()){
                    int currentPosition = editText.getSelectionStart();
                    if(currentPosition>=2){
                        if('0'<=s.charAt(currentPosition-1)&&s.charAt(currentPosition-1)<='9'&&s.charAt(currentPosition-2)=='#'){
                            s.delete(currentPosition-2,currentPosition);
                        }
                    }
                    Log.d("TAG","delete "+ editText.getSelectionStart());

                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        gridView.setVisibility(View.GONE);//隐藏
    }
}
