package com.github.sd4324530.fastweixin;

import com.github.sd4324530.fastweixin.api.*;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.CustomAccount;
import com.github.sd4324530.fastweixin.api.entity.Group;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.*;
import com.github.sd4324530.fastweixin.api.response.*;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.ImageMsg;
import com.github.sd4324530.fastweixin.message.MpNewsMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.apache.http.client.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author peiyu
 */
public class FastweixinTest {

    private static final Logger LOG = LoggerFactory.getLogger(FastweixinTest.class);


    /*
     *AppID(应用ID)wx8c33ff895df5d0d9
     *AppSecret(应用密钥)0705aafac0bef944de4c485d71fce900
     */
    @Test
    public void test() {
        String appid = "wxd26b23a0c2ff805f";
        String secret = "ff87a518abdcca79317453dd10d53c3a";
        ApiConfig config = new ApiConfig(appid, secret);
//        createMenu(config);
//        getUserList(config);
//        uploadMedia(config);
//        downloadMedia(config);
//        getUserInfo(config);
 //       getMenu(config);
//        addCustomAccount(config);
        getOauthPageUrl(config);
//        getToken(config);
//        oauthGetUserInfo(config);
//        ApiConfig config = new ApiConfig(appid, secret, true);
//        testGetJsApiTicket(config);
//        testJsApiSign(config);
       // getUserData(config);
//        getArticleData(config);
    //     sendAllMessage(config);
//          sendCustomMessage(config);
   //   getUserGroups(config);
//        updateGroup(config);
//        getCallbackIP(config);
//        getShortUrl(config);
//        uploadImageMaterial(config);
    }

    /**
     * 创建菜单
     *
     * @param config API配置
     */
    private void createMenu(ApiConfig config) {
        MenuAPI menuAPI = new MenuAPI(config);

        //先删除之前的菜单
        menuAPI.deleteMenu();
        Menu request = new Menu();
        //准备一级主菜单
        MenuButton main1 = new MenuButton();
        main1.setType(MenuType.VIEW);
        main1.setUrl("http://www.ubisp.com/weixin/author");
        main1.setName("优彼商城");
        MenuButton main2 = new MenuButton();
        main2.setType(MenuType.VIEW);
        main2.setUrl("http://www.ubisp.com/weixin/author");
        main2.setName("关于我们");
        
        //准备子菜单
//        MenuButton sub1 = new MenuButton();
//        sub1.setKey("sub1");
//        sub1.setName("授权");
//        sub1.setType(MenuType.VIEW);
//        sub1.setUrl("https://open.weixin.qq.com/connect/qrconnect?appid=wx8ecfa5e99a8cb466&redirect_uri=http%3a%2f%2fcfpc.vixuan.com%2fvixuan-front%2fthirdlogin%2fweixinUserInfo&response_type=code&scope=snsapi_login");
//        MenuButton sub2 = new MenuButton();
//        sub2.setKey("sub2");
//        sub2.setName("点击");
//        sub2.setType(MenuType.CLICK);


//        List<MenuButton> list = new ArrayList<MenuButton>();
//        list.add(sub1);
//        list.add(sub2);
        //将子菜单放入主菜单里
//        main1.setSubButton(list);

        List<MenuButton> mainList = new ArrayList<MenuButton>();
        mainList.add(main1);
        mainList.add(main2);
        //将主菜单加入请求对象
        request.setButton(mainList);
        LOG.debug(request.toJsonString());
        //创建菜单
        ResultType resultType = menuAPI.createMenu(request);
        LOG.debug(resultType.toString());
    }

    /**
     * 获取关注者列表
     *
     * @param config API配置
     */
    public void getUserList(ApiConfig config) {
        UserAPI userAPI = new UserAPI(config);
        GetUsersResponse users = userAPI.getUsers(null);
        LOG.debug("user count:{}", users.getCount());
        LOG.debug("user total:{}", users.getTotal());
        String[] openids = users.getData().getOpenid();
        for (String id : openids) {
            LOG.debug("id:{}", id);
        }
    }

    /**
     * 获取用户信息
     *
     * @param config API配置
     */
    public void getUserInfo(ApiConfig config) {
        UserAPI userAPI = new UserAPI(config);
        GetUserInfoResponse userInfo = userAPI.getUserInfo("opZYwt-OS8WFxwU-colRzpu50eOQ");
        LOG.debug(userInfo.toJsonString());
    }

    public void uploadMedia(ApiConfig config) {
        MediaAPI mediaAPI = new MediaAPI(config);
        UploadMediaResponse response = mediaAPI.uploadMedia(MediaType.IMAGE, new File("E:/123.jpg"));
        LOG.debug(response.toJsonString());
    }

    public void downloadMedia(ApiConfig config) {
        MediaAPI mediaAPI = new MediaAPI(config);
        DownloadMediaResponse response = mediaAPI.downloadMedia("Kw0k6yeKxLaebweRwAUS2x08bcOx2nHMWAXO4s1lMpN_t5Fcsm-svrxe_EfGAgwo");
        LOG.debug("error:{}", response.getErrcode());
        try {
            response.writeTo(new FileOutputStream(new File("E:/222.jpg")));
        } catch (FileNotFoundException e) {
            LOG.error("异常", e);
        } catch (IOException e) {
            LOG.error("异常", e);
        }
    }

    public void getMenu(ApiConfig config) {
        MenuAPI api = new MenuAPI(config);
        GetMenuResponse response = api.getMenu();
        LOG.debug("菜单:{}", response.toJsonString());
    }

    public void addCustomAccount(ApiConfig config) {
        CustomAPI customAPI = new CustomAPI(config);
        CustomAccount customAccount = new CustomAccount();
        customAccount.setAccountName("peiyu@i-xiaoshuo");
        customAccount.setNickName("帅哥");
//        customAccount.setPassword("123456");
        ResultType resultType = customAPI.addCustomAccount(customAccount);
        LOG.debug("添加结果:{}", resultType.toString());
    }

    public void getOauthPageUrl(ApiConfig config) {
        OauthAPI oauthAPI = new OauthAPI(config);
        String pageUrl = oauthAPI.getOauthPageUrl("http://121.40.140.41/erhuluanzi/app/testGet", OauthScope.SNSAPI_BASE, "123");
        LOG.debug("pageUrl:{}", pageUrl);
    }

    public void getToken(ApiConfig config) {
        OauthAPI oauthAPI = new OauthAPI(config);
        OauthGetTokenResponse response = oauthAPI.getToken("041821d373d6a18679cb0b1d8d5cc1ez");
        LOG.debug("response:{}", response.toJsonString());
    }

    public void oauthGetUserInfo(ApiConfig config) {
        OauthAPI oauthAPI = new OauthAPI(config);
        GetUserInfoResponse response = oauthAPI.getUserInfo("OezXcEiiBSKSxW0eoylIeKoEzhGrPf8vRE3NugAdMy16Em-NimErLsOMfMlZBW0P0wauuYLIzl1soHnV-9CGvQtUYxmd3F6ruwjs_SQNw90aZd_yFlVc85P2FlC01QVNyRktVrSX5zHIMkETyjZojQ", "opZYwt-OS8WFxwU-colRzpu50eOQ");
        LOG.debug("response:{}", response.toJsonString());

    }

    public void testGetJsApiTicket(ApiConfig config){
        Assert.assertTrue(StrUtil.isNotBlank(config.getJsApiTicket()));
        if(StrUtil.isNotBlank(config.getJsApiTicket())){
            LOG.debug("ok");
        }
    }

    public void testJsApiSign(ApiConfig config){
//        try {
//            //使用JS-SDK的示例数据来测试
//            String exampleTestStr = JsApiUtil.sign("sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg", "Wm3WZYTPz0wzccnW", 1414587457l, "http://mp.weixin.qq.com");
//            //JS-SDK的示例结果
//            String exampleResult = "f4d90daf4b3bca3078ab155816175ba34c443a7b";
//            Assert.assertEquals(exampleTestStr, exampleResult);
//            if(exampleResult.equals(exampleTestStr))
//            {
//                LOG.debug("ok");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        JsAPI jsAPI = new JsAPI(config);
        GetSignatureResponse response = jsAPI.getSignature("http://mp.weixin.qq.com");
        LOG.debug(response.toJsonString());
    }

    public void getUserData(ApiConfig config) {
        DataCubeAPI dataAPI = new DataCubeAPI(config);
        String[] format = {"yyyy-MM-dd"};
        Date beginDate = DateUtils.parseDate("2015-06-10", format);
        Date endDate = DateUtils.parseDate("2015-06-11", format);
        GetUserSummaryResponse response = dataAPI.getUserSummary(beginDate, endDate);
        GetUserCumulateResponse cumulateResponse = dataAPI.getUserCumulate(beginDate, endDate);
        LOG.debug("-----------------getUserSummary---------------------");
        LOG.debug(response.toJsonString());
        LOG.debug("-----------------getUserCumulate---------------------");
        LOG.debug(cumulateResponse.toJsonString());
    }

    public void getArticleData(ApiConfig config) {
        DataCubeAPI dataCubeAPI = new DataCubeAPI(config);
        String[] format = {"yyyy-MM-dd"};
        Date beginDate = DateUtils.parseDate("2015-01-25", format);
        Date endDate = DateUtils.parseDate("2015-01-26", format);
        GetArticleSummaryResponse articleSummary = dataCubeAPI.getArticleSummary(endDate);
        GetArticleTotalResponse articleTotal = dataCubeAPI.getArticleTotal(endDate);
        GetUserReadResponse userRead = dataCubeAPI.getUserRead(beginDate, endDate);
        GetUserReadHourResponse userReadHour = dataCubeAPI.getUserReadHour(endDate);
        GetUserShareResponse userShare = dataCubeAPI.getUserShare(beginDate, endDate);
        GetUserShareHourResponse userShareHour = dataCubeAPI.getUserShareHour(endDate);
        LOG.debug("------------------articleSummary----------------------");
        LOG.debug(articleSummary.toJsonString());
        LOG.debug("------------------articleTotal----------------------");
        LOG.debug(articleTotal.toJsonString());
        LOG.debug("------------------userRead----------------------");
        LOG.debug(userRead.toJsonString());
        LOG.debug("------------------userReadHour----------------------");
        LOG.debug(userReadHour.toJsonString());
        LOG.debug("------------------userShare----------------------");
        LOG.debug(userShare.toJsonString());
        LOG.debug("------------------userShareHour----------------------");
        LOG.debug(userShareHour.toJsonString());
    }

    public void sendAllMessage(ApiConfig config){
        MediaAPI mediaAPI = new MediaAPI(config);
        UploadMediaResponse response = mediaAPI.uploadMedia(MediaType.IMAGE, new File("C:/123.jpg"));
        String media_id = response.getMediaId();
        com.github.sd4324530.fastweixin.api.entity.Article article = new com.github.sd4324530.fastweixin.api.entity.Article();
        article.setThumbMediaId(media_id);
        article.setAuthor("云猴即时播报测试群发");
        article.setContentSourceUrl("http://www.baidu.com");
        article.setContent("云猴微信测试群发测试消息第一篇");
        article.setDigest("群发测试fangli");
        article.setShowConverPic(com.github.sd4324530.fastweixin.api.entity.Article.ShowConverPic.NO);
        UploadMediaResponse uploadMediaResponse = mediaAPI.uploadNews(Arrays.asList(article));
        MpNewsMsg mpNewsMsg = new MpNewsMsg();
        mpNewsMsg.setMediaId(uploadMediaResponse.getMediaId());
        MessageAPI messageAPI = new MessageAPI(config);
        GetSendMessageResponse messageResponse = messageAPI.sendMessageToUser(mpNewsMsg, false, "100");
        LOG.info("Send Message Id is " + messageResponse.getMsgId());
    }
    
    //给单个用户在公众号里发送消息
    public void sendCustomMessage(ApiConfig config){
        MediaAPI mediaAPI = new MediaAPI(config);
        
        /*
        UploadMediaResponse response = mediaAPI.uploadMedia(MediaType.IMAGE, new File("C:/123.jpg"));
        String media_id = response.getMediaId();
        com.github.sd4324530.fastweixin.api.entity.Article article = new com.github.sd4324530.fastweixin.api.entity.Article();
        article.setThumbMediaId(media_id);
        article.setAuthor("测试用户fangli");
        article.setContentSourceUrl("http://www.baidu.com");
        article.setContent("云猴微信测试群发测试消息第一篇");
        article.setDigest("群发测试fangli");
        article.setShowConverPic(com.github.sd4324530.fastweixin.api.entity.Article.ShowConverPic.NO);
        UploadMediaResponse uploadMediaResponse = mediaAPI.uploadNews(Arrays.asList(article));
        
        Article articles = new Article();
        articles.setDescription("长沙天气渐冷，我把我们用心在做的好产品“V袋洗”分享给大家，希望通过我们的努力，陪您度过一个洁净温暖的冬季。!");
        articles.setTitle("合意购微信测试");
        articles.setUrl("http://www.baidu.com");
        articles.setPicUrl("http://www.esulai.com/ganxi/Uploads/Picture/2014-10-27/544e40cbc198f.png");
        NewsMsg newmsg = new NewsMsg();
        List<Article> list = new ArrayList();
        list.add(articles);
        
        
     
        newmsg.setArticles(list);*/
        
        
       // java.util.Date dt = new Date();
        //System.out.println(dt.toString());   //java.util.Date的含义
        //long lSysTime1 = dt.getTime() / 1000;   //得到秒数，Date类型的getTime()返回毫秒数
        
        //textmsg.getCreateTime(dt.getTime());
        
      //  mpNewsMsg.setMsgType(msgType)
      //  mpNewsMsg.setMediaId(uploadMediaResponse.getMediaId());
       // MessageAPI messageAPI = new MessageAPI(config);
        
        TextMsg msg = new TextMsg();
        msg.setContent("感谢关注");
        CustomAPI customapi = new CustomAPI(config);
        ResultType messageResponse= customapi.sendCustomMessage("ogu7ywyS5bmjUNbhmMdrY-KATGr4", msg);
       // GetSendMessageResponse messageResponse = messageAPI.sendMessageToUser(mpNewsMsg, true, "0");
       // LOG.info("Send Message Id is " + messageResponse.getMsgId());
        LOG.info("Send Message Id is " + messageResponse.getDescription());
    }

    public void getUserGroups(ApiConfig config){
        UserAPI userAPI = new UserAPI(config);
        GetGroupsResponse response = userAPI.getGroups();
        for(Group group : response.getGroups()){
            System.out.println("Group id is " + group.getId() + ", name is " + group.getName() + ", count is " + group.getCount());
        }
    }
    
    //修改分组
    public void updateGroup(ApiConfig config) {
        UserAPI userAPI = new UserAPI(config);
        ResultType type = userAPI.updateGroup(103, "组别3");
        System.out.println(type.toString());
    }

    public void getCallbackIP(ApiConfig config) {
        SystemAPI systemAPI = new SystemAPI(config);
        List<String> callbackIP = systemAPI.getCallbackIP();
        LOG.debug("callbackIP:{}", callbackIP);
    }

    public void getShortUrl(ApiConfig config) {
        SystemAPI systemAPI = new SystemAPI(config);
        String shortUrl = systemAPI.getShortUrl("https://github.com/sd4324530/fastweixin");
        LOG.debug("getShortUrl:{}", shortUrl);
    }

    public void uploadImageMaterial(ApiConfig config){
        MediaAPI mediaAPI = new MediaAPI(config);
        UploadMaterialResponse response = mediaAPI.uploadMaterial(MediaType.IMAGE, new File("/Users/jileilei/Desktop/1.jpg"), "测试图片1", "测试图片1描述");
        System.out.println(response.getMediaId());
    }
    
    
    
}
