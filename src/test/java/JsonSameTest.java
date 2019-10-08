import java.util.Arrays;
import java.util.List;

public class JsonSameTest {
    public static void main(String[] args){
//        List<String> obj1= Arrays.asList("1","5","3");
//        List<String> obj2=Arrays.asList("1","3","2");
//        System.out.println(JsonSameUtil.same(obj1,obj2));

        String str1 = "{\"msgId\":null,\"statusCode\":200,\"message\":\"success\",\"data\":{\"userID\":126,\"userNickName\":\"17600189896\",\"mobileNumber\":\"17600189896\",\"region\":\"本机地址\",\"lastloginTime\":1569204264000,\"isStopped\":0,\"simpleImage\":\"http://59.108.36.236/serverdata/image/user/2019/09/4/f4dd5748-1758-46ec-a594-8300344927ee.jpg\",\"wxAccount\":null}}";
        String str2 = "{\"msgId\":null,\"statusCode\":200,\"message\":\"success\",\"data\":{\"userID\":126,\"userNickName\":\"17600189896\",\"mobileNumber\":\"17600189896\",\"region\":\"本机地址\",\"lastloginTime\":111569204264000,\"isStopped\":0,\"simpleImage\":\"http://59.108.36.236/serverdata/image/user/2019/09/4/f4dd5748-1758-46ec-a594-8300344927ee.jpg\",\"wxAccount\":0}}";
        String str3="{\"msgId\":null,\"statusCode\":200,\"message\":\"success\",\"data\":[{\"id\":1565,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1467,\"filePath\":null,\"textId\":34224,\"textTitle\":\"习近平考察津巴布韦野生动物救助基地\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":null,\"isDeleted\":null,\"textValue\":\"中国高度重视野生动物保护事业，加强野生动物栖息地保护和拯救繁育工作，严厉打击野生动物及象牙等动物产品非法贸易，取得显著成效。中国加强宣传教育，民间团体等也积极参与此项工作，中国野生动物保护事业群众基础不断扩大。同时，中国认真履行野生动物保护国际义务，积极参与野生动物保护国际合作。\",\"docPublishTime\":1449053760000},{di:123}]}";
        String str4="{\"msgId\":null,\"statusCode\":200,\"message\":\"success\",\"data\":[{\"id\":1565,\"resourceType\":\"text\",\"resourceTypeId\":14,\"knowledgeElementId\":1467,\"filePath\":null,\"textId\":34224,\"textTitle\":\"习近平考察津巴布韦野生动物救助基地\",\"createDatetime\":null,\"updateDatetime\":null,\"auditingStatus\":null,\"isDeleted\":null,\"textValue\":\"中国高度重视野生动物保护事业，加强野生动物栖息地保护和拯救繁育工作，严厉打击野生动物及象牙等动物产品非法贸易，取得显著成效。中国加强宣传教育，民间团体等也积极参与此项工作，中国野生动物保护事业群众基础不断扩大。同时，中国认真履行野生动物保护国际义务，积极参与野生动物保护国际合作。\",\"docPublishTime\":14490537600001},{di:123}]}";

        Boolean result=JsonSameUtilWithSkipVerfify.same(str3, str4,"di");
        System.out.println(result);
    }
}
