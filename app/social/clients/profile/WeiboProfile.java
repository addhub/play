package social.clients.profile;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.core.profile.Gender;
import org.pac4j.oauth.profile.OAuth20Profile;

import java.util.Date;

/**
 * Created by sasinda on 10/29/15.
 * Open Source code refer from below address:
 * https://github.com/eduosi/oauth-client/blob/master/src/main/java/com/buession/oauth/profile/weibo/WeiboProfile.java
 */
public class WeiboProfile extends OAuth20Profile {

    private static final long serialVersionUID = -3526611200283123567L;

    @Override
    public String getUsername() {
        return getScreenName();
    }

    /**
     * 返回用户微号
     *
     * @return 用户微号
     */
    public Object getWeiHao() {
        return getAttribute(WeiboAttributesDefinition.WEIHAO);
    }

    /**
     * 返回用户昵称
     *
     * @return 用户昵称
     */
    public String getScreenName() {
        return (String) getAttribute(WeiboAttributesDefinition.SCREEN_NAME);
    }

    /**
     * 返回用户友好显示名称
     *
     * @return 用户友好显示名称
     */

    public String getRealName() {
        return (String) getAttribute(WeiboAttributesDefinition.NAME);
    }

    /**
     * 返回用户用户创建（注册）时间
     *
     * @return 用户用户创建（注册）时间
     */
    public Date getCreatedAt() {
        return (Date) getAttribute(WeiboAttributesDefinition.CREATED_AT);
    }

    /**
     * 返回用户性别
     *
     * @return 用户性别
     */
    @Override
    public Gender getGender() {
        return (Gender) getAttribute(WeiboAttributesDefinition.GENDER);
    }

    /**
     * 返回用户大小 180×180 像素头像地址（大图）
     *
     * @return 用户大小 180×180 像素头像地址（大图）
     */
    public String getAvatarLarge() {
        return (String) getAttribute(WeiboAttributesDefinition.AVATAR_LARGE);
    }

    /**
     * 返回用户高清头像原图地址
     *
     * @return 用户高清头像原图地址
     */
    public String getAvatarHd() {
        return (String) getAttribute(WeiboAttributesDefinition.AVATAR_HD);
    }

    /**
     * 返回用户头像地址
     *
     * @return 用户头像地址
     */

    public String getAvatar() {
        String avatarHd = getAvatarHd();
        return avatarHd != null && avatarHd.length() > 0 ? avatarHd : getAvatarLarge();
    }

    /**
     * 返回用户是否是微博认证用户
     *
     * @return 是否是微博认证用户
     */
    public boolean getIsVerified() {
        return (Boolean) getAttribute(WeiboAttributesDefinition.VERIFIED);
    }

    /**
     * Return the verified type of the user.
     *
     * @return the verified type of the user
     */
    @Deprecated
    public String getVerifiedType() {
        return (String) getAttribute(WeiboAttributesDefinition.VERIFIED_TYPE);
    }

    /**
     * 返回用户认证原因
     *
     * @return 认证原因
     */
    public String getVerifiedReason() {
        return (String) getAttribute(WeiboAttributesDefinition.VERIFIED_REASON);
    }

    /**
     * 返回用户个性化域名
     *
     * @return 用户个性化域名
     */
    public String getDomain() {
        return (String) getAttribute(WeiboAttributesDefinition.DOMAIN);
    }

    /**
     * 返回用户个人资料页地址
     *
     * @return 用户个人资料页地址
     */
    @Override
    public String getProfileUrl() {
        return (String) getAttribute(WeiboAttributesDefinition.PROFILE_URL);
    }

    /**
     * 返回用户博客地址
     *
     * @return 用户博客地址
     */
    public String getBlogUrl() {
        return (String) getAttribute(WeiboAttributesDefinition.BLOG_URL);
    }

    /**
     * 返回用户所在省级 ID
     *
     * @return 用户所在省级 ID
     */

    public String getProvince() {
        return (String) getAttribute(WeiboAttributesDefinition.PROVINCE);
    }

    /**
     * 返回用户所在城市 ID
     *
     * @return 返回用户所在城市 ID
     */

    public String getCity() {
        return (String) getAttribute(WeiboAttributesDefinition.CITY);
    }

    /**
     * 返回用户所在地
     *
     * @return 返回用户所在地
     */

    public String getAddress() {
        return (String) getAttribute(WeiboAttributesDefinition.LOCATION);
    }

    /**
     * 返回用户是否是微博 VIP 用户（即认证用户）
     *
     * @return 是否是微博 VIP 用户（即认证用户）
     */

    public boolean getIsVip() {
        return getIsVerified();
    }

    /**
     * 返回用户是否关注当前登录用户
     *
     * @return 是否是否关注当前登录用户
     */
    public boolean getIsFollowedMe() {
        return (Boolean) getAttribute(WeiboAttributesDefinition.FOLLOW_ME);
    }

    /**
     * 返回用户粉丝数
     *
     * @return 用户粉丝数
     */
    public int getFollowersCount() {
        return (Integer) getAttribute(WeiboAttributesDefinition.FOLLOWERS_COUNT);
    }

    /**
     * 返回用户互粉数
     *
     * @return 用户互粉数
     */
    public int getBiFollowersCount() {
        return (Integer) getAttribute(WeiboAttributesDefinition.BI_FOLLOWERS_COUNT);
    }

    /**
     * 返回用户关注数
     *
     * @return 用户关注数
     */
    public int getFriendsCount() {
        return (Integer) getAttribute(WeiboAttributesDefinition.FRIENDS_COUNT);
    }

    /**
     * 返回用户微博数
     *
     * @return 用户微博数
     */
    public int getStatusesCount() {
        return (Integer) getAttribute(WeiboAttributesDefinition.STATUSES_COUNT);
    }

    /**
     * 返回用户收藏数
     *
     * @return 用户收藏数
     */
    public int getFavouritesCount() {
        return (Integer) getAttribute(WeiboAttributesDefinition.FAVOURITES_COUNT);
    }

    /**
     * Return is or not following for the user.
     *
     * @return is or not following me for the user
     */
    @Deprecated
    public boolean getIsFollowing() {
        return (Boolean) getAttribute(WeiboAttributesDefinition.IS_FOLLOWING);
    }

    /**
     * 返回是否允许所有人给我发私信
     *
     * @return 是否允许所有人给我发私信
     */
    public boolean getIsAllowAllActMsg() {
        return (Boolean) getAttribute(WeiboAttributesDefinition.ALLOW_ALL_ACT_MSG);
    }

    /**
     * 返回是否允许标识用户的地理位置
     *
     * @return 是否允许标识用户的地理位置
     */
    public boolean getIsGeoEnabled() {
        return (Boolean) getAttribute(WeiboAttributesDefinition.GEO_ENABLED);
    }

    /**
     * 返回是否允许所有人对我的微博进行评论
     *
     * @return 是否允许所有人对我的微博进行评论
     */
    public boolean getIsAllowAllComment() {
        return (Boolean) getAttribute(WeiboAttributesDefinition.ALLOW_ALL_COMMENT);
    }

    /**
     * 返回用户是否在线
     *
     * @return 是否用户是否在线
     */
    public boolean getIsOnline() {
        return (Boolean) getAttribute(WeiboAttributesDefinition.IS_ONLINE);
    }

    /**
     * 返回用户当前的语言版本
     *
     * @return 用户当前的语言版本
     */
    public String getLang() {
        return (String) getAttribute(WeiboAttributesDefinition.LANG);
    }

    /**
     * 返回用户备注信息，只有在查询用户关系时才返回此字段
     *
     * @return 用户备注信息
     */
    public String getRemark() {
        return (String) getAttribute(WeiboAttributesDefinition.REMARK);
    }

    /**
     * 返回用户个人描述
     *
     * @return 用户个人描述
     */
    public String getDescription() {
        return (String) getAttribute(WeiboAttributesDefinition.DESCRIPTION);
    }

    public static final AttributesDefinition weiboDefinition = new WeiboAttributesDefinition();
    /**
     *
     * 返回用户资料属性属性定义
     *
     * @return 用户资料属性属性定义
     */
    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return weiboDefinition ;
    }


}
