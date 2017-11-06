package tool;


import com.groupfive.www.travel.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/11/6.
 */

public class EmojiHashMapUtil {
    private static volatile EmojiHashMapUtil emojiHashMapUtil = null;
    private Map<String,Integer> emojiMap;

    public EmojiHashMapUtil(){
        emojiMap = new HashMap<>();
        initMap();
    }

    private void initMap() {
        emojiMap.put("#01",R.drawable.m01);
        emojiMap.put("#02",R.drawable.m02);
        emojiMap.put("#03",R.drawable.m03);
        emojiMap.put("#04",R.drawable.m04);
        emojiMap.put("#05",R.drawable.m05);
        emojiMap.put("#06",R.drawable.m06);
        emojiMap.put("#07",R.drawable.m07);
        emojiMap.put("#08",R.drawable.m08);
        emojiMap.put("#09",R.drawable.m09);
        emojiMap.put("#10",R.drawable.m10);
        emojiMap.put("#11",R.drawable.m11);
        emojiMap.put("#12",R.drawable.m12);
        emojiMap.put("#13",R.drawable.m13);
        emojiMap.put("#14",R.drawable.m14);
        emojiMap.put("#15",R.drawable.m15);
        emojiMap.put("#16",R.drawable.m16);
        emojiMap.put("#17",R.drawable.m17);
        emojiMap.put("#18",R.drawable.m18);
        emojiMap.put("#19",R.drawable.m19);
        emojiMap.put("#20",R.drawable.m20);
        emojiMap.put("#21",R.drawable.m21);
        emojiMap.put("#22",R.drawable.m22);
        emojiMap.put("#23",R.drawable.m23);
        emojiMap.put("#24",R.drawable.m24);
        emojiMap.put("#25",R.drawable.m25);
        emojiMap.put("#26",R.drawable.m26);
        emojiMap.put("#27",R.drawable.m27);
        emojiMap.put("#28",R.drawable.m28);
        emojiMap.put("#29",R.drawable.m29);
        emojiMap.put("#30",R.drawable.m30);
        emojiMap.put("#31",R.drawable.m31);
        emojiMap.put("#32",R.drawable.m32);
        emojiMap.put("#33",R.drawable.m33);
        emojiMap.put("#34",R.drawable.m34);
        emojiMap.put("#35",R.drawable.m35);
        emojiMap.put("#36",R.drawable.m36);
        emojiMap.put("#37",R.drawable.m37);
        emojiMap.put("#38",R.drawable.m38);
        emojiMap.put("#39",R.drawable.m39);
        emojiMap.put("#40",R.drawable.m40);
        emojiMap.put("#41",R.drawable.m41);
        emojiMap.put("#42",R.drawable.m42);
        emojiMap.put("#43",R.drawable.m43);
        emojiMap.put("#44",R.drawable.m44);
        emojiMap.put("#45",R.drawable.m45);
        emojiMap.put("#46",R.drawable.m46);
        emojiMap.put("#47",R.drawable.m47);
        emojiMap.put("#48",R.drawable.m48);
        emojiMap.put("#49",R.drawable.m49);
        emojiMap.put("#50",R.drawable.m50);
        emojiMap.put("#51",R.drawable.m51);
        emojiMap.put("#52",R.drawable.m52);
        emojiMap.put("#53",R.drawable.m53);
        emojiMap.put("#54",R.drawable.m54);
        emojiMap.put("#55",R.drawable.m55);
        emojiMap.put("#56",R.drawable.m56);
        emojiMap.put("#57",R.drawable.m57);
        emojiMap.put("#58",R.drawable.m58);
        emojiMap.put("#59",R.drawable.m59);
        emojiMap.put("#60",R.drawable.m60);
        emojiMap.put("#61",R.drawable.m61);
        emojiMap.put("#62",R.drawable.m62);
        emojiMap.put("#63",R.drawable.m63);
        emojiMap.put("#64",R.drawable.m64);

        emojiMap.put("#65",R.drawable.m65);
        emojiMap.put("#66",R.drawable.m66);
        emojiMap.put("#67",R.drawable.m67);
        emojiMap.put("#68",R.drawable.m68);
        emojiMap.put("#69",R.drawable.m69);
        emojiMap.put("#70",R.drawable.m70);
        emojiMap.put("#71",R.drawable.m71);
        emojiMap.put("#72",R.drawable.m72);
        emojiMap.put("#73",R.drawable.m73);
        emojiMap.put("#74",R.drawable.m74);
        emojiMap.put("#75",R.drawable.m75);
        emojiMap.put("#76",R.drawable.m76);
        emojiMap.put("#77",R.drawable.m77);
        emojiMap.put("#78",R.drawable.m78);
        emojiMap.put("#79",R.drawable.m79);
        emojiMap.put("#80",R.drawable.m80);
        emojiMap.put("#81",R.drawable.m81);
        emojiMap.put("#82",R.drawable.m82);
        emojiMap.put("#83",R.drawable.m83);
        emojiMap.put("#84",R.drawable.m84);
        emojiMap.put("#85",R.drawable.m85);
        emojiMap.put("#86",R.drawable.m86);
        emojiMap.put("#87",R.drawable.m87);
        emojiMap.put("#88",R.drawable.m88);
        emojiMap.put("#89",R.drawable.m89);
        emojiMap.put("#90",R.drawable.m90);
        emojiMap.put("#91",R.drawable.m91);
        emojiMap.put("#92",R.drawable.m92);
        emojiMap.put("#93",R.drawable.m93);
        emojiMap.put("#94",R.drawable.m94);
        emojiMap.put("#95",R.drawable.m95);
        emojiMap.put("#96",R.drawable.m96);
        emojiMap.put("#97",R.drawable.m97);
        emojiMap.put("#98",R.drawable.m98);
        emojiMap.put("#99",R.drawable.m99);
       // emojiMap.put("#100",);


    }



    public Map<String ,Integer> getEmojiHashMap(){
        return emojiMap;
    }


    public List<Emoji> getEmojiList(){

        List list= new ArrayList();
        Iterator<Map.Entry<String,Integer>> iterator = emojiMap.entrySet().iterator();

        while(iterator.hasNext()){

            Map.Entry<String,Integer> entry = iterator.next();
            list.add(new Emoji(entry.getKey(),entry.getValue()));

        }

        return list;

    }

    public static EmojiHashMapUtil getEmojiHashMapUtil(){

        if(emojiHashMapUtil==null){
            synchronized (EmojiHashMapUtil.class){
                if(emojiHashMapUtil==null)
                    emojiHashMapUtil = new EmojiHashMapUtil();
            }
        }
        return emojiHashMapUtil;
    }


    public static class  Emoji{
        String key;
        Integer Rid;
        public Emoji(String key, Integer Rid){
            this.key = key;
            this.Rid = Rid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getRid() {
            return Rid;
        }

        public void setRid(Integer rid) {
            Rid = rid;
        }
    }

}
