package social.clients.profile;

import org.pac4j.core.profile.converter.AttributeConverter;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.core.profile.converter.FormattedDateConverter;
import org.pac4j.core.profile.converter.GenderConverter;
import org.pac4j.oauth.profile.OAuthAttributesDefinition;

import java.util.Locale;

/**
 * Created by jingxiapang on 10/29/15.
 * Open source code referred from below github repository
 * https://github.com/eduosi/oauth-client/blob/master/src/main/java/com/buession/oauth/profile/weibo/WeiboAttributesDefinition.java
 */


public class WeiboAttributesDefinition extends OAuthAttributesDefinition{



    //user ID
    public final static String ID = "id";

    //username
    public final static String USERNAME = "username";

    /**
     * 用户微号
     */
    public final static String WEIHAO = "weihao";

    /**
     * 用户昵称
     */
    public final static String SCREEN_NAME = "screen_name";

    /**
     * 用户友好显示名称
     */
    public final static String NAME = "name";

    /**
     * 用户创建（注册）时间
     */
    public final static String CREATED_AT = "created_at";

    /**
     * 用户性别
     */
    public final static String GENDER = "gender";

    /**
     * 用户头像地址 user  photo ID address
     */
    public final static String AVATAR = "avatar";

    /**
     * 用户头像地址（大图），180×180像素 //user id photo picture with 180*180 pixel
     */
    public final static String AVATAR_LARGE = "avatar_large";

    /**
     * 用户高清原图头像地址
     */
    public final static String AVATAR_HD = "avatar_hd";

    /**
     * 是否是微博认证用户，即加V用户，true：是，false：否
     */
    public final static String VERIFIED = "verified";

    /**
     *
     */
    @Deprecated
    public final static String VERIFIED_TYPE = "verified_type";

    /**
     * 用户认证原因
     */
    public final static String VERIFIED_REASON = "verified_reason";

    /**
     * 用户所在省级份
     */
    public final static String PROVINCE = "province";

    /**
     * 用户所在城市
     */
    public final static String CITY = "city";

    /**
     * 用户联系地址
     */
    public final static String ADDRESS = "address";

    /**
     * 用户联系地址
     */
    public final static String LOCATION = "location";

    /**
     * 用户的个性化域名 //user self-defined weibo website address
     */
    public final static String DOMAIN = "domain";

    /**
     * 用户的微博地址 // user weibo address
     */
    public final static String PROFILE_URL = "profileUrl";

    /**
     * 用户博客地址
     */
    public final static String BLOG_URL = "url";

    /**
     * 该用户是否关注当前登录用户，true：是，false：否
     * whther the user is following present user.
     */
    public final static String FOLLOW_ME = "follow_me";

    /**
     * 粉丝数
     */
    public final static String FOLLOWERS_COUNT = "followers_count";

    /**
     * 用户的互粉数 // user follow each other
     */
    public final static String BI_FOLLOWERS_COUNT = "bi_followers_count";

    /**
     * 关注数
     */
    public final static String FRIENDS_COUNT = "friends_count";

    /**
     * 微博数 // weibo count
     */
    public final static String STATUSES_COUNT = "statuses_count";

    /**
     * 收藏数 //favortie weibo count
     */
    public final static String FAVOURITES_COUNT = "favourites_count";

    /**
     *
     */
    @Deprecated
    public final static String IS_FOLLOWING = "following";

    /**
     * 是否允许所有人给我发私信，true：是，false：否
     * allow all people to send a private message to me or not.
     */
    public final static String ALLOW_ALL_ACT_MSG = "allow_all_act_msg";

    /**
     * 是否允许标识用户的地理位置，true：是，false：否
     */
    public final static String GEO_ENABLED = "geo_enabled";

    /**
     * 是否允许所有人对我的微博进行评论，true：是，false：否
     * //restrict all people comment my weibo or not
     */
    public final static String ALLOW_ALL_COMMENT = "allow_all_comment";

    /**
     * 用户的在线状态，0：不在线、1：在线
     * wether the use is online - 0: off line 1: online
     */
    public final static String IS_ONLINE = "online_status";

    /**
     * 用户当前的语言版本
     * user present language version
     */
    public final static String LANG = "lang";

    /**
     * 用户个人备注
     * user personal remark
     */
    public final static String REMARK = "remark";

    /**
     * 用户个人描述
     * user personal descroption
     */
    public final static String DESCRIPTION = "description";

    private final static ProfileUrlConverter profileUrlConverter = new ProfileUrlConverter();

    private final static FormattedDateConverter formattedDateConverter = new FormattedDateConverter(
            "EEE MMM dd HH:mm:ss Z yyyy", Locale.US);

    private final static GenderConverter genderConverter = new GenderConverter("m", "f");

    public WeiboAttributesDefinition() {
        addAttribute(ID, Converters.stringConverter);
        addAttribute(USERNAME, Converters.stringConverter);
        addAttribute(WEIHAO, Converters.stringConverter);
        addAttribute(SCREEN_NAME, Converters.stringConverter);
        addAttribute(NAME, Converters.stringConverter);
        addAttribute(CREATED_AT, formattedDateConverter);
        addAttribute(GENDER, genderConverter);
        addAttribute(AVATAR, Converters.stringConverter);
        addAttribute(AVATAR_LARGE, Converters.stringConverter);
        addAttribute(AVATAR_HD, Converters.stringConverter);
        addAttribute(VERIFIED, Converters.booleanConverter);
        addAttribute(VERIFIED_TYPE, Converters.integerConverter);
        addAttribute(VERIFIED_REASON, Converters.stringConverter);
        addAttribute(PROVINCE, Converters.stringConverter);
        addAttribute(CITY, Converters.stringConverter);
        addAttribute(ADDRESS, Converters.stringConverter);
        addAttribute(LOCATION, Converters.stringConverter);
        addAttribute(DOMAIN, Converters.stringConverter);
        addAttribute(PROFILE_URL, profileUrlConverter);
        addAttribute(BLOG_URL, Converters.stringConverter);
        addAttribute(FOLLOW_ME, Converters.booleanConverter);
        addAttribute(FOLLOWERS_COUNT, Converters.integerConverter);
        addAttribute(BI_FOLLOWERS_COUNT, Converters.integerConverter);
        addAttribute(FRIENDS_COUNT, Converters.integerConverter);
        addAttribute(STATUSES_COUNT, Converters.integerConverter);
        addAttribute(FAVOURITES_COUNT, Converters.integerConverter);
        addAttribute(IS_FOLLOWING, Converters.booleanConverter);
        addAttribute(ALLOW_ALL_ACT_MSG, Converters.booleanConverter);
        addAttribute(GEO_ENABLED, Converters.booleanConverter);
        addAttribute(ALLOW_ALL_COMMENT, Converters.booleanConverter);
        addAttribute(IS_ONLINE, Converters.booleanConverter);
        addAttribute(LANG, Converters.stringConverter);
        addAttribute(REMARK, Converters.stringConverter);
        addAttribute(DESCRIPTION, Converters.stringConverter);
    }

    static class ProfileUrlConverter implements AttributeConverter<String> {

        @Override
        public String convert(Object attribute) {
            return "http://www.weibo.com/" + attribute + "/home";
        }

    }


}
