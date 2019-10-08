import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.ObjectUtils;


import java.util.Iterator;

public class fastJsonDiff {


    @SuppressWarnings("unchecked")
    public static void compareJson(JSONObject json1, JSONObject json2, String key) {
        Iterator<String> i = json1.keySet().iterator();
        while (i.hasNext()) {
            key = i.next();
            compareJson(json1.get(key), json2.get(key), key);
        }
//        return sb.toString();
    }

    public static void compareJson(Object json1, Object json2, String key) {
        if (json1 instanceof JSONObject) {
//            System.out.println("this JSONObject----" + key);
            compareJson((JSONObject) json1, (JSONObject) json2, key);
        } else if (json1 instanceof JSONArray) {
//            System.out.println("this JSONArray----" + key);
            compareJson((JSONArray) json1, (JSONArray) json2, key);
        } else if (json1 instanceof String) {
//            System.out.println("this String----" + key);
//            compareJson((String) json1, (String) json2, key);
            try {
                String json1ToStr = json1.toString();
                String json2ToStr = json2.toString();
                compareJson(json1ToStr, json2ToStr, key);
            } catch (Exception e) {
                System.out.println("转换发生异常 key:" + key);
                e.printStackTrace();
            }

        } else {
            System.out.println("this other----" + key);
            compareJson(json1.toString(), json2.toString(), key);
        }
    }

    public static void compareJson(String str1, String str2, String key) {
        if (!str1.equals(str2)) {
            StringBuffer sb = new StringBuffer();
            sb.append("key:"+key+ ",json1:"+ str1 +",json2:"+str2);
            System.err.println("不一致key:" + key + ",json1:" + str1 + ",json2:" + str2);
        } else {
            System.out.println("一致：key:" + key + ",json1:" + str1 + ",json2:" + str2);
        }
    }

    public static void compareJson(JSONArray json1, JSONArray json2, String key) {
        if (json1 != null && json2 != null) {
            Iterator i1 = json1.iterator();
            Iterator i2 = json2.iterator();
            while (i1.hasNext()) {
                compareJson(i1.next(), i2.next(), key);
            }
        } else {
            if (json1 == null && json2 == null) {
                System.err.println("不一致：key:" + key + "  在json1和json2中均不存在");
            } else if (json1 == null) {
                System.err.println("不一致：key:" + key + "  在json1中不存在");
            } else if (json2 == null) {
                System.err.println("不一致：key:" + key + "  在json2中不存在");
            } else {
                System.err.println("不一致：key:" + key + "  未知原因");
            }

        }
    }


    public static void main(String[] args) {

        String str1 = "{\"statusCode\":200,\"message\":\"success\",\"msgId\":null,\"data\":[{\"id\":1694,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1574,\"filePath\":\"\",\"textId\":35620,\"textTitle\":\"习近平主持中共中央政治局第二次集体学习\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":\"审核通过\",\"isDeleted\":null,\"textValue\":\"改革开放是一项长期的、艰巨的、繁重的事业，必须一代又一代人接力干下去。必须坚持社会主义市场经济的改革方向，坚持对外开放的基本国策，以更大的政治勇气和智慧，不失时机深化重要领域改革，朝着党的十八大指引的改革开放方向奋勇前进。\",\"docPublishTime\":1357041540000},{\"id\":3049,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1574,\"filePath\":\"\",\"textId\":35620,\"textTitle\":\"习近平主持中共中央政治局第二次集体学习\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":\"审核通过\",\"isDeleted\":null,\"textValue\":\"第一，改革开放是一场深刻革命，必须坚持正确方向，沿着正确道路推进。在方向问题上，我们头脑必须十分清醒，不断推动社会主义制度自我完善和发展，坚定不移走中国特色社会主义道路。第二，改革开放是前无古人的崭新事业，必须坚持正确的方法论，在不断实践探索中推进。摸着石头过河，是富有中国特色、符合中国国情的改革方法。摸着石头过河就是摸规律，从实践中获得真知。摸着石头过河和加强顶层设计是辨证统一的，推进局部的阶段性改革开放要在加强顶层设计的前提下进行，加强顶层设计要在推进局部的阶段性改革开放的基础上来谋划。要加强宏观思考和顶层设计，更加注重改革的系统性、整体性、协同性，同时也要继续鼓励大胆试验、大胆突破，不断把改革开放引向深入。第三，改革开放是一个系统工程，必须坚持全面改革，在各项改革协同配合中推进。改革开放是一场深刻而全面的社会变革，每一项改革都会对其他改革产生重要影响，每一项改革又都需要其他改革协同配合。要更加注重各项改革的相互促进、良性互动，整体推进，重点突破，形成推进改革开放的强大合力。第四，稳定是改革发展的前提，必须坚持改革发展稳定的统一。只有社会稳定，改革发展才能不断推进；只有改革发展不断推进，社会稳定才能具有坚实基础。要坚持把改革的力度、发展的速度和社会可承受的程度统一起来，把改善人民生活作为正确处理改革发展稳定关系的结合点。第五，改革开放是亿万人民自己的事业，必须坚持尊重人民首创精神，坚持在党的领导下推进。改革开放在认识和实践上的每一次突破和发展，改革开放中每一个新生事物的产生和发展，改革开放每一个方面经验的创造和积累，无不来自亿万人民的实践和智慧。改革发展稳定任务越繁重，我们越要加强和改善党的领导，越要保持党同人民群众的血肉联系，善于通过提出和贯彻正确的路线方针政策带领人民前进，善于从人民的实践创造和发展要求中完善政策主张，使改革发展成果更多更公平惠及全体人民，不断为深化改革开放夯实群众基础。\",\"docPublishTime\":1357041540000},{\"id\":3050,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1574,\"filePath\":\"\",\"textId\":35620,\"textTitle\":\"习近平主持中共中央政治局第二次集体学习\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":\"审核通过\",\"isDeleted\":null,\"textValue\":\"改革开放只有进行时没有完成时。没有改革开放，就没有中国的今天，也就没有中国的明天。改革开放中的矛盾只能用改革开放的办法来解决。我们要全面贯彻党的十八大精神，坚持以邓小平理论、“三个代表”重要思想、科学发展观为指导，积极回应广大人民群众对深化改革开放的强烈呼声和殷切期待，凝聚社会共识，协调推进各领域各环节改革，努力把改革开放推向前进。\",\"docPublishTime\":1357041540000}]}";
        String str2 = "{\"statusCode\":200,\"message\":\"success\",\"msgId\":null,\"data\":[{\"id\":1694,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1574,\"filePath\":\"\",\"textId\":35620,\"textTitle\":\"习近平主持中共中央政治局第二次集体学习\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":\"审核通过\",\"isDeleted\":null,\"textValue\":\"改革开放是一项长期的、艰巨的、繁重的事业，必须一代又一代人接力干下去。必须坚持社会主义市场经济的改革方向，坚持对外开放的基本国策，以更大的政治勇气和智慧，不失时机深化重要领域改革，朝着党的十八大指引的改革开放方向奋勇前进。\",\"docPublishTime\":1357041540000},{\"id\":3049,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1574,\"filePath\":\"\",\"textId\":35620,\"textTitle\":\"习近平主持中共中央政治局第二次集体学习\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":\"审核通过\",\"isDeleted\":null,\"textValue\":\"第一，改革开放是一场深刻革命，必须坚持正确方向，沿着正确道路推进。在方向问题上，我们头脑必须十分清醒，不断推动社会主义制度自我完善和发展，坚定不移走中国特色社会主义道路。第二，改革开放是前无古人的崭新事业，必须坚持正确的方法论，在不断实践探索中推进。摸着石头过河，是富有中国特色、符合中国国情的改革方法。摸着石头过河就是摸规律，从实践中获得真知。摸着石头过河和加强顶层设计是辨证统一的，推进局部的阶段性改革开放要在加强顶层设计的前提下进行，加强顶层设计要在推进局部的阶段性改革开放的基础上来谋划。要加强宏观思考和顶层设计，更加注重改革的系统性、整体性、协同性，同时也要继续鼓励大胆试验、大胆突破，不断把改革开放引向深入。第三，改革开放是一个系统工程，必须坚持全面改革，在各项改革协同配合中推进。改革开放是一场深刻而全面的社会变革，每一项改革都会对其他改革产生重要影响，每一项改革又都需要其他改革协同配合。要更加注重各项改革的相互促进、良性互动，整体推进，重点突破，形成推进改革开放的强大合力。第四，稳定是改革发展的前提，必须坚持改革发展稳定的统一。只有社会稳定，改革发展才能不断推进；只有改革发展不断推进，社会稳定才能具有坚实基础。要坚持把改革的力度、发展的速度和社会可承受的程度统一起来，把改善人民生活作为正确处理改革发展稳定关系的结合点。第五，改革开放是亿万人民自己的事业，必须坚持尊重人民首创精神，坚持在党的领导下推进。改革开放在认识和实践上的每一次突破和发展，改革开放中每一个新生事物的产生和发展，改革开放每一个方面经验的创造和积累，无不来自亿万人民的实践和智慧。改革发展稳定任务越繁重，我们越要加强和改善党的领导，越要保持党同人民群众的血肉联系，善于通过提出和贯彻正确的路线方针政策带领人民前进，善于从人民的实践创造和发展要求中完善政策主张，使改革发展成果更多更公平惠及全体人民，不断为深化改革开放夯实群众基础。\",\"docPublishTime\":1357041540000},{\"id\":3050,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1574,\"filePath\":\"\",\"textId\":35620,\"textTitle\":\"习近平主持中共中央政治局第二次集体学习\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":\"审核通过\",\"isDeleted\":null,\"textValue\":\"改革开放只有进行时没有完成时。没有改革开放，就没有中国的今天，也就没有中国的明天。改革开放中的矛盾只能用改革开放的办法来解决。我们要全面贯彻党的十八大精神，坚持以邓小平理论、“三个代表”重要思想、科学发展观为指导，积极回应广大人民群众对深化改革开放的强烈呼声和殷切期待，凝聚社会共识，协调推进各领域各环节改革，努力把改革开放推向前进。\",\"docPublishTime\":1357041540000}]}";


        JSONObject jsonObject1 = JSONObject.parseObject(str1);
        JSONObject jsonObject2 = JSONObject.parseObject(str2);
        compareJson(jsonObject1, jsonObject2, null);
    }

}
