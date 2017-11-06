package tool;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/11/6.
 */

public class EmojiSpannableStringUtil {


    public static SpannableString getEmotionContent(final Context context, final TextView tv, EditText et, String source) {
        SpannableString spannableString = new SpannableString(source);

        Resources res = context.getResources();

        String regexEmotion = "#[0-9][0-9]" ;
        Pattern patternEmotion = Pattern. compile(regexEmotion);
        Matcher matcherEmotion = patternEmotion.matcher(spannableString);

        Map<String,Integer> map = EmojiHashMapUtil.getEmojiHashMapUtil().getEmojiHashMap();

        int size =18;
        if(tv!=null)
            size = ( int) tv.getTextSize()*2;
        else
            size = ( int) et.getTextSize()*2;
        while (matcherEmotion.find()) {
            // 获取匹配到的具体字符
            String key = matcherEmotion.group();
            // 匹配字符串的开始位置
            int start = matcherEmotion.start();
            // 利用表情名字获取到对应的图片
            Integer imgRes = map.get(key);
            if (imgRes != null) {
                // 压缩表情图片

                Bitmap bitmap = BitmapFactory.decodeResource(res, imgRes);
                Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

                ImageSpan span = new ImageSpan(context, scaleBitmap);
                spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
            }
        }
        return spannableString;
    }

}
