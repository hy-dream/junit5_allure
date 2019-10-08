import com.google.gson.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

public class JsonSameUtilWithSkipVerfify {
    private static final Gson gson=new Gson();
    private static final JsonParser parser=new JsonParser();
    private static Logger logger = Logger.getLogger(JsonSameUtilWithSkipVerfify.class);

    /**
     * 比较两个bean是否等价
     */
    public static boolean same(Object a,Object b,String skipVerify){
        if (a==null){
            return b==null;
        }
        logger.debug("当前比对的是object");
        return same(gson.toJson(a),gson.toJson(b), skipVerify);
    }

    /**
     * 比较两个json字符串是否等价
     */
    public static boolean same(String a,String b,String skipVerify){
        logger.debug("当前比对的是string");
        if(a==null){
            return b==null;
        }
        if(a.equals(b)){
            return true;
        }
        JsonElement aElememt=parser.parse(a);
        JsonElement bElement=parser.parse(b);
        if(gson.toJson(aElememt).equals(gson.toJson(bElement))){
            logger.debug("当前比对的是 gson.toJson(aElememt).equals(gson.toJson(bElement))");
            return true;
        }
        return same(aElememt,bElement,skipVerify);
    }

    private static boolean same(JsonElement a,JsonElement b,String skipVerify){
        logger.debug("当前比对的是 JsonElement a,JsonElement b");
        if (a.isJsonObject() && b.isJsonObject()){
            logger.debug("当前比对的是a.isJsonObject() && b.isJsonObject()");
            return same((JsonObject) a,(JsonObject) b, skipVerify);
        } else if (a.isJsonArray() && b.isJsonArray()){
            logger.debug("当前比对的是 a.isJsonArray() && b.isJsonArray()");
            return same((JsonArray) a,(JsonArray) b, skipVerify);
        } else if (a.isJsonPrimitive() && b.isJsonPrimitive()){
            logger.debug("当前比对的是a.isJsonPrimitive() && b.isJsonPrimitive()");
            return same((JsonPrimitive) a,(JsonPrimitive) b, skipVerify);
        } else if (a.isJsonNull() && b.isJsonNull()){
            logger.debug("当前比对的是a.isJsonNull() && b.isJsonNull()");
            return same((JsonNull) a,(JsonNull) b, skipVerify);
        } else{
            return Boolean.FALSE;
        }
    }

    private static boolean same(JsonObject a,JsonObject b,String skipVerify){
        Set<String> aSet=a.keySet();
        Set<String> bSet=b.keySet();
        if(!aSet.equals(bSet)){
            logger.error("!!!! 比对错误 keyset不一样");
            logger.error(aSet);
            logger.error(bSet);
            return false;
        }
        for(String aKey: aSet){
            if(aKey.equals(skipVerify)) {
                logger.warn("****跳过验证 "+skipVerify);
                continue;
            }
            if(!same(a.get(aKey),b.get(aKey), skipVerify)){
                //System.out.println("aKey:" +aKey);
                logger.error("!!!! 比对错误 a.get("+aKey+"):"+a.get(aKey)+"  b.get("+aKey+"):"+b.get(aKey));
                return false;
            }
        }
        return true;
    }

    private static boolean same(JsonArray a,JsonArray b,String skipVerify){
        if(a.size()!=b.size()){
            logger.error("!!!! 比对错误，数组长度不一致，a.size： "+a.size()+" b.size: "+b.size());
            return false;
        }
        List<JsonElement> aList=toSortedList(a);
        List<JsonElement> bList=toSortedList(b);
        for(int i=0;i<aList.size();i++){
            if(!same(aList.get(i),bList.get(i), skipVerify)){
                logger.error("!!!! 比对错误，Array value不一样");
                logger.error(aList.get(i));
                logger.error(bList.get(i));
                return false;
            }
        }
        return true;
    }

    private static boolean same(JsonPrimitive a,JsonPrimitive b,String skipVerify){
        logger.debug("Primitive value");
        logger.debug(a);
        logger.debug(b);
        return a.equals(b);
    }

    private static boolean same(JsonNull a,JsonNull b,String skipVerify){
        return true;
    }
    private static List<JsonElement> toSortedList(JsonArray a){
        logger.debug("当前做的事toSortedList");
        List<JsonElement> aList=new ArrayList<>();
        a.forEach(aList::add);
        aList.sort(Comparator.comparing(gson::toJson));
        return aList;
    }
}
