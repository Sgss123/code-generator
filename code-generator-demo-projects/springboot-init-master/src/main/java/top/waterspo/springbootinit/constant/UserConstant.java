package top.waterspo.springbootinit.constant;

/**
 * 用户常量
 *
 * @author <a href="https://github.com/Sgss123">MFJip</a>
 * @from <a href="https://space.bilibili.com/531522938">Bilibili</a>
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * 被封号
     */
    String BAN_ROLE = "ban";

    // endregion
}
